package com.apisoft.jpgen.part.generator;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.part.pom.Plugin;
import com.apisoft.jpgen.part.pom.Plugin.Configuration;
import com.apisoft.jpgen.part.pom.Plugin.Configuration.Archive;
import com.apisoft.jpgen.part.pom.PomFile;
import com.apisoft.jpgen.part.pom.PomFile.PomBuild;
import com.apisoft.jpgen.part.pom.PomFileTags;

/**
 * 
 * @author Salah Abu Msameh
 */
public class PomFileGenerator implements JGenerator<PomFile> {
	
	@Override
	public String generate(PomFile pom) {
		
		StringBuilder sb = new StringBuilder();
		
		//1. add pom header
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
		sb.append(EscapeCharacters.R_TAP.escapeChar).append("<modelVersion>4.0.0</modelVersion>");
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		
		//2. add parent
		addParent(sb, pom.getParent());
		
		//3. add artifact settings
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(groupIdLine(pom.getGroupId()));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(artifactIdLine(pom.getArtifactId()));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(versionLine(pom.getVersion()));
		
		if(StringUtils.isNotEmpty(pom.getPackaging())) {
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			sb.append(packagingLine(pom.getPackaging()));
		}
		
		if(StringUtils.isNotEmpty(pom.getName())) {
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			sb.append(nameLine(pom.getName()));
		}
		
		if(StringUtils.isNotEmpty(pom.getDescription())) {
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			sb.append(descriptionLine(pom.getDescription()));
		}
		
		//4. add properties
		addProperties(sb, pom.getProperties());
		
		//5. add dependencies
		addDependencies(sb, pom.getDependencies());
		
		//6. add build
		addPomBuild(sb, pom.getBuild());
		
		sb.append(EscapeCharacters.NEW_LINE.escapeChar).append("</project>");
		
		return sb.toString();
	}
	
	/**
	 * Add pom parent
	 * 
	 * @param sb
	 * @param parent
	 */
	private void addParent(StringBuilder sb, Dependency parent) {
		
		if(parent == null) {
			return;
		}
		
		//parent opening tag
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(openingTag(PomFileTags.PARENT.tag));
		
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(groupIdLine(parent.getGroupId()));
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(artifactIdLine(parent.getArtifactId()));
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(versionLine(parent.getVersion()));
		
		//parent closing tag
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.PARENT.tag));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
	}
	
	/**
	 * add properties
	 * 
	 * @param sb
	 * @param properties
	 */
	private void addProperties(StringBuilder sb, Map<String, String> properties) {
		
		if(properties.isEmpty()) {
			return;
		}
		
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(openingTag(PomFileTags.PROPERTIES.tag));
		
		properties.forEach((propertyKey, propertyValue) -> {
			sb.append(R_TWO_TAPS).append(openingTag(propertyKey));
			sb.append(propertyValue);
			sb.append(closingTag(propertyKey));
		});
		
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.PROPERTIES.tag));
	}
	
	/**
	 * add pom dependencies
	 * 
	 * @param sb
	 * @param dependencies
	 */
	private void addDependencies(StringBuilder sb, List<Dependency> dependencies) {
		
		if(dependencies.isEmpty()) {
			return;
		}
		
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(openingTag(PomFileTags.DEPENDENCIES.tag));
		
		dependencies.forEach(dep -> {
			
			sb.append(R_TWO_TAPS).append(openingTag(PomFileTags.DEPENDENCY.tag));
			sb.append(R_TWO_TAPS).append(groupIdLine(dep.getGroupId()));
			sb.append(R_TWO_TAPS).append(artifactIdLine(dep.getArtifactId()));
			
			if(dep.getVersion() != null) {
				sb.append(R_TWO_TAPS).append(versionLine(dep.getVersion()));
			}
			
			if(dep.getScope() != null) {
				sb.append(R_TWO_TAPS).append(scopeLine(dep.getScope()));
			}
			
			//add exclusions
			addExclusions(sb, dep.getExclusions());
			sb.append(R_TWO_TAPS).append(closingTag(PomFileTags.DEPENDENCY.tag));
		});
		
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.DEPENDENCIES.tag));
	}
	
	/**
	 * add dependency exclusions
	 * 
	 * @param sb
	 * @param exclusions
	 */
	private void addExclusions(StringBuilder sb, List<Dependency> exclusions) {
		
		if(exclusions.isEmpty()) {
			return;
		}
		
		sb.append(R_TWO_TAPS).append(TAP).append(openingTag(PomFileTags.EXCLUSIONS.tag));
		
		exclusions.forEach(exc -> {
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(openingTag(PomFileTags.EXCLUSION.tag));
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(groupIdLine(exc.getGroupId()));
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(artifactIdLine(exc.getArtifactId()));
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(closingTag(PomFileTags.EXCLUSION.tag));
		});
		
		sb.append(R_TWO_TAPS).append(TAP).append(closingTag(PomFileTags.EXCLUSIONS.tag));
	}
	
	/**
	 * add pom build
	 * 
	 * @param sb
	 * @param build
	 */
	private void addPomBuild(StringBuilder sb, PomBuild build) {
		
		if(build == null) {
			return;
		}
		
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(openingTag(PomFileTags.BUILD.tag));
		
		//add build name
		addBuildName(sb, build.getBuildName());
		List<Plugin> plugins = build.getPlugins();
		
		if(!plugins.isEmpty()) {
			
			sb.append(R_TWO_TAPS).append(openingTag(PomFileTags.PLUGINS.tag));
			
			plugins.forEach(plugin -> {
				
				sb.append(R_TWO_TAPS).append(TAP).append(openingTag(PomFileTags.PLUGIN.tag));
				sb.append(R_TWO_TAPS).append(TAP).append(groupIdLine(plugin.getGroupId()));
				sb.append(R_TWO_TAPS).append(TAP).append(artifactIdLine(plugin.getArtifactId()));
				
				if(StringUtils.isNotEmpty(plugin.getVersion())) {
					sb.append(R_TWO_TAPS).append(TAP).append(versionLine(plugin.getVersion()));
				}
				
				addConfiguration(sb, plugin.getConfiguration());
				sb.append(R_TWO_TAPS).append(TAP).append(closingTag(PomFileTags.PLUGIN.tag));
			});
			
			sb.append(R_TWO_TAPS).append(closingTag(PomFileTags.PLUGINS.tag));
		}
		
		sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.BUILD.tag));
	}
	
	/**
	 * 
	 * @param sb
	 * @param buildName
	 */
	private void addBuildName(StringBuilder sb, String buildName) {
		
		if(StringUtils.isEmpty(buildName)) {
			return;
		}
		
		sb.append(R_TWO_TAPS);
		sb.append(openingTag(PomFileTags.BUILD_FILAL_NAME.tag));
		sb.append(buildName);
		sb.append(closingTag(PomFileTags.BUILD_FILAL_NAME.tag));
	}

	/**
	 * add pom configuration 
	 * 
	 * @param sb
	 * @param conf
	 */
	private void addConfiguration(StringBuilder sb, Configuration conf) {
		
		if(conf == null) {
			return;
		}
		
		sb.append(R_TWO_TAPS).append(TWO_TAPS).append(openingTag(PomFileTags.CONFIGURATION.tag));
		
		if(StringUtils.isNotEmpty(conf.getSource())) {
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TAP)
				.append(openingTag(PomFileTags.SOURCE.tag))
				.append(conf.getSource())
				.append(closingTag(PomFileTags.SOURCE.tag));
		}
		
		if(StringUtils.isNotEmpty(conf.getTarget())) {
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TAP)
				.append(openingTag(PomFileTags.TARGET.tag))
				.append(conf.getTarget())
				.append(closingTag(PomFileTags.TARGET.tag));
		}
		
		//add buid archive
		addArchive(sb, conf.getArchive());
		
		sb.append(R_TWO_TAPS).append(TWO_TAPS).append(closingTag(PomFileTags.CONFIGURATION.tag));
	}
	
	/**
	 * add build archive 
	 * 
	 * @param sb
	 * @param archive
	 */
	private void addArchive(StringBuilder sb, Archive archive) {
		
		if(archive == null) {
			return;
		}
		
		sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TAP).append(openingTag("archive"));
		
		if(archive.getManifest() != null) {
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TWO_TAPS).append(openingTag("manifest"));
			archive.getManifest().getEntries().keySet().forEach(key -> {
				sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TWO_TAPS).append(TAP).append(openingTag(key));
				sb.append(archive.getManifest().getEntries().get(key));
				sb.append(closingTag(key));
			});
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TWO_TAPS).append(closingTag("manifest"));
		}
		
		if(archive.getManifestEntries() != null) {
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TWO_TAPS).append(openingTag("manifestEntries"));
			archive.getManifestEntries().getEntries().keySet().forEach(key -> {
				sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TWO_TAPS).append(TAP).append(openingTag(key));
				sb.append(archive.getManifestEntries().getEntries().get(key));
				sb.append(closingTag(key));
			});
			sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TWO_TAPS).append(closingTag("manifestEntries"));
		}
		
		sb.append(R_TWO_TAPS).append(TWO_TAPS).append(TAP).append(closingTag("archive"));
	}

	/**
	 * 
	 * @param groupId
	 * @return
	 */
	private StringBuilder groupIdLine(String groupId) {
		return new StringBuilder(TAP)
				.append(openingTag(PomFileTags.GROUP_ID.tag))
				.append(groupId)
				.append(closingTag(PomFileTags.GROUP_ID.tag));
	}
	
	/**
	 * 
	 * @param artifactId
	 * @return
	 */
	private StringBuilder artifactIdLine(String artifactId) {
		return new StringBuilder(TAP)
				.append(openingTag(PomFileTags.ARTIFACT_ID.tag))
				.append(artifactId)
				.append(closingTag(PomFileTags.ARTIFACT_ID.tag));
	}
	
	/**
	 * 
	 * @param version
	 * @return
	 */
	private StringBuilder versionLine(String version) {
		return new StringBuilder(TAP)
				.append(openingTag(PomFileTags.VERSION.tag))
				.append(version)
				.append(closingTag(PomFileTags.VERSION.tag));
	}
	
	/**
	 * 
	 * @param version
	 * @return
	 */
	private StringBuilder packagingLine(String version) {
		return new StringBuilder(TAP)
				.append(openingTag(PomFileTags.PACKAGING.tag))
				.append(version)
				.append(closingTag(PomFileTags.PACKAGING.tag));
	}
	
	/**
	 * 
	 * @param version
	 * @return
	 */
	private StringBuilder nameLine(String name) {
		return new StringBuilder(TAP)
				.append(openingTag(PomFileTags.NAME.tag))
				.append(name)
				.append(closingTag(PomFileTags.NAME.tag));
	}
	
	/**
	 * 
	 * @param version
	 * @return
	 */
	private StringBuilder descriptionLine(String description) {
		return new StringBuilder(TAP)
				.append(openingTag(PomFileTags.DESCRIPTION.tag))
				.append(description)
				.append(closingTag(PomFileTags.DESCRIPTION.tag));
	}
	
	/**
	 * 
	 * @param scope
	 * @return
	 */
	private StringBuilder scopeLine(String scope) {
		return new StringBuilder(TAP)
				.append(openingTag(PomFileTags.SCOPE.tag))
				.append(scope)
				.append(closingTag(PomFileTags.SCOPE.tag));
	}
	
	/**
	 * 
	 * @param tag
	 * @return open tag string
	 */
	private StringBuilder openingTag(String tag) {
		return new StringBuilder(LEFT_ANGLE_BRACKET)
				.append(tag)
				.append(RIGHT_ANGLE_BRACKET);
	}
	
	/**
	 * 
	 * @param tag
	 * @return open tag string
	 */
	private StringBuilder closingTag(String tag) {
		return new StringBuilder(LEFT_ANGLE_BRACKET)
				.append(FORWARD_SPASH)
				.append(tag)
				.append(RIGHT_ANGLE_BRACKET);
	}
}
