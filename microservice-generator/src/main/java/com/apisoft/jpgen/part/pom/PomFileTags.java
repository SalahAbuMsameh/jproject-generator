package com.apisoft.jpgen.part.pom;

/**
 * 
 * @author Salah Abu Msameh
 */
public enum PomFileTags {

	PARENT("parent"),
	GROUP_ID("groupId"),
	ARTIFACT_ID("artifactId"),
	VERSION("version"),
	PACKAGING("packaging"),
	NAME("name"),
	DESCRIPTION("description"),
	EXCLUSIONS("exclusions"),
	EXCLUSION("exclusion"),
	SCOPE("scope"),
	DEPENDENCIES("dependencies"),
	DEPENDENCY("dependency"),
	PROPERTIES("properties"),
	BUILD("build"),
	PLUGINS("plugins"),
	PLUGIN("plugin"),
	BUILD_FILAL_NAME("finalName"),
	CONFIGURATION("configuration"),
	SOURCE("source"),
	TARGET("target");
	
	public String tag;
	
	/**
	 * 
	 * @param tag
	 */
	private PomFileTags(String tag) {
		this.tag = tag;
	}
}
