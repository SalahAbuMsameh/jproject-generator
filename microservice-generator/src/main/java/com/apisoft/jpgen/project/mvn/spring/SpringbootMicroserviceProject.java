package com.apisoft.jpgen.project.mvn.spring;

import java.util.ArrayList;
import java.util.List;

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
	
	protected JClass springbootAppClass;
	protected List<JClass> junitClasses = new ArrayList<JClass>();
	
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
		
		//prepare junit classes
		prepareJUnitClasses();
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
	protected JClass prepareServiceClass() {
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
	 * prepare junit classes
	 */
	protected void prepareJUnitClasses() {
		//do nothing for now
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
		//IOUtils.copyDir(IOUtils.getResourceFolderPath("spring/springboot"), Paths.get(projectName));
		String mavenWrapperDir = projectName + "\\.mvn\\wrapper";
		IOUtils.createDir(mavenWrapperDir);
		IOUtils.copyResourceFile("/spring/springboot/.mvn/wrapper", "maven-wrapper.jar", mavenWrapperDir);
		IOUtils.copyResourceFile("/spring/springboot/.mvn/wrapper", "maven-wrapper.properties", mavenWrapperDir);
		//IOUtils.copyResourceFile("/spring/springboot", ".gitignore", projectName);
		IOUtils.writeFile(projectName + "/.gitignore", getIgnoreFileContent());
		IOUtils.copyResourceFile("/spring/springboot", "mvnw", projectName);
		IOUtils.copyResourceFile("/spring/springboot", "mvnw.cmd", projectName);
				
		//3. create main package
		String mainSrcDirPath = getMainSrcDirPath();
		IOUtils.createDir(mainSrcDirPath + Utils.packageToPath(properties.getPackageName()));
		
		//4. write classes
		JClassGenerator clsGen = new JClassGenerator();
		IOUtils.writeFile(mainSrcDirPath + Utils.packageToPath(properties.getPackageName()) + "/" + springbootAppClass.getClassName() + ".java", 
				clsGen.generate(springbootAppClass));
		
		for(JClass clazz : classes) {
			
			String clsPackageName = clazz.getPackageName();
			IOUtils.createDir(mainSrcDirPath + Utils.packageToPath(clsPackageName));
			IOUtils.writeFile(mainSrcDirPath + Utils.packageToPath(clsPackageName) + "/" + clazz.getClassName() + ".java", 
					clsGen.generate(clazz));
		}
		
		//5. write junit classes
		String testSrcDirPath = getTestSrcDirPath();
		for(JClass clazz : junitClasses) {
			
			String clsPackageName = clazz.getPackageName();
			IOUtils.createDir(testSrcDirPath + Utils.packageToPath(clsPackageName));
			IOUtils.writeFile(testSrcDirPath + Utils.packageToPath(clsPackageName) + "/" + clazz.getClassName() + ".java", 
					clsGen.generate(clazz));
		}
		
		//6. write resources
		String resourceDirPath = getResourcesDirPath();
		
		IOUtils.createDir(resourceDirPath);
		IOUtils.createDir(resourceDirPath + "/" + "static");
		IOUtils.createDir(resourceDirPath + "/" + "templates");
		IOUtils.writeFile(resourceDirPath + "/" + "application.properties", getAppPropertiesContent());
		
		//7. write pom file
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
	protected final String getTestSrcDirPath() {
		return new StringBuilder(properties.getProjectName())
				.append(TEST_SRC)
				.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	protected String getResourcesDirPath() {
		return new StringBuilder(properties.getProjectName())
				.append(MAIN_SRC_RESOURCES)
				.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	private String getIgnoreFileContent() {
		
		return "/target/\r\n" + 
				"!.mvn/wrapper/maven-wrapper.jar\r\n" + 
				"\r\n" + 
				"### STS ###\r\n" + 
				".apt_generated\r\n" + 
				".classpath\r\n" + 
				".factorypath\r\n" + 
				".project\r\n" + 
				".settings\r\n" + 
				".springBeans\r\n" + 
				".sts4-cache\r\n" + 
				"\r\n" + 
				"### IntelliJ IDEA ###\r\n" + 
				".idea\r\n" + 
				"*.iws\r\n" + 
				"*.iml\r\n" + 
				"*.ipr\r\n" + 
				"\r\n" + 
				"### NetBeans ###\r\n" + 
				"/nbproject/private/\r\n" + 
				"/build/\r\n" + 
				"/nbbuild/\r\n" + 
				"/dist/\r\n" + 
				"/nbdist/\r\n" + 
				"/.nb-gradle/";
	}
}
