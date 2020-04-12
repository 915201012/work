package com.demo.dao.impl;

import com.demo.customanno.Autowired;
import com.demo.customanno.Service;
import com.demo.dao.AccountDao;
import com.demo.pojo.Account;
import com.demo.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    public ConnectionUtils connectionUtils;


    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        Connection connection = connectionUtils.getCurrentThreadConn();
        String sql = "select * from account where cardNo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = new Account();
        while (resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }
        resultSet.close();
        preparedStatement.close();
        return account;
    }

    @Override
    public int updateAccount(Account account) throws Exception {
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, account.getMoney());
        preparedStatement.setString(2, account.getCardNo());
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        return i;
    }
}
