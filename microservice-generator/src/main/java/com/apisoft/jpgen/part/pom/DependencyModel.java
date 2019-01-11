package com.apisoft.jpgen.part.pom;

/**
 * 
 * @author Salah Abu Msameh
 */
public interface DependencyModel {

	/**
	 * 
	 * @return dependency group id
	 */
	public String getGroupId();
	
	/**
	 * Sets dependency group id
	 * 
	 * @param groupId
	 */
	public void setGroupId(String groupId);
	
	/**
	 * 
	 * @return dependency artifact id
	 */
	public String getArtifactId();
	
	/**
	 * Sets dependency artifact id
	 * 
	 * @param artifactId
	 */
	public void setArtifactId(String artifactId);
}
