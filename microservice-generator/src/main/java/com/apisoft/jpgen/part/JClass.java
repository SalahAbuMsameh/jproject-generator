package com.apisoft.jpgen.part;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JClass implements JPart {

	private String className;
	private String packageName;
	private String parentClass;
	
	private List<String> imports = new LinkedList<String>();
	private List<String> interfaces = new LinkedList<String>();
	private List<JAnnotation> annotations = new LinkedList<JAnnotation>();
	private List<JInstanceVariable> instanceVariables = new LinkedList<JInstanceVariable>();
	private List<JMethod> methods = new LinkedList<JMethod>();
	
	/**
	 * 
	 * @param packageName
	 * @param className
	 */
	public JClass(String packageName, String className) {
		this.packageName = packageName;
		this.className = className;
	}

	/**
	 * builder class
	 */
	public static class ClassBuilder {
		
		private JClass c;
		
		/**
		 * create a new class with the given name
		 * 
		 * @param packageName
		 * @param className
		 * @return
		 */
		public ClassBuilder(String packageName, String className) {
			this.c = new JClass(packageName, className);
		}

		/**
		 * add import to the current class
		 * 
		 * @param importName
		 * @return
		 */
		public ClassBuilder importName(String importName) {
			this.c.getImports().add(importName);
			return this;
		}
		
		/**
		 * 
		 * @param importPackage
		 * @return
		 */
		public ClassBuilder annotation(JAnnotation annotation) {
			this.c.getAnnotations().add(annotation);
			return this;
		}
		
		/**
		 * 
		 * @return
		 */
		public JClass build() {
			return this.c;
		}
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParentClass() {
		return parentClass;
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public List<JAnnotation> getAnnotations() {
		return annotations;
	}
	
	public List<JInstanceVariable> getInstanceVariables() {
		return instanceVariables;
	}

	public void setInstanceVariables(List<JInstanceVariable> instanceVariables) {
		this.instanceVariables = instanceVariables;
	}

	public List<JMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<JMethod> methods) {
		this.methods = methods;
	}
}
