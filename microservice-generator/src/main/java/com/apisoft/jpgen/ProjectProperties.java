package com.apisoft.jpgen;

import java.util.List;

import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.part.pom.Plugin;
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
	private List<Plugin> plugins;
	
	@JsonProperty("project_name")
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@JsonProperty("project_type")
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
	
	@JsonProperty("project_version")
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
	
	@JsonProperty("java_version")
	public String getJavaVersion() {
		return javaVersion;
	}
	
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}
	
	@JsonProperty("springboot_version")
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
	
	public List<Plugin> getPlugins() {
		return plugins;
	}
	
	public void setPlugins(List<Plugin> plugins) {
		this.plugins = plugins;
	}
}
