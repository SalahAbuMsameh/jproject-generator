package com.apisoft.jpgen.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

import com.apisoft.jpgen.JProjectGenException;

/**
 * 
 * @author Salah Abu Msameh
 */
public class IOUtils {
	
	/**
	 * create directory(s)
	 * 
	 * @param dirName
	 * @throws JProjectGenException 
	 */
	public static void createDir(String dirName) throws JProjectGenException {
		try {
			Files.createDirectories(Paths.get(dirName));
		} catch (IOException e) {
			throw new JProjectGenException(e);
		}
	}
	
	/**
	 * 
	 * @param dirName
	 * @throws JProjectGenException
	 */
	public static void deleteDir(String dirName) throws JProjectGenException {
		
		try {
			Files.walk(Paths.get(dirName))
				.sorted(Comparator.reverseOrder())
				.map(Path::toFile)
				.forEach(File::delete);
		} 
		catch (IOException e) {
			throw new JProjectGenException(e);
		}
	}
	
	/**
	 * copy file
	 * 
	 * @param source
	 * @param target
	 * @throws JProjectGenException 
	 */
	public void copyFile(String source, String target) throws JProjectGenException {
		try {
			Files.copy(Paths.get(source), Paths.get(target));
		} catch (IOException e) {
			throw new JProjectGenException(e);
		}
	}
	
	/**
	 * 
	 * @param resourceFileName
	 * @param target
	 * @throws JProjectGenException 
	 */
	public static void copyResourceFile(String resourcePath, String resourceName, String target) throws JProjectGenException {
		
		String fullPath = resourcePath + "/" + resourceName;
		InputStream is = IOUtils.class.getResourceAsStream(resourcePath + "/" + resourceName);
		
		if(is == null) {
			throw new JProjectGenException("No resource found for resource name [" + fullPath + "]");
		}
		
		try {
			Files.copy(is, Paths.get(target + "/" + resourceName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new JProjectGenException(e);
		}
	}
	
	/**
	 * copy a directory with its all sub directories and files
	 * 
	 * @param source
	 * @param targer
	 */
	public static void copyDir(Path source, Path target) {
		try {
			Files.walkFileTree(source, new JProjectFileVisitor(source, target));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * write a file
	 * 
	 * @param className
	 * @param content
	 */
	public static void writeFile(String className, String content) {
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(className), Charset.forName("UTF-8"))) {
			writer.write(content);
			writer.close();
		} 
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Files visitor
	 */
	private static class JProjectFileVisitor extends SimpleFileVisitor<Path> {
		
		private Path source;
		private Path target;
		
		/**
		 * 
		 * @param source
		 * @param target
		 */
		public JProjectFileVisitor(Path source, Path target) {
			this.source = source;
			this.target = target;
		}
		
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			
			Path newDirectory = target.resolve(source.relativize(dir));
			
	        try {
	            Files.createDirectories(newDirectory);
	        }
	        catch (FileAlreadyExistsException ioException) {
	            return FileVisitResult.SKIP_SUBTREE; // skip processing
	        }
	        
	        return FileVisitResult.CONTINUE;
		}
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			
			Path newFile = target.resolve(source.relativize(file));
			
	        try {
	            Files.copy(file, newFile);
	        }
	        catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        
	        return FileVisitResult.CONTINUE;
		}
	}
}
