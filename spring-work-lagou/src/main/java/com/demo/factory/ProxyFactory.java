package com.demo.factory;

import com.demo.customanno.Autowired;
import com.demo.customanno.Service;
import com.demo.util.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Service
public class ProxyFactory {

    @Autowired
    private TransactionManager transactionManager;

    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), (proxy, method, args) -> {
            Object result;
            try {
                transactionManager.beginTransaction();
                result = method.invoke(obj, args);
                transactionManager.commitTransaction();
            } catch (Exception e) {
                e.printStackTrace();
                transactionManager.rollbackTransaction();
                throw e;
            }
            return result;
        });
    }

    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result;
                try {
                    transactionManager.beginTransaction();
                    result = method.invoke(obj, objects);
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollbackTransaction();
                    throw e;
                }
                return result;
            }
        });

    }

}
