package com.apisoft.jpgen.part.pom;

import java.util.HashMap;
import java.util.Map;

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
		private Archive archive;
		
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
		
		public Archive getArchive() {
			return archive;
		}
		
		public void setArchive(Archive archive) {
			this.archive = archive;
		}

		/**
		 * Maven archive object
		 */
		public static class Archive {
			
			private MapClass manifest = new MapClass();
			private MapClass manifestEntries = new MapClass();
			
			public MapClass getManifest() {
				return manifest;
			}
			
			public void addManifest(String mKey, String mValue) {
				this.manifest.addEntry(mKey, mValue);
			}
			
			public MapClass getManifestEntries() {
				return manifestEntries;
			}
			
			public void addManifestEntries(String meKey, String meValue) {
				this.manifestEntries.addEntry(meKey, meValue);
			}
			
			/**
			 * Map wrapper class
			 */
			public static class MapClass {
				
				private Map<String, String> entries = new HashMap<String, String>();
				
				public Map<String, String> getEntries() {
					return entries;
				}
				
				public void addEntry(String key, String value) {
					this.entries.put(key, value);
				}
			}
		}
	}
}
