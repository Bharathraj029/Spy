package com.spy.screenOne;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class AddBtnFunctionality {

	// "Implement Add Button (Field Name,Test Data,Perform,Id/Xpath/Name) should be
	// added to map based on Field Name.

	private String fieldname1;
	private String testData;
	private String operationTOPerform;
	private String element;
	private String elemType;
	// static Multimap<String, Date> map1 = ArrayListMultimap.create();
	Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

	public AddBtnFunctionality(String fieldName, String by,String testDate, String operation, String elemen) {

		this.fieldname1 = fieldName;
		this.testData = testDate;
		this.operationTOPerform = operation;
		this.element = elemen;
		this.elemType=by;

	}

	public Map<String, Map<String, String>> addFunction() {

		if (map.isEmpty()) {

			map.put(fieldname1, new HashMap<String, String>());
			map.get(fieldname1).put("BY", elemType);
			map.get(fieldname1).put("Test Data", testData);
			map.get(fieldname1).put("Perform", operationTOPerform);
			map.get(fieldname1).put("Element", element);
		} else {

			map.put(fieldname1, new HashMap<String, String>());
			map.get(fieldname1).put("BY", elemType);
			map.get(fieldname1).put("Test Data", testData);
			map.get(fieldname1).put("Perform", operationTOPerform);
			map.get(fieldname1).put("Element", element);
		}

		for (Entry<String, Map<String, String>> entry : map.entrySet()) {
			System.out.println("Parent key::" + entry.getKey());
			Map<String, String> entry1 = entry.getValue();
			for (Entry<String, String> entry2 : entry1.entrySet()) {

				System.out.println("Chile key :" + entry2.getKey());
				System.out.println("Child value::" + entry2.getValue());

			}

		}
		return map;

	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public Boolean validate() {

		if (fieldname1.isEmpty()) {

			AddBtnFunctionality.infoBox("Please enter the field name.", "Error");
			return false;

		}

		if(testData.isEmpty()) {
			
			AddBtnFunctionality.infoBox("Please enter Sample Test Data.", "Error");
			return false;
		}
		return true;
		
		
		
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * AddBtnFunctionality add = new AddBtnFunctionality("Username", "bharath",
	 * "sendKeys", "//*[@id='username'"); add.addFunction();
	 * 
	 * AddBtnFunctionality add1 = new AddBtnFunctionality("Username1", "bh",
	 * "click", "//*[@id='username'"); add1.addFunction();
	 * 
	 * }
	 */

}
