package com.apisoft.jpgen.project.mvn;

import java.util.List;

import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.project.JProjectDefault;

/**
 * 
 * @author Salah Abu Msameh
 */
public class MavenProject extends JProjectDefault {

	protected List<Dependency> dependencies;
	
	/**
	 * 
	 * @param properties
	 */
	public MavenProject(ProjectProperties properties) {
		super(properties);
	}
}
