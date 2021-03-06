package com.apisoft.jpgen.part;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JMethod implements JPart {

	private String methodName;
	private boolean staticMethod;
	private boolean finalMethod;
	private List<JAnnotation> annotations = new ArrayList<JAnnotation>();
	private AccessTypes accessType;
	private String returnType;
	private Map<String, String> parameters = new LinkedHashMap<String, String>();
	
	private boolean addDocs = true;
	private String implementation = "";
	
	/**
	 * 
	 * @param accessType
	 * @param methodName
	 */
	public JMethod(AccessTypes accessType, String methodName) {
		this(accessType, false, methodName);
	}
	
	/**
	 * 
	 * @param accessType
	 * @param staticMethod
	 * @param methodName
	 */
	public JMethod(AccessTypes accessType, boolean staticMethod, String methodName) {
		this.accessType = accessType;
		this.staticMethod = staticMethod;
		this.methodName = methodName;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public boolean isStaticMethod() {
		return staticMethod;
	}
	
	public void setFinalMethod(boolean finalMethod) {
		this.finalMethod = finalMethod;
	}
	
	public boolean isFinalMethod() {
		return finalMethod;
	}
	
	public List<JAnnotation> getAnnotations() {
		return annotations;
	}
	
	public void addAnnotation(JAnnotation annotation) {
		this.annotations.add(annotation);
	}
	
	public AccessTypes getAccessType() {
		return accessType;
	}
	
	public void setAccessType(AccessTypes accessType) {
		this.accessType = accessType;
	}
	
	public String getReturnType() {
		return returnType;
	}	
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}
	
	public void addParameter(String paramName, String paramType) {
		this.parameters.put(paramName, paramType);
	}
	
	public String getImplementation() {
		return implementation;
	}
	
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	
	public boolean isAddDocs() {
		return addDocs;
	}
	
	public void setAddDocs(boolean addDocs) {
		this.addDocs = addDocs;
	}
}
