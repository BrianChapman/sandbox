/**
 *
 */
package com.rts.sandbox.aspectj;

import java.util.Date;

import org.aspectj.lang.annotation.After;
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

	@After("transfer() && args(from, to, amount) && this(bank)")
	public void recordTransaction(Account from, Account to, Money amount, Bank bank) {
		recordTransaction("Transfer from account " + from.name() + " to account " + to.name() + " of amount "
				+ amount.formatted() + " succeeded. ");
		System.out.println("   Account Balance: " + from.name() + ", " + bank.balance(from).formatted());
		System.out.println("   Account Balance: " + to.name() + ", " + bank.balance(to).formatted());
	}

	@AfterReturning(pointcut = "execution(* com.rts.sandbox.aspectj.Bank.balance(Account)) && args(account)",
			returning = "balance")
	public void recordBalanceInquiry(Account account, Money balance) {
		recordTransaction(account.name() + " inquired about their balance of " + balance.formatted() + ".");
	}

	@After("execution(* com.rts.sandbox.aspectj.Bank.balance(java.util.List))")
	public void recordBalanceInquiry() {
		recordTransaction("Inquiry about many accounts just happened.");
	}

	private void recordTransaction(String message) {
		System.out.println(new Date().toString() + " " + message);
	}
}
