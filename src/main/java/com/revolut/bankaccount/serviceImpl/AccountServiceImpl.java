package com.revolut.bankaccount.serviceImpl;

import com.revolut.bankaccount.dao.AccountDao;
import com.revolut.bankaccount.daoImpl.AccountDaoImpl;
import com.revolut.bankaccount.exception.DuplicateAccountIdException;
import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Account;
import com.revolut.bankaccount.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private static AccountServiceImpl instance;
    private AccountDao accountDao = AccountDaoImpl.getInstance();

    private AccountServiceImpl(){}

    public static AccountServiceImpl getInstance(){
        if(instance == null){
            synchronized (AccountServiceImpl.class) {
                if(instance == null) {
                    instance = new AccountServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Account createAccount(long accountNumber, double initialBalance) throws DuplicateAccountIdException {
        return accountDao.createAccount(accountNumber,initialBalance);
    }

    @Override
    public Account getAccount(long accountNumber) throws NoSuchAccountFoundException {
        return accountDao.getAccount(accountNumber);
    }

    @Override
    public double balanceEnquiry(long accountNumber) throws NoSuchAccountFoundException {
        return accountDao.balanceEnquiry(accountNumber);
    }

    @Override
    public void withdraw(long accountNumber, double amount) throws NoSuchAccountFoundException, InsufficientFundsException {
        accountDao.withdraw(accountNumber,amount);
    }

    @Override
    public void deposit(long accountId, double amount) throws NoSuchAccountFoundException {
        accountDao.deposit(accountId,amount);
    }
}
