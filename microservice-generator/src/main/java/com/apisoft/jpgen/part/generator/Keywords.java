package com.apisoft.jpgen.part.generator;

/**
 * Java keywords
 * 
 * @author Salah Abu Msameh
 */
public enum Keywords {

	PACKAGE("package"),
	IMPORT("import"),
	CLASS("class"),
	EXTENDS("extends"),
	IMPLEMENTS("implements"),
	VOID("void"),
	STATIC("static"),
	FINAL("final");
	
	public String keyword;

	/**
	 * 
	 * @param keyword
	 */
	private Keywords(String keyword) {
		this.keyword = keyword;
	}
}
