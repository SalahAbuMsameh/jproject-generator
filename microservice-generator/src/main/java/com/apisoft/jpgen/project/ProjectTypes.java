package com.apisoft.jpgen.project;

/**
 * 
 * @author Salah Abu Msameh
 */
public enum ProjectTypes {

	SPRINGBOOT_MICROSERVICE("springboot-microservice");
	
	public String type;

	/**
	 * 
	 * @param type
	 */
	private ProjectTypes(String type) {
		this.type = type;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isValidType(String type) {
		
		for(ProjectTypes projectType : ProjectTypes.values()) {
			if(projectType.type.equals(type)) {
				return true;
			}
		}
		
		return false;
	}
}
