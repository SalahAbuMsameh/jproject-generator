package com.apisoft.jpgen.part.generator;

import java.util.Iterator;
import java.util.Map;

import com.apisoft.jpgen.part.JMethod;
import com.apisoft.jpgen.part.JavaDoc;

/**
 * JMethod type generator 
 * 
 * @author Salah Abu Msameh
 */
public class JMethodGenerator implements JGenerator<JMethod> {
	
	@Override
	public String generate(JMethod method) {
		
		StringBuilder sb = new StringBuilder();
		JavaDoc javaDoc = new JavaDoc(method.getMethodName());
		
		//1. add method annotations
		method.getAnnotations().forEach(ann -> {
			sb.append(TAP);
			sb.append(new JAnnotationGenerator().generate(ann));
		});
		
		//2. add method declaration
		addMethodDeclaration(method, sb, javaDoc);
		
		return new StringBuilder(EscapeCharacters.TAP.escapeChar)
				.append(new JavaDocGenerator().generate(javaDoc))
				.append(sb)
				.toString();
	}
	
	/**
	 * 
	 * @param methodStr
	 * @param javaDoc
	 */
	private void addMethodDeclaration(JMethod method, StringBuilder methodStr, JavaDoc javaDoc) {
		
		methodStr.append(EscapeCharacters.R_TAP.escapeChar);
		methodStr.append(method.getAccessType().accessType);
		methodStr.append(SPACE);
		
		if(method.isStaticMethod()) {
			methodStr.append(Keywords.STATIC.keyword);
			methodStr.append(SPACE);
		}
		
		String returnType = method.getReturnType();
		
		if(returnType != null) {
			methodStr.append(returnType);
			javaDoc.setReturnType(true);
		}
		else {
			methodStr.append(Keywords.VOID.keyword);
		}
		
		methodStr.append(SPACE);
		methodStr.append(method.getMethodName());
		methodStr.append(LEFT_BRACKET);
		
		//add parameters
		Map<String, String> params = method.getParameters();
		Iterator<String> itr = params.keySet().iterator();
		
		while(itr.hasNext()) {
			
			String paramName = itr.next();
			
			methodStr.append(params.get(paramName));
			methodStr.append(SPACE);
			methodStr.append(paramName);
			
			
			if(itr.hasNext()) {
				methodStr.append(COMMA);
			}
		}
		
		methodStr.append(RIGHT_BRACKET);
		methodStr.append(SPACE);
		methodStr.append(LEFT_CURLY_BRACKET);
		
		String imp = method.getImplementation();
		
		if(imp != null && imp.length() > 0) {
			methodStr.append(EscapeCharacters.NEW_LINE.escapeChar);
			methodStr.append(imp);
			methodStr.append(EscapeCharacters.R_TAP.escapeChar).append(RIGHT_CURLY_BRACKET);
		}
		else {
			methodStr.append(RIGHT_CURLY_BRACKET);
		}
	}
}
