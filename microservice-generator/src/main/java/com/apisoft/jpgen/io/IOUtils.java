package com.apisoft.jpgen.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 
 * @author Salah Abu Msameh
 */
public class IOUtils {

	/**
	 * 
	 * @param dirName
	 */
	public static void createDir(String dirName) {
		try {
			Files.createDirectories(Paths.get(dirName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 */
	public void copyFile(String source, String target) {
		try {
			Files.copy(Paths.get(source), Paths.get(target));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
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

//		@Override
//		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//			
//			Path newDirectory = target.resolve(source.relativize(dir));
//			
//	        try{
//	            Files.copy(dir,newDirectory);
//	        }
//	        catch (FileAlreadyExistsException ioException){
//	            //log it and move
//	            return FileVisitResult.SKIP_SUBTREE; // skip processing
//	        }
//
//	        return FileVisitResult.CONTINUE;
//		}
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			
			Path newFile = target.resolve(source.relativize(file));
			
	        try {
	            Files.copy(file, newFile);
	        }
	        catch (IOException ex){
	            ex.printStackTrace();
	        }
	        
	        return FileVisitResult.CONTINUE;
		}
	}
}
