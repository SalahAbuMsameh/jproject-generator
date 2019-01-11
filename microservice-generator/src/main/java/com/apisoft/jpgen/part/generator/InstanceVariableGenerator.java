package com.apisoft.jpgen.part.generator;

import com.apisoft.jpgen.part.JInstanceVariable;

/**
 * 
 * @author Salah Abu Msameh
 */
public class InstanceVariableGenerator implements JGenerator<JInstanceVariable> {
	
	@Override
	public String generate(JInstanceVariable iv) {
		
		StringBuilder sb = new StringBuilder();
		
		//1. add annotations
		iv.getAnnotations().forEach(ann -> sb.append(new JAnnotationGenerator().generate(ann)));
		
		sb.append(EscapeCharacters.R_TAP.escapeChar);
		sb.append(iv.getAccessType());
		sb.append(SPACE);
		sb.append(iv.getType());
		sb.append(SPACE);
		sb.append(iv.getVariableName());
		sb.append(SEMI_COLON);
		
		return sb.toString();
	}
}
