package com.framework.servlet;

import com.framework.annotations.*;
import com.framework.pojo.Handler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {

    private Properties properties = new Properties();
    private List<String> classNameList = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private List<Handler> handlerList = new ArrayList<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        String initParameter = config.getInitParameter("contextConfigLocation");
        if (initParameter == null || "".equalsIgnoreCase(initParameter.trim())) {
            return;
        }
        //加载配置文件
        doLoadConfig(initParameter);
        //扫描自定义注解
        doScan(properties.getProperty("scanpackage"));
        //初始化对象(实现IOC容器)
        doInstance();
        //依赖注入
        doAutoWired();
        //构造一个HandleMapping处理器映射器，将配置好的url和method绑定关系
        initHandleMapping();
        System.out.println("custom springmvc init success~");
    }

    private void doLoadConfig(String initParameter) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(initParameter);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void doScan(String scanPackage) {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + scanPackage.replaceAll("\\.", "/");
        File pathPackage = new File(path);
        File[] files = pathPackage.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                doScan(scanPackage + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                String className = scanPackage + "." + file.getName().replaceAll(".class", "");
                classNameList.add(className);
            }
        }
    }

    private void doInstance() {
        if (classNameList.size() == 0) {
            return;
        }
        try {
            for (int i = 0; i < classNameList.size(); i++) {
                String className = classNameList.get(i);
                Class<?> aClass = Class.forName(className);
                if (aClass.isAnnotationPresent(Controller.class)) {
                    String simpleName = aClass.getSimpleName();
                    String key = lowerFirst(simpleName);
                    ioc.put(key, aClass.getDeclaredConstructor().newInstance());
                } else if (aClass.isAnnotationPresent(Service.class)) {
                    Service annotation = aClass.getAnnotation(Service.class);
                    String key = annotation.value();
                    if (!"".equals(key.trim())) {
                        ioc.put(key, aClass.getDeclaredConstructor().newInstance());
                    } else {
                        key = lowerFirst(aClass.getSimpleName());
                        ioc.put(key, aClass.getDeclaredConstructor().newInstance());
                    }
                    Class<?>[] interfaces = aClass.getInterfaces();
                    if (interfaces != null && interfaces.length > 0) {
                        for (int j = 0; j < interfaces.length; j++) {
                            ioc.put(interfaces[j].getName(), aClass.getDeclaredConstructor().newInstance());
                        }
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doAutoWired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (int i = 0; i < declaredFields.length; i++) {
                    Field declaredField = declaredFields[i];
                    if (!declaredField.isAnnotationPresent(Autowired.class)) {
                        continue;
                    }
                    Autowired annotation = declaredField.getAnnotation(Autowired.class);
                    String key = annotation.value();
                    if ("".equals(key.trim())) {
                        key = declaredField.getType().getName();
                    }
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(entry.getValue(), ioc.get(key));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void initHandleMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> aClass = entry.getValue().getClass();
            if (!aClass.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseUrl = "";
            if (aClass.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
                baseUrl = annotation.value();
            }
            Method[] methods = aClass.getMethods();
            if (methods != null && methods.length > 0) {
                for (int i = 0; i < methods.length; i++) {
                    Method method = methods[i];
                    if (!method.isAnnotationPresent(RequestMapping.class)) {
                        continue;
                    }
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    String url = baseUrl + annotation.value();
                    Handler handler = new Handler(entry.getValue(), method, Pattern.compile(url));
                    Parameter[] parameters = method.getParameters();

                    if (parameters != null && parameters.length > 0) {
                        for (int j = 0; j < parameters.length; j++) {
                            Parameter parameter = parameters[j];
                            if (parameter.getType() == HttpServletRequest.class || parameter.getType() == HttpServletResponse.class) {
                                handler.getParamMapping().put(parameter.getType().getSimpleName(), j);
                            } else {
                                handler.getParamMapping().put(parameter.getName(), j);
                            }
                        }
                    }
                    if (method.isAnnotationPresent(Security.class)) {
                        Security security = method.getAnnotation(Security.class);
                        String[] value = security.value();
                        if (value.length > 0) {
                            handler.setLimits(Arrays.asList(value));
                        }
                    }

                    handlerList.add(handler);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Handler handler = getHandle(req);
        if (handler == null) {
            resp.getWriter().write("404 Not Found");
            return;
        }
        List<String> limits = handler.getLimits();
        if (limits.size() > 0) {
            String username = req.getParameter("username");
            if (!limits.contains(username)) {
                resp.setCharacterEncoding("UTF-8");
                resp.setHeader("Content-Type", "text/html;charset=UTF-8");
                resp.getWriter().write(username + "没有权限访问:" + handler.getPattern().toString());
                return;
            }
        }

        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        Object[] params = new Object[parameterTypes.length];
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            String value = StringUtils.join(param.getValue(), ",");
            if (!handler.getParamMapping().containsKey(param.getKey())) {
                continue;
            }
            Integer index = handler.getParamMapping().get(param.getKey());
            params[index] = value;
        }
        if (handler.getParamMapping().containsKey(HttpServletRequest.class.getSimpleName())) {
            Integer reqIndex = handler.getParamMapping().get(HttpServletRequest.class.getSimpleName());
            params[reqIndex] = req;
        }
        if (handler.getParamMapping().containsKey(HttpServletResponse.class.getSimpleName())) {
            Integer respIndex = handler.getParamMapping().get(HttpServletResponse.class.getSimpleName());
            params[respIndex] = resp;
        }
        try {
            handler.getMethod().invoke(handler.getClazz(), params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private Handler getHandle(HttpServletRequest req) {
        if (handlerList.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        for (Handler handler : handlerList) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (matcher.matches()) {
                return handler;
            }
            continue;
        }
        return null;
    }

    private String lowerFirst(String simpleName) {
        char[] chars = simpleName.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

}
