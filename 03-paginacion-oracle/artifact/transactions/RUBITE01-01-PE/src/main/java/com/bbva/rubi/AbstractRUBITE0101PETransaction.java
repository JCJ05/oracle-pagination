package com.bbva.rubi;

import com.bbva.elara.transaction.AbstractTransaction;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractRUBITE0101PETransaction extends AbstractTransaction {

	public AbstractRUBITE0101PETransaction(){
	}


	/**
	 * Set value for String output parameter hash
	 */
	protected void setHash(final String field){
		this.addParameter("hash", field);
	}
}
