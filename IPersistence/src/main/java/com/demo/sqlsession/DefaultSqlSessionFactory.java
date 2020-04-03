package com.demo.sqlsession;

import com.demo.pojo.Configuration;

/**
 * @author user
 */
public class DefaultSqlSessionFactory implements  SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {

        return new DefaultSqlSession(configuration);
    }
}
