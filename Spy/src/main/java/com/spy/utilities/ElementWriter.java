package com.spy.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;

public class ElementWriter {

	public static String createPath(WebDriver driver,String title) throws IOException {

		
		
		boolean check=false;
		String projectPath = System.getProperty("user.dir");
		String pathToCreate;
		String fPathToCrt = projectPath + "\\" + "src" + "\\" + "main" + "\\" + "java" + "\\" + "com" + "\\" + "temp"
				+ "\\" + "pages";

		File path = new File(fPathToCrt);

		if (path.exists()) {

			System.out.println("Path already exist");
			CreateJavaFile crtJavaFile=new  CreateJavaFile(driver, fPathToCrt);
			 pathToCreate=crtJavaFile.createJavaFile(title);
			//System.out.println(createDirs);
			
			check=false;

		} else {
			boolean createDirs = new File(fPathToCrt).mkdirs();
			CreateJavaFile crtJavaFile=new  CreateJavaFile(driver, fPathToCrt);
			pathToCreate=crtJavaFile.createJavaFile(title);
			System.out.println(createDirs);
			check=true;

		}

		System.out.println(fPathToCrt);
		return pathToCreate;

	}

/*	public static void main(String[] args) {

		ElementWriter.createPath();

	}*/

}
