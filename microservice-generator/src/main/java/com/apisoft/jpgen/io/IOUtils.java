package com.apisoft.jpgen.io;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 
 * @author Salah Abu Msameh
 */
public class IOUtils {

	/**
	 * 
	 * @param source
	 * @param target
	 */
	public void copyDir(String source, String target) {
		
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
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			
			Path newDirectory = target.resolve(source.relativize(dir));
			
	        try{
	            Files.copy(dir,newDirectory);
	        }
	        catch (FileAlreadyExistsException ioException){
	            //log it and move
	            return FileVisitResult.SKIP_SUBTREE; // skip processing
	        }

	        return FileVisitResult.CONTINUE;
		}
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			// TODO Auto-generated method stub
			return super.visitFile(file, attrs);
		}
	}
}
