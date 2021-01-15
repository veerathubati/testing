package com.revolut.bankaccount.model;

public class Account {

	private long accoutNumber;
	private double balance;
	private String customerName;
	private String customerAddress;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	private final Object lock = new Object();

	public Account(long accoutNumber, double initialBalance) {
		this.accoutNumber = accoutNumber;
		this.balance = initialBalance;
	}

	public long getAccountNumber() {
		return accoutNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getAccoutNumber() {
		return accoutNumber;
	}

	public void setAccoutNumber(long accoutNumber) {
		this.accoutNumber = accoutNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Account account = (Account) o;
		return accoutNumber == account.accoutNumber && Double.compare(account.balance, balance) == 0;
	}
}
