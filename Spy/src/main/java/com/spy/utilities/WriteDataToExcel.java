package com.spy.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataToExcel {

	public String setData(String path, String sheetName, int row, String colname, String value) throws IOException {
		
		XSSFWorkbook wb;
		
			FileInputStream fi = new FileInputStream(new File(path));
			wb = new XSSFWorkbook(fi);
			XSSFSheet sheet = wb.getSheet(sheetName);
			XSSFRow r = sheet.getRow(0);
			int count = 0;
			XSSFCell cell;
			for (int i = 0; i < r.getLastCellNum(); i++) {

				if (r.getCell(i).getStringCellValue().equals(colname)) {
					count = i;
					break;
				}

			}

			r = sheet.getRow(row);
			if (r == null)
				r = sheet.createRow(row);
			cell = r.getCell(count);

			if (cell == null)
				cell = r.createCell(count);
			cell.setCellValue(value);
			FileOutputStream fo = null;
			try {
			fo = new FileOutputStream(new File(path));
			cell.setCellValue(value);
			wb.write(fo);
			}
		 finally {
			fo.close();
		}

		return colname;

	}

	/*public static void main(String[] args) throws IOException {
		
		WriteDataToExcel wd = new WriteDataToExcel();
		
		
		String path="C:\\Users\\bs009\\Desktop\\NG\\AutomationTestData\\write.xlsx";
		String sheetName="test";
		int row=1;
		String colname="name";
		String value="hi";
		wd.setData(path, sheetName, row, colname, value);
	}*/
	
}
