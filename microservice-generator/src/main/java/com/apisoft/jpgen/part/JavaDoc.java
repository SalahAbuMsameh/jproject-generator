package com.apisoft.jpgen.part;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JavaDoc implements JPart {

	private String name;
	private boolean returnType;
	private Date since;
	private List<String> parameters = new ArrayList<String>();
	private String throwType;
	
	/**
	 * 
	 * @param name
	 */
	public JavaDoc(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addParam(String param) {
		this.parameters.add(param);
	}
	
	public List<String> getParameters() {
		return parameters;
	}
	
	public boolean hasReturnType() {
		return returnType;
	}
	
	public void setReturnType(boolean returnType) {
		this.returnType = returnType;
	}
	
	public Date getSince() {
		return since;
	}
	
	public void setSince(Date since) {
		this.since = since;
	}

	public String getThrowType() {
		return throwType;
	}
	
	public void setThrowType(String throwType) {
		this.throwType = throwType;
	}
}
