package com.apisoft.jpgen.type.assembler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.apisoft.jpgen.part.AccessTypes;
import com.apisoft.jpgen.part.JAnnotation;
import com.apisoft.jpgen.part.JClass;
import com.apisoft.jpgen.part.JInstanceVariable;
import com.apisoft.jpgen.part.JMethod;
import com.apisoft.jpgen.part.JavaTypes;
import com.apisoft.jpgen.part.generator.JClassGenerator;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JClassGeneratorTest {

	@Test
	public void testJClassGenerating() {
		
		JClass cls = new JClass("com.apisoft.test", "TestClass");
		JClassGenerator clsGenerator = new JClassGenerator();
		
		//1. test the simplest class assembling
		String clsStr = clsGenerator.generate(cls);
		
		assertNotNull(clsStr);
		assertTrue(clsStr.contains("package com.apisoft.test;"));
		assertTrue(clsStr.contains("public class TestClass {}"));
		
		//2. add imports
		cls.getImports().add("java.util.List");
		cls.getImports().add("java.util.LinkedList");
		clsStr = clsGenerator.generate(cls);
		
		assertTrue(clsStr.contains("import java.util.List;"));
		assertTrue(clsStr.contains("import java.util.LinkedList;"));
		
		//3. add annotations
		cls.getAnnotations().add(new JAnnotation("Service"));
		clsStr = clsGenerator.generate(cls);
		
		assertTrue(clsStr.contains("@Service"));
		
		//4.1 add instance variables
		cls.getInstanceVariables().add(new JInstanceVariable(AccessTypes.PRIVATE, JavaTypes.STRING.type, "name"));
		clsStr = clsGenerator.generate(cls);
		
		assertTrue(clsStr.contains("private String name;"));
		
		//4.2 add instance variables with annotation
		JInstanceVariable iv = new JInstanceVariable(AccessTypes.PRIVATE, "CustomersRepository", "custRepo");
		iv.getAnnotations().add(new JAnnotation("Repository"));
		cls.getInstanceVariables().add(iv);
		clsStr = clsGenerator.generate(cls);
		
		assertTrue(clsStr.contains("@Repository"));
		assertTrue(clsStr.contains("private CustomersRepository custRepo;"));
		
		//5. add method
		JMethod m = new JMethod(AccessTypes.PUBLIC, "findCustomerById");
		m.setReturnType("Customer");
		
		cls.getMethods().add(m);
		clsStr = clsGenerator.generate(cls);
		
		assertTrue(clsStr.contains("public Customer findCustomerById() {}"));
	}
}
