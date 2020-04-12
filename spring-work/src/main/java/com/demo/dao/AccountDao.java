package com.demo.dao;

import com.demo.pojo.Account;

public interface AccountDao {
    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccount(Account account) throws Exception;
}
