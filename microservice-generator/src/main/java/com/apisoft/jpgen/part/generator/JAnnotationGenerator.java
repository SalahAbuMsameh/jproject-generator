package com.apisoft.jpgen.part.generator;

import java.util.Iterator;

import com.apisoft.jpgen.part.JAnnotation;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JAnnotationGenerator implements JGenerator<JAnnotation> {
	
	@Override
	public String generate(JAnnotation annotation) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(EscapeCharacters.R_TAP.escapeChar);
		sb.append(AT);
		sb.append(annotation.getAnnotationName());
		
		String defaulValue = annotation.getDefaultValue();
		
		if(defaulValue != null) {
			
			sb.append(LEFT_BRACKET);
			
			if(annotation.isString()) {
				sb.append(DOUBLE_QUOTE);
				sb.append(defaulValue);
				sb.append(DOUBLE_QUOTE);
			}
			else {
				sb.append(defaulValue);
			}
			
			sb.append(RIGHT_BRACKET);
		}
		else if(!annotation.getAttributes().isEmpty()) {
			
			sb.append(LEFT_BRACKET);
			Iterator<JAnnotation.Attribute> itr = annotation.getAttributes().iterator();
			
			while(itr.hasNext()) {
				
				JAnnotation.Attribute att = itr.next();
				
				sb.append(att.getAttributeName());
				sb.append(EQUAL);
				
				if(att.isString()) {
					sb.append(DOUBLE_QUOTE);
					sb.append(att.getAttributeValue());
					sb.append(DOUBLE_QUOTE);
				}
				else {
					sb.append(att.getAttributeValue());
				}
				
				if(itr.hasNext()) {
					sb.append(COMMA);
				}
			}
			
			sb.append(RIGHT_BRACKET);
		}
		
		return sb.toString();
	}
}
