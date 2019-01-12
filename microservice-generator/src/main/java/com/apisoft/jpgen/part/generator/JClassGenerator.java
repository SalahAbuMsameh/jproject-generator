package com.apisoft.jpgen.part.generator;

import java.io.File;
import java.util.Date;

import com.apisoft.jpgen.part.JClass;
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
		
		//4. add annotations
		cls.getAnnotations().forEach(ann -> {
			sb.append(new JAnnotationGenerator()
					.generate(ann)
					.replaceAll(EscapeCharacters.R_TAP.escapeChar, EscapeCharacters.NEW_LINE.escapeChar));
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		});
		
		//5. add class declaration line
		sb.append(getClassDeclaration(className));
		
		//6. instance variables
		if(!cls.getInstanceVariables().isEmpty()) {
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			cls.getInstanceVariables().forEach(iv -> sb.append(new InstanceVariableGenerator().generate(iv)));
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		}
		
		//6. add methods
		if(!cls.getMethods().isEmpty()) {
			cls.getMethods().forEach(m -> sb.append(new JMethodGenerator().generate(m)));
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		}
		
		//7. end of the class 
		sb.append(RIGHT_CURLY_BRACKET);
		
		return sb.toString();
	}

	/**
	 * Generate class file path
	 * 
	 * @return
	 */
	public String getClassFilePath(String packageName, String className) {
		return new StringBuilder(packageName.replaceAll(".", File.pathSeparator))
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
	 * @param className
	 * @return
	 */
	public String getClassDeclaration(String className) {
		return new StringBuilder("public class ")
				.append(className)
				.append(SPACE).append(LEFT_CURLY_BRACKET)
				.toString();
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
