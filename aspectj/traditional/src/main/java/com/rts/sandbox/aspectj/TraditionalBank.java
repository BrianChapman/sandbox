/**
 *
 */
package com.rts.sandbox.aspectj;

import java.util.List;
import java.util.Map;

/**
 * AspectJ is only configured to match classes in this project. Weaving of 3rd party jars isn't the
 * focus of this example.
 * 
 * We override the methods here so that AspectJ recognizes them and applies the advice. You
 * wouldn't, of course do this in real life, but for this example, I wanted to demonstrate a
 * straightforward use case without weaving.
 * 
 * @author Brian Chapman
 * 
 */
public class TraditionalBank extends SecureBank {

	/*
	 * (non-Javadoc)
	 * @see com.rts.sandbox.aspectj.SecureBank#transfer(com.rts.sandbox.aspectj.Account,
	 * com.rts.sandbox.aspectj.Account, com.rts.sandbox.aspectj.Money)
	 */
	@Override
	public void transfer(Account accountFrom, Account accountTo, Money amount) {
		// TODO Auto-generated method stub
		super.transfer(accountFrom, accountTo, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rts.sandbox.aspectj.SecureBank#balance(com.rts.sandbox.aspectj.Account)
	 */
	@Override
	public Money balance(Account account) {
		// TODO Auto-generated method stub
		return super.balance(account);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rts.sandbox.aspectj.SecureBank#balance(java.util.List)
	 */
	@Override
	public Map<Account, Money> balance(List<Account> accounts) {
		// TODO Auto-generated method stub
		return super.balance(accounts);
	}
}
