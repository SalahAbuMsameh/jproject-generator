package com.apisoft.jpgen;

import java.util.List;

import com.apisoft.jpgen.part.pom.Dependency;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Salah Abu Msameh
 */
public class ProjectProperties {

	private String projectName;
	private String projectType;
	private String packageName;
	private String version;
	private String packaging;
	private String javaVersion;
	private String springbootVersion;
	private List<Dependency> dependancies;
	
	@JsonProperty("project-name")
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@JsonProperty("project-type")
	public String getProjectType() {
		return projectType;
	}
	
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@JsonProperty("package")
	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getPackaging() {
		return packaging;
	}
	
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	@JsonProperty("java-version")
	public String getJavaVersion() {
		return javaVersion;
	}
	
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}
	
	@JsonProperty("springboot-version")
	public String getSpringbootVersion() {
		return springbootVersion;
	}
	
	public void setSpringbootVersion(String springbootVersion) {
		this.springbootVersion = springbootVersion;
	}
	
	public List<Dependency> getDependancies() {
		return dependancies;
	}
	
	public void setDependancies(List<Dependency> dependancies) {
		this.dependancies = dependancies;
	}
}
