package com.apisoft.jpgen.project.mvn.spring;

import java.nio.file.Paths;

import com.apisoft.jpgen.JProjectGenException;
import com.apisoft.jpgen.ProjectProperties;
import com.apisoft.jpgen.io.IOUtils;
import com.apisoft.jpgen.part.AccessTypes;
import com.apisoft.jpgen.part.JAnnotation;
import com.apisoft.jpgen.part.JClass;
import com.apisoft.jpgen.part.JMethod;
import com.apisoft.jpgen.part.generator.JClassGenerator;
import com.apisoft.jpgen.part.generator.PomFileGenerator;
import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.part.pom.Plugin;
import com.apisoft.jpgen.part.pom.PomFile;
import com.apisoft.jpgen.part.pom.PomFile.PomBuild;
import com.apisoft.jpgen.project.mvn.MavenProject;
import com.apisoft.jpgen.util.Utils;

/**
 * 
 * @author Salah Abu Msameh
 */
public class SpringbootMicroserviceProject extends MavenProject {
	
	protected String defaultClassName;
	protected JClass springbootAppClass;
	
	
	/**
	 * 
	 * @param properties
	 */
	public SpringbootMicroserviceProject(ProjectProperties properties) {
		super(properties);
	}
	
	@Override
	public void prepare() throws JProjectGenException {
		
		defaultClassName = Utils.toClassName(properties.getProjectName());
		
		//springboot application class
		prepareSpringbootApp();
		
		//spring boot parent pom
		preparePomFile();
		
		//prepare classes
		classes.add(prepareRestController());
		classes.add(prepareServiceClass());
	}
	
	/**
	 * prepare springboot application class
	 */
	protected void prepareSpringbootApp() {
		
		JMethod mainMethod = new JMethod(AccessTypes.PUBLIC, true, "main");
		mainMethod.addParameter("args", "String[]");
		mainMethod.setImplementation("		SpringApplication.run(" + defaultClassName + "Application.class, args);");
		
		springbootAppClass = new JClass.ClassBuilder(properties.getPackageName(), defaultClassName + "Application")
				.importName("org.springframework.boot.SpringApplication")
				.importName("org.springframework.boot.autoconfigure.SpringBootApplication")
				.annotation(new JAnnotation("SpringBootApplication"))
				.method(mainMethod)
				.build();
	}

	/**
	 * 
	 * @return
	 */
	private JClass prepareServiceClass() {
		return new JClass.ClassBuilder(properties.getPackageName() + "." + "service", defaultClassName + "Service")
				.importName("org.springframework.stereotype.Service")
				.annotation(new JAnnotation("Service"))
				.build();
	}

	/**
	 * 
	 * @return
	 */
	protected JClass prepareRestController() {
		return new JClass.ClassBuilder(properties.getPackageName() + "." + "rest", defaultClassName + "RestController")
				.importName("org.springframework.web.bind.annotation.RestController")
				.annotation(new JAnnotation("RestController"))
				.build();
	}

	/**
	 * prepare pom file
	 */
	protected void preparePomFile() {
		
		PomBuild pomBuild = new PomBuild();
		pomBuild.setBuildName(properties.getProjectName());
		pomBuild.getPlugins().add(new Plugin("org.springframework.boot", "spring-boot-maven-plugin"));

		pomFile = new PomFile.PomFileBuilder(properties.getPackageName(), properties.getProjectName())
				.parent(new Dependency("org.springframework.boot", "spring-boot-starter-parent", properties.getSpringbootVersion()))
				.version(properties.getVersion())
				.property("java.version", properties.getJavaVersion())
				.pomBuild(pomBuild)
				.build();
	}
	
	@Override
	public void generate() throws JProjectGenException {
		
		String projectName = properties.getProjectName();
		
		//1. create root project directory
		IOUtils.createDir(projectName);
		
		//2. copy maven wrapper folder
		IOUtils.copyDir(Utils.getResourceFolderPath("spring/springboot"), Paths.get(projectName));
		
		//3. create main package
		String mainSrcDirPath = getMainSrcDirPath();
		IOUtils.createDir(mainSrcDirPath + Utils.packageToPath(properties.getPackageName()));
		
		//4. write classes
		JClassGenerator clsGen = new JClassGenerator();
		IOUtils.writeFile(mainSrcDirPath + Utils.packageToPath(properties.getPackageName()) + "/" + springbootAppClass.getClassName() + ".java", 
				clsGen.generate(springbootAppClass));
		
		classes.forEach(cls -> {
			String clsPackageName = cls.getPackageName();
			IOUtils.createDir(mainSrcDirPath + Utils.packageToPath(clsPackageName));
			IOUtils.writeFile(mainSrcDirPath + Utils.packageToPath(clsPackageName) + "/" + cls.getClassName() + ".java", 
					clsGen.generate(cls));
		});
		
		//5. write resources
		String resourceDirPath = getResourcesDirPath();
		
		IOUtils.createDir(resourceDirPath);
		IOUtils.createDir(resourceDirPath + "/" + "static");
		IOUtils.createDir(resourceDirPath + "/" + "templates");
		IOUtils.writeFile(resourceDirPath + "/" + "application.properties", getAppPropertiesContent());
		
		//5. write pom file
		IOUtils.writeFile(projectName + "/" + "pom.xml", new PomFileGenerator().generate(pomFile));
	}
	
	/**
	 * 
	 * @return
	 */
	protected String getAppPropertiesContent() {
		return "";
	}

	/**
	 * 
	 * @return
	 */
	protected final String getMainSrcDirPath() {
		return new StringBuilder(properties.getProjectName())
				.append(MAIN_SRC)
				.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	protected String getResourcesDirPath() {
		return new StringBuilder(properties.getProjectName()).append(MAIN_SRC_RESOURCES).toString();
	}
}
