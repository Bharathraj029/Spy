package com.spy.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class TestDataFunction extends Thread {
	
	private DefaultTableModel dtm;
	private JTextField classVal;
	private String path;
	private JTextField methodName;
	private JTextField desc;
	//private JDialog dialo;
	//,JDialog dialog
	public TestDataFunction(DefaultTableModel dtm1,JTextField classValue,String filePath,JTextField mathodNameValue,JTextField descField) {
	
		this.dtm=dtm1;
		this.classVal=classValue;
		this.path=filePath;
		this.methodName=mathodNameValue;
		this.desc=descField;
		//this.dialo=dialog;
	}
	@Override
	public void run()  {

		
		WriteDataToExcel wd=new WriteDataToExcel();
		
		synchronized (this) {
			
		
		for (int k = 0; k < dtm.getRowCount(); k++) {

			CreateTestDataFile ct = new CreateTestDataFile(
					dtm.getValueAt(k, 0).toString(),
					dtm.getValueAt(k, 3).toString(), classVal.getText(),
					path);
			ct.generate();

		}

		try {
			CreateTestDataFile.createFunctions(path, classVal.getText(), methodName.getText(), desc.getText());
			int row=getLastRow(path, "Functions");
			System.out.println("Last row is  ::"+row);
			wd.setData(path, "Functions", row+1, "Method Name", methodName.getText());
			wd.setData(path, "Functions", row+1, "Class Name", classVal.getText());
			wd.setData(path, "Functions", row+1, "Description", desc.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*dialo.setVisible(false);
		MultiThreadApp.infoBox("File Generated Successfully.", "File Generation");*/
	}
		//notify();
	}

	private int getLastRow(String path,String sheetName) throws IOException {
		
		FileInputStream fi=new FileInputStream(new File(path));
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet(sheetName);
		int lastRow=sheet.getLastRowNum();
		fi.close();
		return lastRow;
		
		
		
		
	}
		
}
