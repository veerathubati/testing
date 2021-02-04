package com.revolut.bankaccount.exception;

import com.revolut.bankaccount.constants.Constants;

public class InvalidIdException extends Exception {

	public InvalidIdException(String accountId) {
		super(Constants.ACCOUNT_NUMBER_ALREADY_EXISTS + accountId);
	}
}
