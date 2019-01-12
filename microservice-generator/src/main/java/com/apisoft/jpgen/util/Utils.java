package com.apisoft.jpgen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

/**
 * 
 * @author Salah Abu Msameh
 */
public class Utils {
	
	/**
	 * 
	 * @param formatted date as string using default format dd/MM/yyyy
	 */
	public static String format(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String toClassName(String value) {
		
		if(StringUtils.isEmpty(value)) {
			return null;
		}
		
		return WordUtils.capitalize(value.replaceAll("-", " ").replaceAll("_", " "))
				.replaceAll(" ", "");
	}
}
