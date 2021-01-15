package com.revolut.bankaccount.constants;

public class Constants {

	public static final String ACCOUNT_NUMBER_ALREADY_EXISTS = "ACCOUNT_NUMBER_ALREADY_EXISTS:";
	public static final String NO_SUCH_ACCOUNT_FOUND = "NO_ACCOUNT_FOUND_FOR_THE_ACCOUNT_NUMBER :";
	public static final String INSUFFICIENT_FUNDS = "UNABLE_TO_PROCESS_INSUFFICIENT_FUNDS_IN_THE_ACCOUNT:";
	public static final Integer GENERIC_PORT_NUMBER = 4848;
	public final static String ACCOUNT_API_PATH = "http://localhost:4848/account/";
	public final static String TRANSACTION_API_PATH = "http://localhost:4848/transfer/";
	public static final String INVALID_ID = "INVALID_ID: ";
	public static final String INVALID_AMOUNT_TRANSFER = "INVALID_AMOUNT_TRANSFER: ";
	public static final String INVALID_INITIAL_BALANCE = "INVALID_INITIAL_BALANCE: ";
}
