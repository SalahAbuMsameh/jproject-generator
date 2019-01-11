package com.apisoft.jpgen.part.pom;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Salah Abu Msameh
 */
public class Dependency implements DependencyModel {

	private String groupId;
	private String artifactId;
	private String version;
	private String scope;
	private List<Dependency> exclusions = new LinkedList<Dependency>();
	
	/**
	 * 
	 * @param groupId
	 * @param artifactId
	 */
	public Dependency(String groupId, String artifactId) {
		this(groupId, artifactId, null);
	}
	
	/**
	 * 
	 * @param groupId
	 * @param artifactId
	 * @param version
	 */
	public Dependency(String groupId, String artifactId, String version) {
		this(groupId, artifactId, version, null);
	}
	
	/**
	 * 
	 * @param groupId
	 * @param artifactId
	 * @param version
	 * @param scope
	 */
	public Dependency(String groupId, String artifactId, String version, String scope) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.scope = scope;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}
	
	@Override
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Override
	public String getArtifactId() {
		return artifactId;
	}
	
	@Override
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getScope() {
		return scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public void addExclusion(Dependency dependency) {
		exclusions.add(dependency);
	}
	
	public List<Dependency> getExclusions() {
		return exclusions;
	}
}
