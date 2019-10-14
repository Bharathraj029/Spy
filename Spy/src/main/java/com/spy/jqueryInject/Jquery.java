package com.spy.jqueryInject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Jquery {
	
	public static String retId(String path,WebDriver driver) throws FileNotFoundException, InterruptedException {
		
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("var script = document.createElement('script');script.src = \"https://code.jquery.com/jquery-3.4.1.min.js\";document.getElementsByTagName('head')[0].appendChild(script);");
		isjQueryLoaded(driver);
		js1.executeScript("$.noConflict();");
		isjQueryLoaded(driver);
		Scanner sc = new Scanner(
				new FileInputStream(new File(path)));
		String inject = "";
		while (sc.hasNext()) {
			String[] s = sc.next().split("\r\n");
			for (int i = 0; i < s.length; i++) {
				inject += s[i];
				inject += " ";
			}
		}
		
		//js1.executeScript("window.localStorage.removeItem('XPATH');");
		System.out.println(inject);
		//js1.executeScript("var s=window.document.createElement('script'); s.src="+inject+"; window.document.head.appendChild(s);");
		//js1.executeScript("window.jquery("+inject+")");
		js1.executeScript(inject);
		//	String xpath = (String) js1.executeScript("return window.localStorage.getItem('items');");

		//System.out.println(xpath);
		return "Success";
	}
	public static String close(String path,WebDriver driver) throws FileNotFoundException {
		
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		
		isjQueryLoaded(driver);
	//	js1.executeScript("$.noConflict();");
		isjQueryLoaded(driver);
		Scanner sc = new Scanner(
				new FileInputStream(new File(path)));
		String inject = "";
		while (sc.hasNext()) {
			String[] s = sc.next().split("\r\n");
			for (int i = 0; i < s.length; i++) {
				inject += s[i];
				inject += " ";
			}
		}
		js1.executeScript(inject);
		
		return "Success";
		
		
		
	}
	
	
	public static void isjQueryLoaded(WebDriver driver) {
	    System.out.println("Waiting for ready state complete");
	    (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                JavascriptExecutor js = (JavascriptExecutor) d;
	                String readyState = js.executeScript("return document.readyState").toString();
	                System.out.println("Ready State: " + readyState);
	                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
	            }
	        });
	}
}
