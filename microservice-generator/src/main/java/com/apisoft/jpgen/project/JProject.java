package com.apisoft.jpgen.project;

/**
 * 
 * @author Salah Abu Msameh
 */
public interface JProject {

	/**
	 * Prepares all project parts
	 */
	public void prepare();
	
	/**
	 * Generates project
	 */
	public void generate();
}
