package com.apisoft.jpgen.part;

/**
 * 
 * @author Salah Abu Msameh
 */
public enum AccessTypes {

	PUBLIC("public"),
	PRIVATE("private"),
	PROTECTED("protected");
	
	public String accessType;
	
	/**
	 * 
	 * @param accessType
	 */
	private AccessTypes(String accessType) {
		this.accessType = accessType;
	}
}
