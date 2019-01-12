package com.apisoft.jpgen.project.mvn.spring;

import java.nio.file.Paths;

import com.apisoft.jpgen.JProjectGenException;
import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.io.IOUtils;
import com.apisoft.jpgen.part.JAnnotation;
import com.apisoft.jpgen.part.JClass;
import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.part.pom.Plugin;
import com.apisoft.jpgen.part.pom.PomFile;
import com.apisoft.jpgen.part.pom.PomFile.PomBuild;
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
	public void prepare() throws JProjectGenException {
		
		String projectName = properties.getProjectName();
		defaultClassName = Utils.toClassName(projectName);
		String packageName = properties.getPackageName();
		
		//springboot application class
		springbootAppClass = new JClass.ClassBuilder(packageName, defaultClassName)
				.importLine("org.springframework.boot.SpringApplication")
				.importLine("org.springframework.boot.autoconfigure.SpringBootApplication")
				.annotation(new JAnnotation("SpringBootApplication"))
				.build();
		
		//spring boot parent pom
		PomBuild pomBuild = new PomBuild();
		pomBuild.setBuildName(projectName);
		pomBuild.getPlugins().add(new Plugin("org.springframework.boot", "spring-boot-maven-plugin"));

		pomFile = new PomFile.PomFileBuilder(packageName, projectName)
				.parent(new Dependency("org.springframework.boot", "spring-boot-starter-parent", properties.getSpringbootVersion()))
				.version(properties.getVersion())
				.property("java.version", properties.getJavaVersion())
				.pomBuild(pomBuild)
				.build();
	}
	
	@Override
	public void generate() throws JProjectGenException {
		
		String projectName = properties.getProjectName();
		
		//1. create root project directory
		IOUtils.createDir(projectName);
		
		//2. copy maven wrapper folder
		IOUtils.copyDir(Utils.getResourceFolderPath(".mvn"), Paths.get(projectName));
	}
}
