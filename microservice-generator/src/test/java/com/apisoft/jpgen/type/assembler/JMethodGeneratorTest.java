package com.apisoft.jpgen.type.assembler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.apisoft.jpgen.part.AccessTypes;
import com.apisoft.jpgen.part.JAnnotation;
import com.apisoft.jpgen.part.JMethod;
import com.apisoft.jpgen.part.JavaTypes;
import com.apisoft.jpgen.part.generator.JMethodGenerator;

/**
 * Method assembler test
 * 
 * @author Salah Abu Msameh
 */
public class JMethodGeneratorTest {
	
	@Test
	public void testJMethodGeneration() {
		
		JMethod method = new JMethod(AccessTypes.PUBLIC, "testMethod");	
		JMethodGenerator methodGen = new JMethodGenerator();
		
		//1. test the assembling generation result
		String methodStr = methodGen.generate(method);
		assertNotNull(methodStr);
		assertTrue(methodStr.contains("public void testMethod() {}"));
		
		//2. test return type
		method.setReturnType(JavaTypes.STRING.type);
		assertTrue(methodGen.generate(method).contains("	public String testMethod() {}"));
		
		//3.1 add annotation with default value
		method.addAnnotation(new JAnnotation("AnnWithDefaultValue", "defauleValue", true));
		assertTrue(methodGen.generate(method).contains("	@AnnWithDefaultValue(\"defauleValue\")"));
		
		//3.2 add annotations with attributes
		JAnnotation annWithAtts = new JAnnotation("AnnWithAttributes");
		
		annWithAtts.addAttribute(new JAnnotation.Attribute("attName1", "NonStringAttValue"));
		annWithAtts.addAttribute(new JAnnotation.Attribute("attName2", "StringAttValue", true));
		method.addAnnotation(annWithAtts);
		
		assertTrue(methodGen.generate(method)
				.contains("	@AnnWithAttributes(attName1 = NonStringAttValue, attName2 = \"StringAttValue\")"));
		
		//4. add parameters to the methods
		method.addParameter("title", JavaTypes.STRING.type);
		method.addParameter("name", JavaTypes.STRING.type);
		method.addParameter("age", JavaTypes.INT.type);
		
		assertTrue(methodGen.generate(method)
				.contains("	public String testMethod(String title, String name, int age) {}"));
	}
}
