package com.spy.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spy.main.SpyMain;

public class Validation {

	public static boolean validateField(String methodname,String className) {

		if (methodname.isEmpty()) {

			AddBtnFunctionality.infoBox("Please enter the Method name.", "Error");
			return false;

		}

		if (className.isEmpty()) {

			AddBtnFunctionality.infoBox("Please enter class name.", "Error");
			return false;

		}
		return true;

	}

	public static boolean validateSpecilChar(String methodNae, String className) {

		if (methodNae.length() > 0) {
			Pattern pat = Pattern.compile("[^a-zA-z0-9]");
			Matcher match = pat.matcher(methodNae);
			boolean bool = match.find();
			if (bool == true) {
				SpyMain.infoBox("Method Name: Special characters are not allowed.", "Error");
				return false;

			} else {
				return true;
			}
		} else if (className.length() > 0) {
			if (methodNae.length() > 0) {
				Pattern pat = Pattern.compile("[^a-zA-z0-9]");
				Matcher match = pat.matcher(className);
				boolean bool = match.find();
				if (bool == true) {

					SpyMain.infoBox("Class Name: Special characters are not allowed.", "Error");
					return false;

				} else {
					return true;
				}
			}

		}
		// return rootPaneCheckingEnabled;
		return false;

	}

}
