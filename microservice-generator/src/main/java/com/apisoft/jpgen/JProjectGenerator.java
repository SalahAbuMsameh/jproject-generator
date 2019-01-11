package com.apisoft.jpgen;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JProjectGenerator {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.err.println("No gen file found");
			System.exit(0);
		}
		
		System.out.println("Debug - generating ...");
	}
}
