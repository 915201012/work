package com.demo.sqlsession;

import com.demo.pojo.Configuration;
import com.demo.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author user
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... args) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, args);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... args) throws Exception {
        List<Object> objects = selectList(statementId, args);
        if (objects != null && objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为null或因参数引起的返回结果数量错误");
        }
    }

    @Override
    public void save(String statementId, Object... args) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        simpleExecutor.save(configuration, mappedStatement, args);
    }

    @Override
    public void update(String statementId, Object... args) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        simpleExecutor.update(configuration, mappedStatement, args);
    }

    @Override
    public void delete(String statementId, Object... args) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        simpleExecutor.delete(configuration, mappedStatement, args);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(),
                new Class[]{mapperClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //获取方法名
                        String methodName = method.getName();
                        //获取当前类名
                        String className = method.getDeclaringClass().getName();
                        String statementId = className + "." + methodName;
                        switch (methodName) {
                            case "findAll":
                                return selectList(statementId, args);
                            case "findByCondition":
                                return selectOne(statementId, args);
                            case "saveUser":
                                save(statementId, args);
                                break;
                            case "updateUser":
                                update(statementId, args);
                                break;
                            case "deleteUser":
                                delete(statementId, args);
                                break;
                        }
                        return null;
                    }
                });

        return (T) proxyInstance;
    }

}
