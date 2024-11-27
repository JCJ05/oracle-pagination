package com.bbva.rubi.lib.re01.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.rubi.lib.re01.RUBIRE01;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class RUBIRE01Abstract extends AbstractLibrary implements RUBIRE01 {

	protected ApplicationConfigurationService applicationConfigurationService;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

}