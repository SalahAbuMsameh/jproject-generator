package com.apisoft.jpgen.project;

import com.apisoft.jpgen.JProjectGenException;

/**
 * 
 * @author Salah Abu Msameh
 */
public interface JProject {

	/**
	 * Prepares all project parts
	 */
	public void prepare() throws JProjectGenException;
	
	/**
	 * Generates project
	 */
	public void generate() throws JProjectGenException;
	
	/**
	 * Rollback the created files & folders in case not completed successfully
	 */
	public void rollback();
}
