package com.apisoft.jpgen.part;

/**
 * Java key types 
 * 
 * @author Salah Abu Msameh
 */
public enum JavaTypes {
	
	STRING("String"), 
	INT("int");
	
	public String type;

	/**
	 * 
	 * @param type
	 */
	private JavaTypes(String type) {
		this.type = type;
	}
}
