package com.apisoft.jpgen.project;

import java.util.ArrayList;
import java.util.List;

import com.apisoft.jpgen.JProjectGenException;
import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.io.IOUtils;
import com.apisoft.jpgen.part.JClass;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JProjectDefault implements JProject {

	protected ProjectProperties properties;
	protected List<JClass> classes = new ArrayList<JClass>();
	
	/**
	 * 
	 * @param properties
	 */
	public JProjectDefault(ProjectProperties properties) {
		this.properties = properties;
	}

	@Override
	public void prepare() throws JProjectGenException {
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public void generate() throws JProjectGenException {
		throw new UnsupportedOperationException("not yet implemented");
	}
	
	public List<JClass> getClasses() {
		return classes;
	}
	
	public void setClasses(List<JClass> classes) {
		this.classes = classes;
	}
	
	@Override
	public void rollback() {
		try {
			IOUtils.deleteDir(properties.getProjectName());
		} catch (JProjectGenException e) {
			e.printStackTrace();
		}
	}
}
