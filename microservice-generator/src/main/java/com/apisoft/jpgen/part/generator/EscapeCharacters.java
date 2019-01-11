package com.apisoft.jpgen.part.generator;

/**
 * Escape characters
 * @author Salah Abu Msameh
 */
public enum EscapeCharacters {

	NEW_LINE("\r\n"),
	TAP("\t"),
	R_TAP("\r\t");
	
	public String escapeChar;
	
	/**
	 * 
	 * @param escapeChar
	 */
	private EscapeCharacters(String escapeChar) {
		this.escapeChar = escapeChar;
	}
}
