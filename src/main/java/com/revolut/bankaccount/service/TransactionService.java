package com.revolut.bankaccount.service;

import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Transaction;

public interface TransactionService {

	/*
	 * Processing the flow for the given instance of transaction
	 * @param transaction : processing transaction
	 */
	void transferMoney(Transaction transaction) throws NoSuchAccountFoundException, InsufficientFundsException;
}
