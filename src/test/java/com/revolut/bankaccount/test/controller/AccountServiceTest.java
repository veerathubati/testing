package com.revolut.bankaccount.test.controller;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revolut.bankaccount.exception.DuplicateAccountIdException;
import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;
import com.revolut.bankaccount.model.Account;
import com.revolut.bankaccount.service.AccountService;
import com.revolut.bankaccount.serviceImpl.AccountServiceImpl;

public class AccountServiceTest {

	private static AccountService accountService;

	@BeforeClass
	public static void setUp() {
		accountService = AccountServiceImpl.getInstance();
	}

	@AfterClass
	public static void destroy() {
		accountService = null;
	}

	@Test
	public void createAccountAndBalanceEnquiryTest1() throws Exception {
		long accountNumber = 12345L;
		double initialBalance = 1000D;
		Account account = accountService.createAccount(accountNumber, initialBalance);
		Assert.assertEquals(account, accountService.getAccount(accountNumber));
	}

	/*
	 * @Test public void createAccountAndBalanceEnquiryTest2() throws
	 * DuplicateAccountIdException { long accountNumber = 12345L; double
	 * initialBalance = 1000D; accountService.createAccount(accountNumber,
	 * initialBalance); Assert.assertThrows(DuplicateAccountIdException.class, () ->
	 * accountService.createAccount(accountNumber, initialBalance)); }
	 */

	@Test
	public void withdrawMoneyTest1() throws Exception {
		long accountId = 2L;
		double balance = 1000D;
		double withdrawnAmount = 200D;

		accountService.createAccount(accountId, balance);
		accountService.withdraw(accountId, withdrawnAmount);
		Assert.assertEquals((balance - withdrawnAmount), accountService.balanceEnquiry(accountId), 0);
	}

	@Test
	public void testWithdraw_NoSuchAccountException() {
		Assert.assertThrows(NoSuchAccountFoundException.class, () -> accountService.withdraw(1001L, 1000D));
	}

	@Test
	public void testWithdraw_InsufficientBalanceException() throws DuplicateAccountIdException {
		long accountId = 3L;
		double balance = 1000D;
		double withdrawnAmount = 2000D;
		accountService.createAccount(accountId, balance);
		Assert.assertThrows(InsufficientFundsException.class,
				() -> accountService.withdraw(accountId, withdrawnAmount));
	}

	@Test
	public void testDeposit() throws DuplicateAccountIdException, NoSuchAccountFoundException {
		long accountId = 4L;
		double balance = 1000D;
		double depositedAmount = 200D;

		accountService.createAccount(accountId, balance);
		accountService.deposit(accountId, depositedAmount);
		Assert.assertEquals(balance + depositedAmount, accountService.balanceEnquiry(accountId), 0);
	}

	@Test
	public void testDeposit_NoSuchAccountException() {
		Assert.assertThrows(NoSuchAccountFoundException.class, () -> accountService.deposit(1002L, 1000D));
	}

}
