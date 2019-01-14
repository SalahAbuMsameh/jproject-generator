package com.apisoft.jpgen.part;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JInstanceVariable implements JPart {

	private AccessTypes accessType;
	private String type;
	private boolean staticVariable;
	private boolean finalVariable;
	private String variableName;
	private boolean setterAndGetter;
	private List<JAnnotation> annotations = new LinkedList<JAnnotation>();
	private String initialization;
	
	/**
	 * 
	 * @param accessType
	 * @param type
	 * @param variableName
	 */
	public JInstanceVariable(AccessTypes accessType, String type, String variableName) {
		this(accessType, type, variableName, false, false);
	}
	
	/**
	 * 
	 * @param accessType
	 * @param type
	 * @param variableName
	 * @param staticVariable
	 */
	public JInstanceVariable(AccessTypes accessType, String type, String variableName, boolean staticVariable) {
		this(accessType, type, variableName, staticVariable, false);
	}
	
	/**
	 * 
	 * @param accessType
	 * @param type
	 * @param variableName
	 * @param staticVariable
	 * @param finalVariable
	 */
	public JInstanceVariable(AccessTypes accessType, String type, String variableName, boolean staticVariable, boolean finalVariable) {
		this.accessType = accessType;
		this.type = type;
		this.variableName = variableName;
		this.staticVariable = staticVariable;
		this.finalVariable = finalVariable;
	}
	
	public String getAccessType() {
		return accessType.accessType;
	}
	
	public void setAccessType(AccessTypes accessType) {
		this.accessType = accessType;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public List<JAnnotation> getAnnotations() {
		return annotations;
	}
	
	public void addAnnotation(JAnnotation annotation) {
		annotations.add(annotation);
	}
	
	public boolean hasSetterAndGetter() {
		return setterAndGetter;
	}
	
	public String getInitialization() {
		return initialization;
	}
	
	public void setInitialization(String initialization) {
		this.initialization = initialization;
	}
	
	public boolean isStaticVariable() {
		return staticVariable;
	}
	
	public boolean isFinalVariable() {
		return finalVariable;
	}
}
