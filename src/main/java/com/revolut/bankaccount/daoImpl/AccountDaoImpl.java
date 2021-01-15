package com.revolut.bankaccount.daoImpl;

import java.util.concurrent.ConcurrentHashMap;

import com.revolut.bankaccount.dao.AccountDao;
import com.revolut.bankaccount.exception.DuplicateAccountIdException;
import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Account;

public class AccountDaoImpl implements AccountDao {

    private static AccountDaoImpl instance;
    private ConcurrentHashMap<Long,Account> accountMap = new ConcurrentHashMap<>();
    private static final Object newAccountLock = new Object();

    private AccountDaoImpl(){}

    public static AccountDaoImpl getInstance(){
        if(instance == null){
            synchronized (AccountDaoImpl.class) {
                if(instance == null) {
                    instance = new AccountDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Account createAccount(long accountNumber, double initialBalance) throws DuplicateAccountIdException{
        if(accountMap.containsKey(accountNumber)){
            throw new DuplicateAccountIdException(accountNumber);
        }else {
            synchronized (newAccountLock) {
                if(accountMap.containsKey(accountNumber)){
                    throw new DuplicateAccountIdException(accountNumber);
                }else{
                    Account account = new Account(accountNumber,initialBalance);
                    accountMap.put(accountNumber,account);
                    return account;
                }
            }
        }
    }

    @Override
    public Account getAccount(long accountNumber) throws NoSuchAccountFoundException {
        if(accountMap.containsKey(accountNumber)) {
            return accountMap.get(accountNumber);
        }else {
            throw new NoSuchAccountFoundException(accountNumber);
        }
    }

    @Override
    public void withdraw(long accountNumber, double amount) throws NoSuchAccountFoundException, InsufficientFundsException {
        if(!accountMap.containsKey(accountNumber)){
            throw new NoSuchAccountFoundException(accountNumber);
        }else if(amount > accountMap.get(accountNumber).getBalance()){
            throw new InsufficientFundsException(accountNumber);
        }else{
            if(amount > accountMap.get(accountNumber).getBalance()){
                throw new InsufficientFundsException(accountNumber);
            }else{
                Account account = accountMap.get(accountNumber);
                account.setBalance(account.getBalance() - amount);
                accountMap.put(accountNumber,account);
            }
        }
    }

    @Override
    public void deposit(long accountId, double amount) throws NoSuchAccountFoundException {
        if(!accountMap.containsKey(accountId)){
            throw new NoSuchAccountFoundException(accountId);
        }else {
            Account account = accountMap.get(accountId);
            account.setBalance(account.getBalance() + amount);
            accountMap.put(accountId,account);
        }
    }

    @Override
    public double balanceEnquiry(long accountId) throws NoSuchAccountFoundException {
        if(!accountMap.containsKey(accountId)){
            throw new NoSuchAccountFoundException(accountId);
        }else {
            return accountMap.get(accountId).getBalance();
        }
    }
}
