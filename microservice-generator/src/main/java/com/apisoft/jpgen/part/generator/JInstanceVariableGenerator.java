package com.apisoft.jpgen.part.generator;

import org.apache.commons.lang3.StringUtils;

import com.apisoft.jpgen.part.JInstanceVariable;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JInstanceVariableGenerator implements JGenerator<JInstanceVariable> {
	
	@Override
	public String generate(JInstanceVariable iv) {
		
		StringBuilder sb = new StringBuilder();
		
		//add annotations
		if(!iv.getAnnotations().isEmpty()) {
			iv.getAnnotations().forEach(ann -> {
				sb.append(EscapeCharacters.TAP.escapeChar);
				sb.append(new JAnnotationGenerator().generate(ann));
			});
		}
		
		sb.append(EscapeCharacters.R_TAP.escapeChar);
		sb.append(iv.getAccessType());
		sb.append(SPACE);
		
		if(iv.isStaticVariable()) {
			sb.append(Keywords.STATIC.keyword);
			sb.append(SPACE);
		}
		
		if(iv.isFinalVariable()) {
			sb.append(Keywords.FINAL.keyword);
			sb.append(SPACE);
		}
		
		sb.append(iv.getType());
		sb.append(SPACE);
		sb.append(iv.getVariableName());
		
		//append initialization 
		String initialization = iv.getInitialization();
		if(StringUtils.isNotEmpty(initialization)) {
			sb.append(EQUAL);
			sb.append(initialization);
		}
		
		sb.append(SEMI_COLON);
		
		return sb.toString();
	}
}
