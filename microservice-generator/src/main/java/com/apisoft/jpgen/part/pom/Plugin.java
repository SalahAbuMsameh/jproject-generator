package com.apisoft.jpgen.part.pom;

/**
 * 
 * @author Salah Abu Msameh
 */
public class Plugin extends Dependency {

	private Configuration configuration;
	
	
	/**
	 * 
	 * @param groupId
	 * @param artifactId
	 */
	public Plugin(String groupId, String artifactId) {
		super(groupId, artifactId);
	}
	
	/**
	 * 
	 * @param groupId
	 * @param artifactId
	 * @param version
	 */
	public Plugin(String groupId, String artifactId, String version) {
		super(groupId, artifactId, version);
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Helper class to hold the plugin's configuration
	 */
	public static class Configuration {
		
		private String source;
		private String target;
		
		public String getSource() {
			return source;
		}
		
		public void setSource(String source) {
			this.source = source;
		}
		
		public String getTarget() {
			return target;
		}
		
		public void setTarget(String target) {
			this.target = target;
		}
	}
}
