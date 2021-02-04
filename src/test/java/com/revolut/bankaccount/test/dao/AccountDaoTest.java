package com.revolut.bankaccount.test.dao;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revolut.bankaccount.dao.AccountDao;
import com.revolut.bankaccount.daoImpl.AccountDaoImpl;
import com.revolut.bankaccount.exception.DuplicateAccountIdException;
import com.revolut.bankaccount.exception.InsufficientFundsException;
import com.revolut.bankaccount.exception.NoSuchAccountFoundException;

public class AccountDaoTest {

	private static AccountDao accountDao;

	@BeforeClass
	public static void setUp() {
		accountDao = AccountDaoImpl.getInstance();
	}

	@AfterClass
	public static void destroy() {
		accountDao = null;
	}

	@Test
	public void withdrawMoneyTest1() throws Exception {
		long accountNumber = 1234567L;
		double amount = 5000D;
		double amountToBeWithdrawal = 2000D;
		accountDao.createAccount(accountNumber, amount);
		accountDao.withdraw(accountNumber, amountToBeWithdrawal);
		Assert.assertEquals((amount - amountToBeWithdrawal), accountDao.balanceEnquiry(accountNumber), 0);
	}

	@Test
	public void withdrawMoneyTest2() throws InsufficientFundsException, DuplicateAccountIdException {
		long accountNumber = 9999999L;
		double amount = 70D;
		double amountToBeWithdrawal = 1000D;
		accountDao.createAccount(accountNumber, amount);
		Assert.assertThrows(InsufficientFundsException.class,
				() -> accountDao.withdraw(accountNumber, amountToBeWithdrawal));
	}

	/*
	 * @Test public void depositAmountTest1() throws Exception { long accountNumber
	 * = 1234567L; double amount = 5000D; double amountToBeDeposit = 2000D;
	 * accountDao.deposit(accountNumber, amountToBeDeposit);
	 * Assert.assertEquals((amount + amountToBeDeposit),
	 * accountDao.balanceEnquiry(accountNumber), 0); }
	 */

	/*
	 * @Test public void depositAmountTest2() throws Exception { long accountNumber
	 * = 9999999L; double amountToBeDeposit = 1000D;
	 * Assert.assertThrows(NoSuchAccountFoundException.class, () ->
	 * accountDao.deposit(accountNumber, amountToBeDeposit)); }
	 */

	/*
	 * @Test public void balanceEnquiryTest1() throws Exception { long accountNumber
	 * = 9999999L; double balanceAmount = 5000D; Assert.assertEquals(balanceAmount,
	 * accountDao.balanceEnquiry(accountNumber), 0); }
	 */

}
