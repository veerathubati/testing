package com.revolut.bankaccount.serviceImpl;

import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Account;
import com.revolut.bankaccount.model.Transaction;
import com.revolut.bankaccount.service.AccountService;
import com.revolut.bankaccount.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

    private static TransactionServiceImpl instance;
    private AccountService accountService = AccountServiceImpl.getInstance();

    private TransactionServiceImpl(){}

	public static TransactionServiceImpl getInstance(){
        if(instance == null){
            synchronized (TransactionServiceImpl.class) {
                if(instance == null) {
                    instance = new TransactionServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void transferMoney(Transaction transaction) throws NoSuchAccountFoundException, InsufficientFundsException {
        Account transmitterAccountInfo =  accountService.getAccount(transaction.getTransmitterId());
        Account receiverAccountInfo = accountService.getAccount(transaction.getReceiverId());
        	
        	accountService.withdraw(transaction.getTransmitterId(), transaction.getAmount());
        	
        	accountService.deposit(transaction.getReceiverId(), transaction.getAmount());        
                 
    }
}
