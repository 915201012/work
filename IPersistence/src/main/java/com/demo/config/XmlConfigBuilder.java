package com.demo.config;

import com.demo.io.Resources;
import com.demo.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author user
 */
public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }


    public Configuration parse(InputStream in) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);

        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream inputStream = Resources.getResourceAsStream(mapperPath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(inputStream);
        }
        return configuration;
    }
}
