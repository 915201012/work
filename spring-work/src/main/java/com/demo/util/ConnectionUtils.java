package com.demo.util;

import com.demo.customanno.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service("connectionUtils")
public class ConnectionUtils {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public Connection getCurrentThreadConn() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null){
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
}
