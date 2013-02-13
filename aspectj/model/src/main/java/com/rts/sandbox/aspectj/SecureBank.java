/**
 *
 */
package com.rts.sandbox.aspectj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Chapman
 * 
 */
public class SecureBank implements Bank {

	private Map<Account, Money> accounts = new HashMap<Account, Money>();
	private Account overdraftAccount;

	public SecureBank() {
		this.overdraftAccount = new StandardAccount("OverdraftAccount");
		accounts.put(overdraftAccount, new Money(Integer.MAX_VALUE));
	}

	/*
	 * (non-Javadoc)
	 * @see com.rts.sandbox.aspectj.Bank#transfer(com.rts.sandbox.aspectj.Account,
	 * com.rts.sandbox.aspectj.Account, com.rts.sandbox.aspectj.Money)
	 */
	@Override
	public void transfer(Account accountFrom, Account accountTo, Money amount) {
		if (!accounts.containsKey(accountFrom) || !accounts.containsKey(accountTo)) {
			throw new IllegalArgumentException("Accounts must exist to transfer money!");
		}

		Money from = accounts.get(accountFrom);
		Money to = accounts.get(accountTo);
		Money fromAfter = MoneyMath.with(from).subtract(amount).result();
		Money toAfter = MoneyMath.with(to).add(amount).result();

		if (MoneyMath.with(fromAfter).lessThan(new Money(0))) {
			throw new IllegalStateException("Cannot withdraw more than you have!");
		}

		accounts.put(accountFrom, fromAfter);
		accounts.put(accountTo, toAfter);
	}

	/**
	 * @param account1
	 * @param money
	 */
	@Override
	public void register(Account account, Money money) {
		if (accounts.containsKey(account)) {
			throw new IllegalArgumentException("Already Registered: " + account.name() + ".");
		}
		accounts.put(account, money);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rts.sandbox.aspectj.Bank#balance(com.rts.sandbox.aspectj.Account)
	 */
	@Override
	public Money balance(Account account) {
		return accounts.get(account);
	}

	public Map<Account, Money> balance(List<Account> accounts) {
		Map<Account, Money> results = new HashMap<Account, Money>();
		for (Account account : accounts) {
			results.put(account, balance(account));
		}
		return results;
	}

	protected Account getOverdraftAccount() {
		return overdraftAccount;
	}
}
