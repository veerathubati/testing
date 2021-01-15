package com.revolut.bankaccount.exception;

import com.revolut.bankaccount.constants.Constants;

public class NoSuchAccountFoundException extends Exception{
    public NoSuchAccountFoundException(long accountId){
        super(Constants.NO_SUCH_ACCOUNT_FOUND + accountId);
    }
}
