package com.rts.sandbox.aspectj;

/**
 *
 */

/**
 * Local version of unit tests.
 * 
 * @author Brian Chapman
 * 
 */
public class AnnotatedSecureBankUnitTest extends SecureBankUnitTest {

	/*
	 * (non-Javadoc)
	 * @see com.rts.sandbox.aspectj.SecureBankUnitTest#getBank()
	 */
	@Override
	public SecureBank getBank() {
		return new AnnotatedBank();
	}

}
