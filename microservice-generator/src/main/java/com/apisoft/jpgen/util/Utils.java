package com.apisoft.jpgen.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import com.apisoft.jpgen.JProjectGenException;

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
	
	/**
	 * 
	 * @param string
	 * @return
	 * @throws JProjectGenException 
	 */
	public static Path getResourceFolderPath(String folderName) throws JProjectGenException {
		
		URL url = Utils.class.getClassLoader().getResource(folderName);
		
		if(url == null) {
			throw new JProjectGenException("Unable to locate folder resourc : " + folderName);
		}
		
		try {
			return Paths.get(url.toURI());
		} catch (URISyntaxException e) {
			throw new JProjectGenException(e);
		}
	}
}
