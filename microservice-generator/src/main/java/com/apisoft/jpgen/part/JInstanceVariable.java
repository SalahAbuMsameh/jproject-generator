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
	private String variableName;
	private boolean setterAndGetter;
	private List<JAnnotation> annotations = new LinkedList<JAnnotation>();
	
	/**
	 * 
	 * @param accessType
	 * @param type
	 * @param variableName
	 */
	public JInstanceVariable(AccessTypes accessType, String type, String variableName) {
		this.accessType = accessType;
		this.type = type;
		this.variableName = variableName;
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
	
	public boolean hasSetterAndGetter() {
		return setterAndGetter;
	}
}
