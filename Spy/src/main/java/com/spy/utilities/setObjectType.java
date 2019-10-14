package com.spy.utilities;

import java.util.ArrayList;
import java.util.List;

public class setObjectType {

	private String element;

	public void setElement(String elementType) {

		this.element = elementType;

	}

	public String setType() {

		String objectType = null;
		List<String> list = new ArrayList<String>();
		list.add("password");
		list.add("email");
		list.add("text");
		list.add("search");
		list.add("number");
		list.add("tel");
		list.add("url");

		if (list.contains(element)) {

			objectType = "TextField";
		}

		else if (element.contains("submit")) {

			objectType = "Button";

		}

		// ||element.contains("radio")||element.contains("checkbox")

		else if (element.contains("radio")) {

			objectType = "RadioButton";
		} else if (element.contains("checkbox")) {

			objectType = "CheckBox";
		} else {

			objectType = "";
		}
		return objectType;

	}

}
