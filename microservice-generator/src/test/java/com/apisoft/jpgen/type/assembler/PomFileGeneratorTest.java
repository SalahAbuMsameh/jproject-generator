package com.apisoft.jpgen.type.assembler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.apisoft.jpgen.part.generator.PomFileGenerator;
import com.apisoft.jpgen.part.pom.Dependency;
import com.apisoft.jpgen.part.pom.Plugin;
import com.apisoft.jpgen.part.pom.Plugin.Configuration;
import com.apisoft.jpgen.part.pom.PomFile;
import com.apisoft.jpgen.part.pom.PomFile.PomBuild;

/**
 * 
 * @author Salah Abu Msameh
 */
public class PomFileGeneratorTest {

	/**
	 * 
	 */
	@Test
	public void testPomFileAssembling() {
		
		//1. test simple pom file
		PomFile pomFile = new PomFile("com.cname.prod", "artifact-name", "1.02");
		PomFileGenerator pomFileGen = new PomFileGenerator();
		
		String pomFileStr =  pomFileGen.generate(pomFile);
		
		assertNotNull(pomFileStr);
		assertTrue(pomFileStr.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
		assertTrue(pomFileStr.contains("<groupId>com.cname.prod</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>artifact-name</artifactId>"));
		assertTrue(pomFileStr.contains("<version>1.02</version>"));
		
		//2. add packaging
		pomFile.setPackaging("war");
		pomFileStr = pomFileGen.generate(pomFile);
		assertTrue(pomFileStr.contains("<packaging>war</packaging>"));
		
		//3. add parent
		Dependency parent = new Dependency("com.cname.parent", "parent-artifact-name", "2.0");
		pomFile.setParent(parent);
		pomFileStr =  pomFileGen.generate(pomFile);
		
		assertTrue(pomFileStr.contains("<parent>"));
		assertTrue(pomFileStr.contains("<groupId>com.cname.parent</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>parent-artifact-name</artifactId>"));
		assertTrue(pomFileStr.contains("<version>2.0</version>"));
		assertTrue(pomFileStr.contains("</parent>"));
		
		//4.1 add a dependency with no version
		Dependency d1 = new Dependency("org.springframework.boot", "spring-boot-starter-web");
		d1.addExclusion(new Dependency("org.springframework.boot", "spring-boot-starter-tomcat"));
		pomFile.addDependency(d1);
		pomFileStr = pomFileGen.generate(pomFile);
		
		assertTrue(pomFileStr.contains("<dependencies>"));
		assertTrue(pomFileStr.contains("<dependency>"));
		assertTrue(pomFileStr.contains("<groupId>org.springframework.boot</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>spring-boot-starter-web</artifactId>"));
		assertTrue(pomFileStr.contains("<exclusions>"));
		assertTrue(pomFileStr.contains("<exclusion>"));
		assertTrue(pomFileStr.contains("<groupId>org.springframework.boot</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>spring-boot-starter-tomcat</artifactId>"));
		assertTrue(pomFileStr.contains("</exclusion>"));
		assertTrue(pomFileStr.contains("</exclusions>"));
		assertTrue(pomFileStr.contains("</dependency>"));
		assertTrue(pomFileStr.contains("</dependencies>"));
		
		//4.2 add a dependency with version
		pomFile.addDependency(new Dependency("io.springfox", "springfox-swagger2", "2.9.2"));
		pomFileStr = pomFileGen.generate(pomFile);
		
		assertTrue(pomFileStr.contains("<groupId>io.springfox</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>springfox-swagger2</artifactId>"));
		assertTrue(pomFileStr.contains("<version>2.9.2</version>"));
		
		//4.3 add a dependency with scope
		pomFile.addDependency(new Dependency("org.springframework.security", "spring-security-test", null, "test"));
		pomFileStr = pomFileGen.generate(pomFile);
		
		assertTrue(pomFileStr.contains("<groupId>org.springframework.security</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>spring-security-test</artifactId>"));
		assertTrue(pomFileStr.contains("<scope>test</scope>"));
		
		//5. add properties
		pomFile.addProperty("java.version", "1.8");
		pomFileStr = pomFileGen.generate(pomFile);
		
		assertTrue(pomFileStr.contains("<properties>"));
		assertTrue(pomFileStr.contains("<java.version>1.8</java.version>"));
		assertTrue(pomFileStr.contains("</properties>"));
		
		//5.1 add plugins
		PomBuild b = new PomBuild();
		
		b.setBuildName("ArtifactNameV1");
		b.addPlugin(new Plugin("org.springframework.boot", "spring-boot-maven-plugin"));
		
		pomFile.setBuild(b);
		pomFileStr = pomFileGen.generate(pomFile);
		
		assertTrue(pomFileStr.contains("<build>"));
		assertTrue(pomFileStr.contains("<plugins>"));
		assertTrue(pomFileStr.contains("<plugin>"));
		assertTrue(pomFileStr.contains("<groupId>org.springframework.boot</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>spring-boot-maven-plugin</artifactId>"));
		assertTrue(pomFileStr.contains("</plugin>"));
		assertTrue(pomFileStr.contains("</plugins>"));
		assertTrue(pomFileStr.contains("</build>"));
		
		//5.2 another plugin
		Plugin plugin = new Plugin("org.apache.maven.plugins", "maven-compiler-plugin", "3.6.1");
		Configuration conf = new Configuration();
		conf.setSource("1.8");
		conf.setTarget("1.8");
		plugin.setConfiguration(conf);
		b.addPlugin(plugin);
		pomFileStr = pomFileGen.generate(pomFile);
		
		assertTrue(pomFileStr.contains("<groupId>org.apache.maven.plugins</groupId>"));
		assertTrue(pomFileStr.contains("<artifactId>maven-compiler-plugin</artifactId>"));
		assertTrue(pomFileStr.contains("<version>3.6.1</version>"));
		assertTrue(pomFileStr.contains("<configuration>"));
		assertTrue(pomFileStr.contains("<source>1.8</source>"));
		assertTrue(pomFileStr.contains("<target>1.8</target>"));
		assertTrue(pomFileStr.contains("</configuration>"));
	}
}
