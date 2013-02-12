/**
 *
 */
package com.rts.sandbox.aspectj;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;

/**
 * Money baby!
 * 
 * @author Brian Chapman
 * 
 */
public class Money {

	private BigDecimal amount;
	private final Currency CURRENCY = Currency.getInstance("USD");

	public Money(BigDecimal amount) {
		this.amount = amount;
	}

	public Money(Integer amount) {
		this.amount = new BigDecimal(amount);
	}

	public BigDecimal amount() {
		return amount;
	}

	public String currency() {
		return currency();
	}

	public String formatted() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.setCurrency(CURRENCY);
		formatter.setParseIntegerOnly(true);
		return formatter.format(amount);
	}
}
