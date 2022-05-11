package com.sunsigne.tuto.ressources;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileTask {

	////////// READ ////////////
	
	public String read(String path) {
		return read(null, path);
	}
	
	public String read(String valueToRead, String path) {

		File file = new File("ressources/" + path);
		Scanner scan = null;
		String content = "";

		try {
			if (file.exists()) {
				scan = new Scanner(file, "UTF-8");
				boolean flag = false;

				// read the whole file
				if (valueToRead == null) {
					while (scan.hasNextLine()) {
						if (!flag) {
							content = content.concat(scan.nextLine());
							flag = true;
						} else
							content = content.concat(String.format("%n" + scan.nextLine()));
					}
				}

				// read one specific value
				else {
					while (scan.hasNextLine()) {
						String line = scan.nextLine();
						if (line.split("=")[0].equalsIgnoreCase(valueToRead))
							content = line.split("=")[1];
					}
				}

				scan.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}
	
	////////// WRITE ////////////
	
	public void write(String path, String text) {
		write(null, path, text);
	}
	
	public void write(String valueToReplace, String path, String text) {
		
		File file = new File("ressources/" + path);
		String fileContent = read(path);
		String[] alllines = fileContent.split(System.getProperty("line.separator"));
		int size = alllines.length;
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(file);
			
			// write the whole file
			if (valueToReplace == null) {
				writer.write(text);
			}
			
			// write one specific value
			else {
				for (int i = 0; i < size; i++) {
					if (alllines[i].split("=")[0].equalsIgnoreCase(valueToReplace))
						writer.write(String.format(alllines[i].split("=")[0] + "=" + text + "%n"));
					else
						writer.write(String.format(alllines[i] + "%n"));
				}
			}
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
