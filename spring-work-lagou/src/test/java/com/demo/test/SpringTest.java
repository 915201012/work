package com.demo.test;

import com.demo.pojo.Account;
import com.demo.util.DruidUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpringTest {

    @Test
    public void test() throws Exception {
        Connection connection = DruidUtils.getInstance().getConnection();
        String sql = "select * from account where cardNo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "6029621011000");
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = new Account();
        while (resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }
        System.out.println(account);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }


    @Test
    public void getClassByPackage() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        String value = rootElement.attributeValue("base-package");
        System.out.println(value);

    }

}
