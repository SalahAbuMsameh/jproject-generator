package com.apisoft.jpgen.project;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Salah Abu Msameh
 */
public enum ProjectTypes {

	SPRINGBOOT_MICROSERVICE("springboot-microservice");
	
	public String type;
	private static Map<String, ProjectTypes> types = new HashMap<String, ProjectTypes>();
	
	static {
		for(ProjectTypes t : ProjectTypes.values()) {
			types.put(t.type, t);
		}
	}

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
		return types.keySet().contains(type);
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static ProjectTypes getByType(String type) {
		return types.get(type);
	}
}
