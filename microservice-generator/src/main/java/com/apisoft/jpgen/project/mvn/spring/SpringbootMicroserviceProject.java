package com.apisoft.jpgen.project.mvn.spring;

import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.part.JAnnotation;
import com.apisoft.jpgen.part.JClass;
import com.apisoft.jpgen.project.mvn.MavenProject;
import com.apisoft.jpgen.util.Utils;

/**
 * 
 * @author Salah Abu Msameh
 */
public class SpringbootMicroserviceProject extends MavenProject {
	
	private String defaultClassName;
	private JClass springbootAppClass;
	
	/**
	 * 
	 * @param properties
	 */
	public SpringbootMicroserviceProject(ProjectProperties properties) {
		super(properties);
	}
	
	@Override
	public void prepare() {
		
		String projectName = properties.getProjectName();
		defaultClassName = Utils.toClassName(projectName);
		String packageName = properties.getPackageName();
		
		springbootAppClass = new JClass.ClassBuilder(packageName, defaultClassName)
				.importLine("org.springframework.boot.SpringApplication")
				.importLine("org.springframework.boot.autoconfigure.SpringBootApplication")
				.annotation(new JAnnotation("SpringBootApplication"))
				.build();
		
		
	}
	
	@Override
	public void generate() {
		
	}
}
