package com.revolut.bankaccount.exception;

import com.revolut.bankaccount.constants.Constants;

public class InvalidTransferException extends Exception {

	public InvalidTransferException(String accountId) {
		super(Constants.INVALID_AMOUNT_TRANSFER + accountId);
	}
}
