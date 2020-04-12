package com.demo.util;

import com.alibaba.druid.pool.DruidDataSource;


public class DruidUtils {

    private DruidUtils(){}

    private static DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/work");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
    }

    public static DruidDataSource getInstance(){
        return druidDataSource;
    }
}
