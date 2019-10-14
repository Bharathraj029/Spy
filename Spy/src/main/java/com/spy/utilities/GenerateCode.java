package com.spy.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateCode {

	List<String> list = new ArrayList<String>();
	Map<String, String> map = new HashMap<String, String>();

	public List<String> fileWriteMethod(String path, String action, String variableName,String functType) throws IOException {
		FileWriter file = new FileWriter(new File(path), true);

		BufferedWriter bw = new BufferedWriter(file);

		bw.write(System.lineSeparator());
		if (action.equalsIgnoreCase("SendKeys")) {
			bw.write(" public void set" + variableName.substring(0, 1).toUpperCase()
					+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
					+ "(String " + variableName.toLowerCase().substring(0, 1)
					+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
					+ "_" + "){");
			list.add("this.set" + variableName.substring(0, 1).toUpperCase()
					+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
					+ "(" + variableName.toLowerCase().substring(0, 1)
					+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
					+ ");");
			
			bw.write(System.lineSeparator());
			if(functType.equalsIgnoreCase("JavaScript")) {
				bw.write("JavascriptExecutor myExecutor = ((JavascriptExecutor)driver );");
				bw.write("myExecutor.executeScript(\"arguments[0].value='"+variableName.toLowerCase().substring(0, 1)
						+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
						+ "_"+"';\", "+variableName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "")+");");
			}else {
				bw.write(" " + variableName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "") + ".sendKeys("
						+ variableName.toLowerCase().substring(0, 1)
						+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
						+ "_" + ");" + System.lineSeparator() + " }");
	
			}
			
		} else {

			bw.write("public void clk" + variableName.substring(0, 1).toUpperCase() + "(){");
			bw.write(System.lineSeparator());
			bw.write(variableName + ".click();");
			list.add(variableName + ".click();");
			// map.put("Click",variableName.substring(0, 1).toUpperCase());
		}

		bw.close();
		return list;

	}

	public void fileWritePageInit(String title, String filePath) throws IOException {

		FileWriter file = new FileWriter(new File(filePath), true);

		BufferedWriter bw = new BufferedWriter(file);
		bw.write(System.lineSeparator());
		bw.write("WebDriver driver;");
		bw.write(System.lineSeparator());
		bw.write(" public " + title + "(WebDriver driver){" + System.lineSeparator());
		bw.write("this.driver=driver;");
		bw.write("	PageFactory.initElements(driver, this);\r\n" + " }" + System.lineSeparator());
		bw.close();

	}

	public void chkImportWritePackage(String filePath, String valueToWrite, String title) throws IOException {

		System.out.println("Not present");
		FileWriter file = new FileWriter(new File(filePath), true);

		BufferedWriter bw = new BufferedWriter(file);

		bw.write(valueToWrite + System.lineSeparator());
		bw.write("import org.openqa.selenium.WebElement;" + System.lineSeparator());
		bw.write("import org.openqa.selenium.support.FindBy;" + System.lineSeparator());
		bw.write("import org.openqa.selenium.WebDriver;" + System.lineSeparator());
		bw.write("import org.openqa.selenium.support.PageFactory;" + System.lineSeparator());
		bw.write("import org.openqa.selenium.WebDriver;" + System.lineSeparator());

		bw.write("public class " + title + "{" + System.lineSeparator() + System.lineSeparator());
		bw.flush();

		bw.close();
	}

	public void writeElement(String fieldName, String by, String value, String filePath) throws IOException {

		// list.add(fieldName);

		if (by.startsWith("Id")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true));
			writer.newLine();
			writer.write(" @FindBy(id=\"" + value + "\")");
			writer.write(System.lineSeparator());
			writer.write(" WebElement " + fieldName.toLowerCase().replaceAll("[^a-zA-Z0-9	]", "") + ";");
			writer.write(System.lineSeparator());

			writer.flush();
			writer.close();

		} else if (by.startsWith("Name")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true));
			writer.newLine();
			writer.write(" @FindBy(name=\"" + value + "\")");
			writer.write(System.lineSeparator());
			writer.write(" WebElement " + fieldName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "") + ";");
			writer.write(System.lineSeparator());

			writer.flush();
			writer.close();

		} else if (by.startsWith("Xpath")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true));
			writer.newLine();
			writer.write(" @FindBy(xpath=\"" + value + "\")");
			writer.write(System.lineSeparator());
			writer.write(" WebElement " + fieldName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "") + ";");
			writer.write(System.lineSeparator());

			writer.flush();
			writer.close();

		} else if (by.startsWith("Class Name")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString(), true));
			writer.newLine();
			writer.write(" @FindBy(className=\"" + value + "\")");
			writer.write(System.lineSeparator());
			writer.write(" WebElement " + fieldName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "") + ";");
			writer.write(System.lineSeparator());

			writer.flush();
			writer.close();

		}

	}

	public void createFinalMethod(String path, String methodName, String action, String variableName)
			throws IOException {

		FileWriter file = new FileWriter(new File(path), true);

		BufferedWriter bw = new BufferedWriter(file);

		bw.write(System.lineSeparator());

		if (action.equalsIgnoreCase("SendKeys")) {

			bw.write("this.set" + variableName.substring(0, 1).toUpperCase()
					+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
					+ "(" + variableName.toLowerCase().substring(0, 1)
					+ variableName.substring(1, variableName.length() - 1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase()
					+ ");");
		} else {

			bw.write("this." + variableName + ".click();");
		}

		bw.flush();
		bw.close();

	}

	public String writeMethodName(List<String> list, String path, String methodName,List<Object> list2)
			throws IOException {

		StringBuilder sb = new StringBuilder();

		Iterator<String> itr = list.iterator();
		while (itr.hasNext()) {

			sb.append(itr.next());
			sb.append(",");

		}
		String str = sb.toString().replaceAll(",$", "");
		System.out.println(str);

		FileWriter file = new FileWriter(new File(path), true);

		BufferedWriter bw = new BufferedWriter(file);
		bw.write(System.lineSeparator());
		bw.write(" public void " + methodName + "(" + str + "){");
		Iterator<Object> it = list2.iterator();
		while (it.hasNext()) {
			bw.write(System.lineSeparator());
			bw.write(" " + it.next());

		}
		bw.write(System.lineSeparator());
		bw.write(" }");
		bw.write(System.lineSeparator());
		bw.write("}");
		bw.flush();
		bw.close();
		return str;
		

	}
	
	public String writeMethodName(List<String> list,List<String> list2, String path, String methodName)
			throws IOException {

		StringBuilder sb = new StringBuilder();

		Iterator<String> itr = list.iterator();
		while (itr.hasNext()) {

			sb.append(itr.next());
			sb.append(",");

		}
		String str = sb.toString().replaceAll(",$", "");
		System.out.println(str);

		FileWriter file = new FileWriter(new File(path), true);

		BufferedWriter bw = new BufferedWriter(file);
		bw.write(System.lineSeparator());
		bw.write(" public void " + methodName + "(" + str + "){");
		Iterator<String> it = list2.iterator();
		while (it.hasNext()) {
			bw.write(System.lineSeparator());
			bw.write(" " + it.next());

		}
		bw.write(System.lineSeparator());
		bw.write(" }");
		bw.write(System.lineSeparator());
		bw.write("}");
		bw.flush();
		bw.close();
		return str;
		

	}
	
	/*private void methDeclaration() {
		FileWriter file = new FileWriter(new File(path), true);

		BufferedWriter bw = new BufferedWriter(file);
		bw.write(System.lineSeparator());
		bw.write(" public void " + methodName + "(" + str + "){");
		Iterator<String> it = listeOfVar.iterator();
		while (it.hasNext()) {
			bw.write(System.lineSeparator());
			bw.write(" " + it.next());

		}
		bw.write(System.lineSeparator());
		bw.write(" }");
		bw.write(System.lineSeparator());
		bw.write("}");
		bw.flush();
		bw.close();
		
	}*/
	
	public void writeThisMethod(List<String> listeOfVar, String path) throws IOException {
		
		FileWriter file = new FileWriter(new File(path), true);

		BufferedWriter bw = new BufferedWriter(file);
		Iterator<String> it = listeOfVar.iterator();
		while (it.hasNext()) {
			bw.write(System.lineSeparator());
			bw.write(" " + it.next());

		}
		bw.write(System.lineSeparator());
		bw.write(" }");
		bw.write(System.lineSeparator());
		bw.write("}");
		bw.flush();
		bw.close();


		
	}
}
