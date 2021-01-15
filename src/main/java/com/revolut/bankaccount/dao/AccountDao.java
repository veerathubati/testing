package com.revolut.bankaccount.dao;

import com.revolut.bankaccount.exception.DuplicateAccountIdException;
import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Account;

public interface AccountDao {

	/*
	 * Creation of a new Account based on the account number and amount of balance
	 * that has initially
	 * @param accountNumber : AccountNumber for that particular Account
	 * @param initialBalance : Initial Balance for the new opened account
	 */
	Account createAccount(long accountNumber, double initialBalance) throws DuplicateAccountIdException;

	/*
	 * Grabbing of the account based on the accountNumber
	 * @param accountNumber : AccountNumber for that particular Account
	 */
	Account getAccount(long accountNumber) throws NoSuchAccountFoundException;

	/*
	 * Withdraws the amount of money from that particular accountNumber specified
	 * @param accountNumber : AccountNumber for that particular Account
	 * @param amount : Balance that needs to be withdrawal from the account
	 */
	void withdraw(long accountNumber, double amount) throws NoSuchAccountFoundException, InsufficientFundsException;

	/*
	 * Deposit the amount of money for that particular accountNumber specified
	 * @param accountNumber : AccountNumber for that particular Account
	 * @param amount : Amount of money to be deposited for that particular account
	 */
	void deposit(long accountNumber, double amount) throws NoSuchAccountFoundException;

	/*
	 * Checking the current balance have it for the particular AccountNumber
	 * @param accountNumber : AccountNumber for that particular Account
	 */
	double balanceEnquiry(long accountNumber) throws NoSuchAccountFoundException;
}
