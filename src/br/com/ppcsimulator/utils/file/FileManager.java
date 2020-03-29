package br.com.ppcsimulator.utils.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	public static void create(File file, String content) throws IOException {		  
		if (file.createNewFile()) {
		    System.out.println("File is created: "+file.getName());
		} else {
			file.delete();
			file.createNewFile();
		    System.out.println("File already exists.");
		}
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		System.out.println("Success!");
		writer.close();
	}

}
