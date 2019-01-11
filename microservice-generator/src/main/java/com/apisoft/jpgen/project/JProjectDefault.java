package com.apisoft.jpgen.project;

import com.apisoft.jpgen.ProjectProperties;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JProjectDefault implements JProject {

	protected ProjectProperties properties;
	
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
}
