package com.demo.sqlsession;

import com.demo.config.XmlConfigBuilder;
import com.demo.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author user
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {
        XmlConfigBuilder builder = new XmlConfigBuilder();
        Configuration configuration = builder.parse(in);
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
