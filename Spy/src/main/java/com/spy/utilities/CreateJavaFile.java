package com.spy.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class CreateJavaFile {

	private WebDriver driverNae;
	private String fileName;

	public CreateJavaFile(WebDriver driver, String pathOfJavaFileCreate) {

		this.driverNae = driver;
		this.fileName = pathOfJavaFileCreate;

	}

	public String createJavaFile(String title) throws IOException {
		
		

		File javaCreatePath = new File(fileName + "\\" + title + ".java");
		if (javaCreatePath.exists()) {

			System.out.println(javaCreatePath.getAbsolutePath());

			System.out.println(javaCreatePath.getCanonicalPath());

			System.out.println(javaCreatePath.getName());

			System.out.println("File exist");

		} else {

			javaCreatePath.createNewFile();
			System.out.println(javaCreatePath.getAbsolutePath());

			System.out.println(javaCreatePath.getCanonicalPath());

			System.out.println(javaCreatePath.getName());

		}
		return javaCreatePath.getAbsolutePath();

		

	}

	

}
