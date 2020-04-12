package com.demo.util;

import com.demo.customanno.Autowired;
import com.demo.customanno.Service;

import java.sql.SQLException;

@Service("transactionManager")
public class TransactionManager {

    @Autowired
    public ConnectionUtils connectionUtils;


    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }

    public void rollbackTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }
}
