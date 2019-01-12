package com.apisoft.jpgen.project;

import com.apisoft.jpgen.JProjectGenException;
import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.project.mvn.spring.SpringbootMicroserviceProject;

/**
 * 
 * @author Salah Abu Msameh
 */
public class JPrjectFactor {

	/**
	 * 
	 * @param projectType
	 * @throws JProjectGenException 
	 */
	public static JProject getProject(ProjectProperties properties) throws JProjectGenException {
		
		switch (ProjectTypes.getByType(properties.getProjectType())) {
			case SPRINGBOOT_MICROSERVICE: {
				return new SpringbootMicroserviceProject(properties);
			}
			default: {
				throw new JProjectGenException("Unsupported type");
			}
		}
	}
}
