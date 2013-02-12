/**
 *
 */
package com.rts.sandbox.aspectj;

/**
 * A Bank where you store money.
 * 
 * @author Brian Chapman
 * 
 */
public interface Bank {

	public void transfer(Account accountFrom, Account accountTo, Money amount);

	public void register(Account account, Money initialAmount);

	public Money balance(Account account);

}
