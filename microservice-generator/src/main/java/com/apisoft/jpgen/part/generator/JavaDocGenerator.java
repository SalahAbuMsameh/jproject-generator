package com.apisoft.jpgen.part.generator;

import com.apisoft.jpgen.part.JavaDoc;
import com.apisoft.jpgen.util.Utils;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JavaDocGenerator implements JGenerator<JavaDoc> {
	
	private static final String SINCE = "since";
	
	@Override
	public String generate(JavaDoc javaDoc) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(EscapeCharacters.R_TAP.escapeChar).append("/**");
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(" *").append(SPACE).append(javaDoc.getName());
		
		if(javaDoc.getSince() != null) {
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(" *");
			sb.append(EscapeCharacters.R_TAP.escapeChar);
			sb.append(" *").append(SPACE).append(AT).append(SINCE);
			sb.append(SPACE).append(Utils.format(javaDoc.getSince()));
		}
		
		//end
		sb.append(EscapeCharacters.R_TAP.escapeChar).append("/*");
		
		return sb.toString();
	}
}
