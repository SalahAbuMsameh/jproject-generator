package com.apisoft.jpgen.part;

import java.util.LinkedList;
import java.util.List;

/**
 * Annotation type
 * 
 * @author Salah Abu Msameh
 */
public class JAnnotation implements JPart {

	private String annotationName;
	private String defaultValue;
	private List<Attribute> attributes = new LinkedList<Attribute>();
	private boolean string;

	/**
	 * 
	 * @param annotationName
	 */
	public JAnnotation(String annotationName) {
		this(annotationName, null, false);
	}
	
	/**
	 * 
	 * @param annotationName
	 * @param defaultValue
	 * @param isString
	 */
	public JAnnotation(String annotationName, String defaultValue, boolean isString) {
		this.annotationName = annotationName;
		this.defaultValue = defaultValue;
		this.string = isString;
	}
	
	public String getAnnotationName() {
		return annotationName;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	public boolean isString() {
		return this.string;
	}
	
	/**
	 * Annotation related attribute 
	 */
	public static class Attribute {
		
		private String attributeName;
		private String attributeValue;
		private boolean string;
		
		/**
		 * 
		 * @param attributeName
		 * @param attributeValue
		 */
		public Attribute(String attributeName, String attributeValue) {
			this(attributeName, attributeValue, false);
		}
		
		/**
		 * 
		 * @param attributeName
		 * @param attributeValue
		 * @param string
		 */
		public Attribute(String attributeName, String attributeValue, boolean string) {
			this.attributeName = attributeName;
			this.attributeValue = attributeValue;
			this.string = string;
		}
		
		public String getAttributeName() {
			return attributeName;
		}
		
		public String getAttributeValue() {
			return attributeValue;
		}
		
		public boolean isString() {
			return string;
		}
	}
}
