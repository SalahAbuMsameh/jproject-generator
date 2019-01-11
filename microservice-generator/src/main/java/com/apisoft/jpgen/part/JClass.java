package com.apisoft.jpgen.part;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JClass implements JPart {

	private String packageName;
	private List<String> imports = new LinkedList<String>();
	private String className;
	private String parentClass;
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
		public ClassBuilder newClass(String packageName, String className) {
			this.c = new JClass(packageName, className);
			return this;
		}
		
		/**
		 * add imports to the current class
		 * 
		 * @param imports
		 * @return
		 */
		public ClassBuilder imports(List<String> imports) {
			this.c.setImports(imports);
			return this;
		}
		
		/**
		 * 
		 * @param importPackage
		 * @return
		 */
		public ClassBuilder addImport(String importPackage) {
			
			List<String> imports = this.c.getImports();
			
			if(imports == null) {
				imports = new ArrayList<String>();
				this.c.setImports(imports);
			}
			
			//add import
			imports.add(importPackage);
			
			return this;
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
