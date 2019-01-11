package com.apisoft.jpgen.part.pom;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.apisoft.jpgen.part.JPart;

/**
 * Represents a simple pom file characteristics
 * 
 * @author Salah Abu Msameh
 */
public class PomFile extends Dependency implements JPart {

	private String packaging;
	private String name;
	private String description;
	
	private Dependency parent;
	private Map<String, String> properties = new HashMap<String, String>();
	private List<Dependency> dependencies = new LinkedList<Dependency>();
	private PomBuild build;
	
	/**
	 * 
	 * @param groupId
	 * @param artifactId
	 * @param version
	 */
	public PomFile(String groupId, String artifactId, String version) {
		super(groupId, artifactId, version);
	}
	
	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	public Dependency getParent() {
		return parent;
	}
	
	public void setParent(Dependency parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addProperty(String propertyKey, String propertyValue) {
		properties.put(propertyKey, propertyValue);
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
	
	public void addDependency(Dependency dependency) {
		dependencies.add(dependency);
	}
	
	public List<Dependency> getDependencies() {
		return dependencies;
	}
	
	public PomBuild getBuild() {
		return build;
	}
	
	public void setBuild(PomBuild build) {
		this.build = build;
	}
	
	/**
	 * helper class to hold the pom buid info
	 */
	public static class PomBuild {
		
		private String buildName;
		private List<Plugin> plugins = new LinkedList<Plugin>();
		
		public String getBuildName() {
			return buildName;
		}

		public void setBuildName(String buildName) {
			this.buildName = buildName;
		}
		
		public void addPlugin(Plugin plugin) {
			plugins.add(plugin);
		}
		
		public List<Plugin> getPlugins() {
			return plugins;
		}
	}
}
