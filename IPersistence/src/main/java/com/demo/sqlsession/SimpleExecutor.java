package com.demo.sqlsession;

import com.demo.config.BoundSql;
import com.demo.pojo.Configuration;
import com.demo.pojo.MappedStatement;
import com.demo.util.GenericTokenParser;
import com.demo.util.ParameterMapping;
import com.demo.util.ParameterMappingTokenHandler;
import com.google.common.collect.Lists;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * @author user
 */
public class SimpleExecutor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement statement, Object... args) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();
        String sql = statement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlTest());
        String parameterType = statement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(args[0]);
            preparedStatement.setObject(i + 1, o);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = statement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        List<Object> objects = Lists.newArrayList();
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    @Override
    public void save(Configuration configuration, MappedStatement statement, Object... args) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();
        String sql = statement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlTest());
        String parameterType = statement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(args[0]);
            preparedStatement.setObject(i + 1, o);
        }
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Configuration configuration, MappedStatement statement, Object... args) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();
        String sql = statement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlTest());
        String parameterType = statement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(args[0]);
            preparedStatement.setObject(i + 1, o);
        }
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Configuration configuration, MappedStatement statement, Object... args) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();
        String sql = statement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlTest());
        String parameterType = statement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(args[0]);
            preparedStatement.setObject(i + 1, o);
        }
        preparedStatement.executeUpdate();
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        }
        return null;
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parse = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parse, parameterMappings);
        return boundSql;
    }
}
