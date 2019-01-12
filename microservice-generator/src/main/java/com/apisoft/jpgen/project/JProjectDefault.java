package com.apisoft.jpgen.project;

import java.util.List;

import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.part.JClass;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JProjectDefault implements JProject {

	protected ProjectProperties properties;
	protected List<JClass> classes;
	
	/**
	 * 
	 * @param properties
	 */
	public JProjectDefault(ProjectProperties properties) {
		this.properties = properties;
	}

	@Override
	public void prepare() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public void generate() {
		throw new UnsupportedOperationException("not yet implemented");
	}
	
	public List<JClass> getClasses() {
		return classes;
	}
	
	public void setClasses(List<JClass> classes) {
		this.classes = classes;
	}
}
