/**
 *
 */
package com.rts.sandbox.aspectj;

import java.math.BigDecimal;

/**
 * Do math with Money
 * 
 * @author Brian Chapman
 * 
 */
public class MoneyMath {

	private Money money;

	public static MoneyMath with(Money money) {
		return createMoneyMath(money);
	}

	public MoneyMath(Money money) {
		this.money = money;
	}

	public MoneyMath add(Money add) {
		Money result = createMoney(money.amount().add(add.amount()));
		return createMoneyMath(result);
	}

	public MoneyMath subtract(Money subtract) {
		Money result = createMoney(money.amount().subtract(subtract.amount()));
		return createMoneyMath(result);
	}

	public Boolean lessThan(Money other) {
		return money.amount().doubleValue() < other.amount().doubleValue();
	}

	public Money result() {
		return money;
	}

	private static MoneyMath createMoneyMath(Money money) {
		return new MoneyMath(money);
	}

	private Money createMoney(BigDecimal amount) {
		return new Money(amount);
	}
}
