package com.apisoft.jpgen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Salah Abu Msameh
 */
public class Utils {

	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	/**
	 * 
	 * @param formatted date as string using default format dd/MM/yyyy
	 */
	public static String format(Date date) {
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
	}
	
	/**
	 * 
	 * @param strValue
	 * @return
	 */
	public static boolean isNotEmpty(String strValue) {
		return strValue != null && strValue.length() > 0;
	}
}
