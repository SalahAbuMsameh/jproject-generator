package com.apisoft.jpgen.part.generator;

import com.apisoft.jpgen.part.JPart;

/**
 * 
 * @author Salah Abu Msameh
 */
public interface JGenerator<J extends JPart> {

	static final String SPACE = " ";
	static final String AT = "@";
	static final String EQUAL = " = ";
	static final String COMMA = ", ";
	static final String LEFT_BRACKET = "(";
	static final String RIGHT_BRACKET = ")";
	static final String LEFT_CURLY_BRACKET = "{";
	static final String RIGHT_CURLY_BRACKET = "}";
	static final String DOUBLE_QUOTE = "\"";
	static final String SEMI_COLON = ";";
	static final String LEFT_ANGLE_BRACKET = "<";
	static final String RIGHT_ANGLE_BRACKET = ">";
	static final String FORWARD_SPASH = "/";
	
	static final String TWO_TAPS = EscapeCharacters.TAP.escapeChar + EscapeCharacters.TAP.escapeChar;
	static final String R_TWO_TAPS = EscapeCharacters.R_TAP.escapeChar + EscapeCharacters.TAP.escapeChar;
	
	/**
	 * Generates the given project part
	 * 
	 * @param jProjectPart
	 * @return
	 */
	public String generate(J jProjectPart);
}
