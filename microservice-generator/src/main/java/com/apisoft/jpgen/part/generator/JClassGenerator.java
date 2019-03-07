package com.apisoft.jpgen.part.generator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.apisoft.jpgen.part.AccessTypes;
import com.apisoft.jpgen.part.JClass;
import com.apisoft.jpgen.part.JInstanceVariable;
import com.apisoft.jpgen.part.JMethod;
import com.apisoft.jpgen.part.JavaDoc;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JClassGenerator implements JGenerator<JClass> {
	
	@Override
	public String generate(JClass cls) {
		
		StringBuilder sb = new StringBuilder();
		
		//1. add package
		sb.append(getPackageLine(cls.getPackageName()));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		
		//2. add imports
		if(!cls.getImports().isEmpty()) {
			
			for(String imp : cls.getImports()) {
				sb.append(getImportLine(imp));
				sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			}
			
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		}
		
		//3. prepare java doc
		String className = cls.getClassName();
		JavaDoc javaDoc = new JavaDoc(className);
		javaDoc.setSince(new Date());
		sb.append(new JavaDocGenerator().generate(javaDoc)
				.replaceAll(EscapeCharacters.R_TAP.escapeChar, EscapeCharacters.NEW_LINE.escapeChar));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		
		//4. add annotations
		cls.getAnnotations().forEach(ann -> {
			sb.append(new JAnnotationGenerator()
					.generate(ann)
					.replaceAll(EscapeCharacters.R_TAP.escapeChar, EscapeCharacters.NEW_LINE.escapeChar));
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		});
		
		//5. add class declaration line
		sb.append(getClassDeclaration(cls.getAccessType(), cls.isStaticClass(), cls.isFinalClass(), className, 
				cls.getParentClass(), cls.getInterfaces()));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		
		//6. instance variables
		addInstanceVariables(sb, cls.getInstanceVariables());
		
		//7. add methods
		addMethods(sb, cls.getMethods());
		
		//8. end of the class 
		sb.append(RIGHT_CURLY_BRACKET);
		
		return sb.toString();
	}
	
	/**
	 * Add instance variables
	 * 
	 * @param sb
	 * @param ivs
	 */
	private void addInstanceVariables(StringBuilder sb, List<JInstanceVariable> ivs) {
		
		if(ivs.isEmpty()) {
			return;
		}
		
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		JInstanceVariableGenerator ivGen = new JInstanceVariableGenerator();
		
		sb.append(ivs.stream()
			.map(iv -> ivGen.generate(iv))
			.collect(Collectors.joining(EscapeCharacters.NEW_LINE.escapeChar + EscapeCharacters.NEW_LINE.escapeChar)));
		
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
	}

	/**
	 * add class methods
	 * 
	 * @param sb
	 * @param methods
	 */
	private void addMethods(StringBuilder sb, List<JMethod> methods) {
		
		if(methods.isEmpty()) {
			return;
		}
		
		JMethodGenerator methodGen = new JMethodGenerator();
		
		methods.forEach(m -> {
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			sb.append(methodGen.generate(m));
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		});
	}

	/**
	 * Generate class file path
	 * 
	 * @return
	 */
	public String getClassFilePath(String packageName, String className) {
		return new StringBuilder(packageName.replaceAll(".", "/"))
				.append(className)
				.toString();
	}
	
	/**
	 * Generate package line
	 * 
	 * @return
	 */
	public String getPackageLine(String packageName) {
		return generateSimpleKeywordLine("package", packageName);
	}

	/**
	 * 
	 * @param import
	 * @return
	 */
	public String getImportLine(String imp) {
		return generateSimpleKeywordLine("import", imp);
	}
	
	/**
	 * 
	 * @param accessType
	 * @param staticClass
	 * @param finalClass
	 * @param className
	 * @param parentClass
	 * @param interfaces
	 * @return
	 */
	public StringBuilder getClassDeclaration(AccessTypes accessType, boolean staticClass, boolean finalClass, 
			String className, String parentClass, List<String> interfaces) {
		
		StringBuilder sb = new StringBuilder();
		
		if(accessType == null) {
			accessType = AccessTypes.PUBLIC;
		}
		
		sb.append(accessType.accessType);
		sb.append(SPACE);
		
		if(staticClass) {
			sb.append(Keywords.STATIC.keyword);
			sb.append(SPACE);
		}
		
		if(finalClass) {
			sb.append(Keywords.FINAL.keyword);
			sb.append(SPACE);
		}
		
		sb.append(Keywords.CLASS.keyword);
		sb.append(SPACE);
		sb.append(className);
		sb.append(SPACE);
		
		//extends parent class
		if(StringUtils.isNotEmpty(parentClass)) {
			sb.append(Keywords.EXTENDS.keyword);
			sb.append(SPACE);
			sb.append(parentClass);
			sb.append(SPACE);
		}
		
		//implements interfaces
		if(!interfaces.isEmpty()) {
			
			sb.append(Keywords.IMPLEMENTS.keyword);
			sb.append(SPACE);
			
			Iterator<String> itr = interfaces.iterator();
			
			while(itr.hasNext()) {
				sb.append(itr.next());
				if(itr.hasNext()) {
					sb.append(COMMA);
				}
			}
			
			sb.append(SPACE);
		}
		
		sb.append(LEFT_CURLY_BRACKET);
		
		return sb;
	}
	
	/**
	 * 
	 * @param keyword
	 * @param value
	 * @return
	 */
	protected String generateSimpleKeywordLine(String keyword, String value) {
		return new StringBuilder(keyword)
				.append(SPACE)
				.append(value)
				.append(SEMI_COLON)
				.toString();
	}
}
