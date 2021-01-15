package com.revolut.bankaccount.exception;

import com.revolut.bankaccount.constants.Constants;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(long accountId){
        super(Constants.INSUFFICIENT_FUNDS + accountId);
    }
}
