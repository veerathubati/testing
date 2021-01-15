package com.revolut.bankaccount.test.utils;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.bankaccount.utils.ValidationUtils;

public class ValidationUtilsTest {

	public static ValidationUtils validationUtils;

	/*
	 * @Test public void validateIdTest1() throws Exception { String num =
	 * "123445675L"; long finalNum = validationUtils.validateId(num);
	 * Assert.assertEquals(Long.parseLong(num), finalNum, 0); }
	 */

	@Test
	public void validateInitialAmountTest1() throws Exception {
		String initialAmount = "50000D";
		double finalAmount = validationUtils.validateInitialAmount(initialAmount);
		Assert.assertEquals(Double.parseDouble(initialAmount), finalAmount, 0);
	}

}
