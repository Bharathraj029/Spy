package com.spy.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateTestDataFile {

	private String field;
	private String data;
	private String sheetName;
	private String fPath;

	/*
	 * public static void main(String[] args) throws IOException {
	 * 
	 * CreateTestDataFile ct = new CreateTestDataFile("User Name:","admin");
	 * //ct.generateTestData(); ct.start();
	 * 
	 * 
	 * }
	 */
	public CreateTestDataFile(String fieldName, String testData, String name, String path) {

		this.field = fieldName;
		this.data = testData;
		this.sheetName = name;
		this.fPath = path;

	}

	public void generate() {

		File file = new File(fPath);
		XSSFWorkbook wb = null;
		FileInputStream fi = null;
		FileOutputStream fo;
		XSSFSheet sheet;
		 boolean exist=false;
		if (file.exists()) {

			System.out.println("File exists");
			try {
				fi = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				wb = new XSSFWorkbook(fi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("File created");
			wb = new XSSFWorkbook();
		}

		 
		if (wb.getNumberOfSheets() != 0) {

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {

				System.out.println("SheetName in CreatTestdat field method ::"+sheetName);
				if (wb.getSheetName(i).equalsIgnoreCase(sheetName)) {

					
					exist=true;
					System.out.println("Sheet already exist");
					
				} /*else {

					sheet = wb.createSheet(sheetName);
				}*/
			}

		} else {

			sheet = wb.createSheet(sheetName);
			exist=true;
		}

		if(exist==false) {
			sheet=wb.createSheet(sheetName);
		}else {
			
			
		}
		
		if (fi != null) {
			try {
				fi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			fo = new FileOutputStream(file);
			try {
				wb.write(fo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			createField(field, file.getAbsolutePath(), data);
			/*
			 * ReadDataFromExcel read=new ReadDataFromExcel(file.getAbsolutePath(),
			 * "Test Data", 1, field); read.readData();
			 */
			WriteDataToExcel wd = new WriteDataToExcel();
			System.out.println("Column name:;"+field);
			wd.setData(file.getAbsolutePath(), sheetName, 1, field.replaceAll("[^a-zA-Z0-9 ]", ""), data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createField(String fieldName, String path, String data) throws IOException {

		FileInputStream fi = new FileInputStream(new File(path));
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet sheet;
		XSSFCell cell;
		XSSFRow row;
		int column = 0;
		boolean found = false;
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {

			if (wb.getSheetName(i).equalsIgnoreCase(sheetName)) {

				sheet = wb.getSheet(sheetName);

				row = sheet.getRow(0);
				if (row == null)
					row = sheet.createRow(0);
				for (int j = 0; j < row.getLastCellNum(); j++) {

					if (row.getCell(j).getStringCellValue().equalsIgnoreCase(fieldName)) {

						column = j;
						found = true;
						break;

					}

				}

				if (row == null)
					row = sheet.createRow(1);

				/*
				 * for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++) {
				 * cell=row.getCell(j); if(cell==null) { cell=row.createCell(j); }
				 * 
				 * }
				 */
				if (found) {
					// column=row.getLastCellNum();
				} else {
					// row.
					if (row.getLastCellNum() == -1) {
						column = 0;
					} else if (row.getLastCellNum() == 1) {
						column = 1;
					} else {
						column = row.getLastCellNum() + 1;
					}

				}
				cell = row.getCell(column);

				if (cell == null)
					cell = row.createCell(column);
				cell.setCellValue(fieldName.replaceAll("[^a-zA-Z0-9 ]", ""));

				FileOutputStream fo = new FileOutputStream(new File(path));
				cell.setCellValue(fieldName.replaceAll("[^a-zA-Z0-9 ]", ""));
				wb.write(fo);
				fo.close();

			}

		}

	}

	public static void createFunctions(String path, String className, String methodName, String descrip) throws IOException {

		FileInputStream input = new FileInputStream(new File(path));
		XSSFWorkbook wb = new XSSFWorkbook(input);
		XSSFRow row = null;
		boolean exist1=false;
		if (wb.getNumberOfSheets() != 0) {

			for (int i = 0; i <wb.getNumberOfSheets(); i++) {

				if (wb.getSheetName(i).equalsIgnoreCase("Functions")) {

					System.out.println("Function sheet exist");
					exist1=true;
				} /*else {

					
				}*/
			}

		} else {

			wb.createSheet("Functions");
			XSSFSheet sheet1 = wb.getSheet("Functions");
			row = sheet1.getRow(0);
			if (row == null)
				row = sheet1.createRow(0);
			FileOutputStream fo = new FileOutputStream(new File(path));
			Cell c1=row.createCell(0);
			c1.setCellValue("Method Name");
			Cell c2=row.createCell(1);
			c2.setCellValue("Class Name");
			Cell c3=row.createCell(2);
			c3.setCellValue("Description");
			
			wb.write(fo);
			fo.close();

		}
		
		if(exist1==false) {
			wb.createSheet("Functions");
			XSSFSheet sheet1 = wb.getSheet("Functions");
			row = sheet1.getRow(0);
			if (row == null)
				row = sheet1.createRow(0);
			FileOutputStream fo = new FileOutputStream(new File(path));
			Cell c1=row.createCell(0);
			c1.setCellValue("Method Name");
			Cell c2=row.createCell(1);
			c2.setCellValue("Class Name");
			Cell c3=row.createCell(2);
			c3.setCellValue("Description");
			
			wb.write(fo);
			fo.close();

		}else {
			
			System.out.println("Already exist");
		}

	}

}
