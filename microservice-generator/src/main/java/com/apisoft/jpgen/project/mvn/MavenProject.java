package com.apisoft.jpgen.project.mvn;

import java.util.List;

import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.part.pom.PomFile;
import com.apisoft.jpgen.project.JProjectDefault;

/**
 * 
 * @author Salah Abu Msameh
 */
public class MavenProject extends JProjectDefault {

	protected static final String MAIN_SOURCE = "/src/main/java/";
	protected static final String TEST_SOURCE = "/src/test/java/";
	
	protected List<Dependency> dependencies;
	protected PomFile pomFile;
	
	/**
	 * 
	 * @param properties
	 */
	public MavenProject(ProjectProperties properties) {
		super(properties);
	}
}
