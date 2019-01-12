package com.apisoft.jpgen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.project.JPrjectFactor;
import com.apisoft.jpgen.project.JProject;
import com.apisoft.jpgen.project.ProjectTypes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JProjectGenerator {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.err.println("No generation file found");
			System.exit(0);
		}
		
		try {
			ProjectProperties properties = loadProjectProperties(args[0]);
			validate(properties);
			JProject project = JPrjectFactor.getProject(properties);
			
			project.prepare();
			project.generate();
		} 
		catch (JsonParseException e) {
			e.printStackTrace();
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (JProjectGenException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}

	/**
	 * 
	 * @param properties
	 */
	private static void validate(ProjectProperties properties) throws JProjectGenException {
		
		String projectName = properties.getProjectName();
		
		if(StringUtils.isEmpty(projectName)) {
			throw new JProjectGenException("project name is mandatory");
		}
		
		if(Character.isDigit(projectName.charAt(0))) {
			throw new JProjectGenException("Invalid project name, cannot start with number");
		}
		
		if(projectName.matches("[^a-zA-Z\\-_0-9]")) {
			throw new JProjectGenException("Invalid project name, special characters is not allowed exept - and _ ");
		}
		
		String projectType = properties.getProjectType();
		
		if(StringUtils.isEmpty(projectType)) {
			throw new JProjectGenException("Project type is mandatory");
		}
		
		if(!ProjectTypes.isValidType(projectType)) {
			throw new JProjectGenException("Unsupported project type");
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	private static ProjectProperties loadProjectProperties(String filePath) 
			throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		
		return new ObjectMapper()
				.readValue(new FileInputStream(filePath), ProjectProperties.class);
	}
	
	/**
	 * 
	 * @return
	 */
	private static String getDummyProperties() {
		
		ProjectProperties props = new ProjectProperties();
		
		props.setProjectName("user-service");
		props.setPackageName("com.apisoft.user");
		props.setProjectType("springboot-microservice");
		props.setVersion("1.0");
		
		List<Dependency> dependencies = new ArrayList<>();
		dependencies.add(new Dependency("org.springframework.boot", "spring-boot-starter-web"));
		dependencies.add(new Dependency("org.springframework.boot", "spring-boot-starter-test"));
		
		props.setDependancies(dependencies);
		
		try {
			return new ObjectMapper().writeValueAsString(props);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
