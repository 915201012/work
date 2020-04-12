package com.demo.config;

import com.demo.pojo.Configuration;
import com.demo.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author user
 */
public class XmlMapperBuilder {

    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }


    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        selectNodes(rootElement, namespace, "//select");
        selectNodes(rootElement, namespace, "//insert");
        selectNodes(rootElement, namespace, "//update");
        selectNodes(rootElement, namespace, "//delete");
    }

    private void selectNodes(Element rootElement, String namespace, String node) {
        List<Element> list = rootElement.selectNodes(node);
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();
            MappedStatement statement = new MappedStatement();
            statement.setId(id);
            statement.setResultType(resultType);
            statement.setParameterType(parameterType);
            statement.setSql(sql);
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, statement);
        }
    }

}
