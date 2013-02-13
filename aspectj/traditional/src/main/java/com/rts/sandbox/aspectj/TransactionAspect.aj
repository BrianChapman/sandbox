/**
 *
 */
package com.rts.sandbox.aspectj;

import java.util.Date;

/**
 * @author brian
 *
 */
public aspect TransactionAspect {

	pointcut transfer() : execution(* com.rts.sandbox.aspectj.Bank.transfer(..));

	before(Account from, Account to, Money amount, SecureBank bank): transfer() && args(from, to, amount) && this(bank) {
		Money fromBalance = bank.balance(from);
		if (MoneyMath.with(fromBalance).lessThan(amount)) {
			Money amountToTransfer = MoneyMath.with(amount).subtract(fromBalance).result();
			bank.transfer(bank.getOverdraftAccount(), from, amountToTransfer);
		}

	}

	after(Account from, Account to, Money amount, SecureBank bank) : transfer() && args(from, to, amount) && this(bank) {
		recordTransaction("Transfer from account " + from.name() + " to account " + to.name() + " of amount "
				+ amount.formatted() + " succeeded. ");
		System.out.println("   Account Balance: " + from.name() + ", " + bank.balance(from).formatted());
		System.out.println("   Account Balance: " + to.name() + ", " + bank.balance(to).formatted());
	}

	after(Account account) returning(Money balance): execution(* com.rts.sandbox.aspectj.Bank.balance(Account)) && args(account) {
		recordTransaction(account.name() + " inquired about their balance of " + balance.formatted() + ".");
	}

	after() : execution(* com.rts.sandbox.aspectj.Bank.balance(java.util.List)) {
		recordTransaction("Inquiry about many accounts just happened.");
	}

	public void recordBalanceInquiry() {
	}

	private void recordTransaction(String message) {
		System.out.println(new Date().toString() + " " + message);
	}
}
