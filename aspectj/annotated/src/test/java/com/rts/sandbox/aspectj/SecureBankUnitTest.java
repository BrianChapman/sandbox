/**
 *
 */
package com.rts.sandbox.aspectj;

import org.junit.Test;

/**
 * 
 * 
 * @author Brian Chapman
 * 
 */
public class SecureBankUnitTest {

	/**
	 * The use of aspects should automatically add money into account1 to protect from an overdraft,
	 * thus preventing the {@link IllegalStateException} from being thrown.
	 */
	@Test
	public void testTransfer() {
		Money initialAmount1 = new Money(1000);
		Money initialAmount2 = new Money(100);
		Money toTransfer = new Money(500);
		SecureBank bank = new SecureBank();
		Account account1 = new StandardAccount("account1");
		Account account2 = new StandardAccount("account2");
		bank.register(account1, initialAmount1);
		bank.register(account2, initialAmount2);

		bank.transfer(account2, account1, toTransfer);
	}
}
