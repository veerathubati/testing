package com.revolut.bankaccount.exception;

import com.revolut.bankaccount.constants.Constants;

public class DuplicateAccountIdException extends Exception{
    public DuplicateAccountIdException(long accountId){
        super(Constants.ACCOUNT_NUMBER_ALREADY_EXISTS + accountId);
    }
}
