/**
 *
 */
package com.rts.sandbox.aspectj;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Brian Chapman
 * 
 */
public abstract class SecureBankUnitTest {

	Money initialAmount1;
	Money initialAmount2;
	Money toTransfer;
	SecureBank bank;
	Account account1;
	Account account2;

	@Before
	public void setUp() {
		initialAmount1 = new Money(1000);
		initialAmount2 = new Money(100);
		toTransfer = new Money(500);
		bank = getBank();
		account1 = new StandardAccount("account1");
		account2 = new StandardAccount("account2");
		bank.register(account1, initialAmount1);
		bank.register(account2, initialAmount2);
	}

	/**
	 * The use of aspects should automatically add money into account1 to protect from an overdraft,
	 * thus preventing the {@link IllegalStateException} from being thrown.
	 */
	@Test
	public void testTransfer() {
		bank.transfer(account2, account1, toTransfer);
	}

	/**
	 * Get account balances.
	 */
	@Test
	public void getBalances() {
		List<Account> accounts = Arrays.asList(account1, account2);
		Map<Account, Money> balances = bank.balance(accounts);
		assertEquals(2, balances.size());
	}

	public abstract SecureBank getBank();
}
