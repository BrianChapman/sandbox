/**
 *
 */
package com.rts.sandbox.aspectj;

import java.util.Date;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Record all transactions for auditing purposes.
 * 
 * @author Brian Chapman
 * 
 */
@Aspect
public class TransactionsAspect {

	@Pointcut("execution(* com.rts.sandbox.aspectj.Bank.transfer(..))")
	public void transfer() {
	}

	@Before("transfer() && args(from, to, amount) && this(bank)")
	public void overdraftProtection(Account from, Account to, Money amount, SecureBank bank) {
		Money fromBalance = bank.balance(from);
		if (MoneyMath.with(fromBalance).lessThan(amount)) {
			Money amountToTransfer = MoneyMath.with(amount).subtract(fromBalance).result();
			bank.transfer(bank.getOverdraftAccount(), from, amountToTransfer);
		}
	}

	@AfterReturning("transfer() && args(from, to, amount) && this(bank)")
	public void recordTransaction(Account from, Account to, Money amount, Bank bank) {
		System.out.println(new Date().toString() + " Transfer from account " + from.name() + " to account " + to.name()
				+ " of amount " + amount.formatted() + " succeeded. ");
		System.out.println("   Account Balance: " + from.name() + ", " + bank.balance(from).formatted());
		System.out.println("   Account Balance: " + to.name() + ", " + bank.balance(to).formatted());
	}
}
