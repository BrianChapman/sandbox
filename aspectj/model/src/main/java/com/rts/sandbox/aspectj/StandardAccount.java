/**
 *
 */
package com.rts.sandbox.aspectj;

/**
 * @author Brian Chapman
 * 
 */
public class StandardAccount implements Account {

	private String name;

	public StandardAccount(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see com.rts.sandbox.aspectj.Account#name()
	 */
	@Override
	public String name() {
		return this.name;
	}

}
