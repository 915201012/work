package com.demo.service.impl;

import com.demo.customanno.Autowired;
import com.demo.customanno.Service;
import com.demo.customanno.Transactional;
import com.demo.dao.AccountDao;
import com.demo.pojo.Account;
import com.demo.service.TransferService;

@Service("transferService")
@Transactional(value = true)
public class TransferServiceImpl implements TransferService {

    @Autowired
    public AccountDao accountDao;


    @Override
    public void transfer(String formCardNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(formCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);
        accountDao.updateAccount(from);
//        int i = 1 / 0;
        accountDao.updateAccount(to);

    }
}
