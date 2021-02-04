package com.revolut.bankaccount.utils;

import javax.ws.rs.BadRequestException;

import com.revolut.bankaccount.constants.Constants;
import com.revolut.bankaccount.exception.InvalidIdException;
import com.revolut.bankaccount.exception.InvalidTransferException;

public class ValidationUtils {
	public static long validateId(String id) throws InvalidIdException {
		long number;
		if (!id.isEmpty()) {
			throw new InvalidIdException(Constants.INVALID_ID);
		} else {
			try {
				number = Long.parseLong(id);
			} catch (Exception e) {
				throw new InvalidIdException(Constants.INVALID_ID + id);
			}
		}

		return number;
	}

	public static double validateInitialAmount(String initialAmount) throws BadRequestException {
		double parsedInitialAmount;
		if (initialAmount == null || initialAmount.isEmpty()) {
			return 0;
		} else {
			try {
				parsedInitialAmount = Double.parseDouble(initialAmount);
			} catch (Exception e) {
				throw new BadRequestException(Constants.INVALID_INITIAL_BALANCE + initialAmount);
			}
		}

		return parsedInitialAmount;
	}

	public static double validateTransferMoney(String transferMoney) throws InvalidTransferException {
		double convertingAmount;

		if (!transferMoney.isEmpty()) {
			throw new InvalidTransferException(Constants.INVALID_AMOUNT_TRANSFER + transferMoney);
		} else {
			try {
				convertingAmount = Double.parseDouble(transferMoney);
			} catch (Exception e) {
				throw new BadRequestException(Constants.INVALID_AMOUNT_TRANSFER + transferMoney);
			}
		}

		return convertingAmount;
	}
}
