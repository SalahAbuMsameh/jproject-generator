package com.apisoft.jpgen.part.generator;

import java.util.List;
import java.util.Map;

import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.part.pom.Plugin;
import com.apisoft.jpgen.part.pom.PomFile;
import com.apisoft.jpgen.part.pom.PomFile.PomBuild;
import com.apisoft.jpgen.part.pom.PomFileTags;
import com.apisoft.jpgen.util.Utils;

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
		Dependency parent = pom.getParent();
		if(parent != null) {
			
			//parent opening tag
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(openingTag(PomFileTags.PARENT.tag));
			
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(groupIdLine(parent.getGroupId()));
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(artifactIdLine(parent.getArtifactId()));
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(versionLine(parent.getVersion()));
			
			//parent closing tag
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.PARENT.tag));
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		}
		
		//3. add artifact settings
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(groupIdLine(pom.getGroupId()));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(artifactIdLine(pom.getArtifactId()));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(versionLine(pom.getVersion()));
		sb.append(EscapeCharacters.NEW_LINE.escapeChar);
		sb.append(packagingLine(pom.getPackaging()));
		
		//4. add properties
		Map<String, String> properties = pom.getProperties();
		if(!properties.isEmpty()) {
			
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(openingTag(PomFileTags.PROPERTIES.tag));
			
			properties.forEach((propertyKey, propertyValue) -> {
				sb.append(R_TWO_TAPS).append(openingTag(propertyKey));
				sb.append(propertyValue);
				sb.append(closingTag(propertyKey));
			});
			
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.PROPERTIES.tag));
		}
		
		//5. add dependencies
		List<Dependency> dependencies = pom.getDependencies();
		if(!dependencies.isEmpty()) {
			
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
				List<Dependency> exclusions = dep.getExclusions();
				if(!exclusions.isEmpty()) {
					
					sb.append(R_TWO_TAPS).append(EscapeCharacters.TAP.escapeChar).append(openingTag(PomFileTags.EXCLUSIONS.tag));
					
					exclusions.forEach(exc -> {
						sb.append(R_TWO_TAPS).append(TWO_TAPS).append(openingTag(PomFileTags.EXCLUSION.tag));
						sb.append(R_TWO_TAPS).append(TWO_TAPS).append(groupIdLine(exc.getGroupId()));
						sb.append(R_TWO_TAPS).append(TWO_TAPS).append(artifactIdLine(exc.getArtifactId()));
						sb.append(R_TWO_TAPS).append(TWO_TAPS).append(closingTag(PomFileTags.EXCLUSION.tag));
					});
					sb.append(R_TWO_TAPS).append(EscapeCharacters.TAP.escapeChar).append(closingTag(PomFileTags.EXCLUSIONS.tag));
				}
				
				sb.append(R_TWO_TAPS).append(closingTag(PomFileTags.DEPENDENCY.tag));
			});
			
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.DEPENDENCIES.tag));
		}
		
		//6. add build
		PomBuild build = pom.getBuild();
		
		if(build != null) {
			
			sb.append(EscapeCharacters.NEW_LINE.escapeChar);
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(openingTag(PomFileTags.BUILD.tag));
			
			//add build name
			String buildName = build.getBuildName();
			if(Utils.isNotEmpty(buildName)) {
				sb.append(R_TWO_TAPS).append(openingTag(PomFileTags.BUILD_FILAL_NAME.tag))
					.append(buildName)
					.append(closingTag(PomFileTags.BUILD_FILAL_NAME.tag));
			}
			
			List<Plugin> plugins = build.getPlugins();
			if(!plugins.isEmpty()) {
				
				sb.append(R_TWO_TAPS).append(openingTag(PomFileTags.PLUGINS.tag));
				
				plugins.forEach(plugin -> {
					
					sb.append(R_TWO_TAPS).append(EscapeCharacters.TAP.escapeChar).append(openingTag(PomFileTags.PLUGIN.tag));
					sb.append(R_TWO_TAPS).append(EscapeCharacters.TAP.escapeChar).append(groupIdLine(plugin.getGroupId()));
					sb.append(R_TWO_TAPS).append(EscapeCharacters.TAP.escapeChar).append(artifactIdLine(plugin.getArtifactId()));
					
					if(Utils.isNotEmpty(plugin.getVersion())) {
						sb.append(R_TWO_TAPS).append(EscapeCharacters.TAP.escapeChar).append(versionLine(plugin.getVersion()));
					}
					
					Plugin.Configuration conf = plugin.getConfiguration();
					if(conf != null) {
						sb.append(R_TWO_TAPS).append(TWO_TAPS).append(openingTag(PomFileTags.CONFIGURATION.tag));
							
							if(Utils.isNotEmpty(conf.getSource())) {
								sb.append(R_TWO_TAPS).append(TWO_TAPS).append(EscapeCharacters.TAP.escapeChar)
									.append(openingTag(PomFileTags.SOURCE.tag))
									.append(conf.getSource())
									.append(closingTag(PomFileTags.SOURCE.tag));
							}
							
							if(Utils.isNotEmpty(conf.getTarget())) {
								sb.append(R_TWO_TAPS).append(TWO_TAPS).append(EscapeCharacters.TAP.escapeChar)
									.append(openingTag(PomFileTags.TARGET.tag))
									.append(conf.getTarget())
									.append(closingTag(PomFileTags.TARGET.tag));
							}
							
						sb.append(R_TWO_TAPS).append(TWO_TAPS).append(closingTag(PomFileTags.CONFIGURATION.tag));
					}
					
					sb.append(R_TWO_TAPS).append(EscapeCharacters.TAP.escapeChar).append(closingTag(PomFileTags.PLUGIN.tag));
				});
				
				sb.append(R_TWO_TAPS).append(closingTag(PomFileTags.PLUGINS.tag));
			}
			sb.append(EscapeCharacters.R_TAP.escapeChar).append(closingTag(PomFileTags.BUILD.tag));
		}
		
		sb.append("</project>");
		
		return sb.toString();
	}

	/**
	 * 
	 * @param groupId
	 * @return
	 */
	private StringBuilder groupIdLine(String groupId) {
		return new StringBuilder(EscapeCharacters.TAP.escapeChar)
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
		return new StringBuilder(EscapeCharacters.TAP.escapeChar)
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
		return new StringBuilder(EscapeCharacters.TAP.escapeChar)
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
		return new StringBuilder(EscapeCharacters.TAP.escapeChar)
				.append(openingTag(PomFileTags.PACKAGING.tag))
				.append(version)
				.append(closingTag(PomFileTags.PACKAGING.tag));
	}
	
	/**
	 * 
	 * @param scope
	 * @return
	 */
	private StringBuilder scopeLine(String scope) {
		return new StringBuilder(EscapeCharacters.TAP.escapeChar)
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
