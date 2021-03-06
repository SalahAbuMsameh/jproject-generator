package com.apisoft.jpgen.part;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JClass implements JPart {

	private AccessTypes accessType;
	private boolean staticClass;
	private boolean finalClass;
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
		 * add extend class
		 * 
		 * @param parentClass
		 * @return
		 */
		public ClassBuilder extend(String parentClass) {
			this.c.setParentClass(parentClass);
			return this;
		}
		
		/**
		 * add implement interface
		 * 
		 * @param interfaceName
		 * @return
		 */
		public ClassBuilder implement(String interfaceName) {
			this.c.getInterfaces().add(interfaceName);
			return this;
		}
		
		/**
		 * add instance variable to class
		 * 
		 * @param iv
		 * @return
		 */
		public ClassBuilder instanceVariable(JInstanceVariable iv) {
			this.c.getInstanceVariables().add(iv);
			return this;
		}
		
		/**
		 * add a method to the current class 
		 * 
		 * @param method
		 * @return
		 */
		public ClassBuilder method(JMethod method) {
			this.c.getMethods().add(method);
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
	
	public AccessTypes getAccessType() {
		return accessType;
	}
	
	public void setAccessType(AccessTypes accessType) {
		this.accessType = accessType;
	}
	
	public boolean isStaticClass() {
		return staticClass;
	}
	
	public void setStaticClass(boolean staticClass) {
		this.staticClass = staticClass;
	}
	
	public boolean isFinalClass() {
		return finalClass;
	}
	
	public void setFinalClass(boolean finalClass) {
		this.finalClass = finalClass;
	}
}
