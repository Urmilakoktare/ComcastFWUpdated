package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws Throwable {
		//FileInputStream fis = new FileInputStream("./testdata/testScriptdata.xlsx");
		FileInputStream fis = new FileInputStream("./testdata/testdataorg.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount(String sheet) throws Throwable {
		FileInputStream fis = new FileInputStream("./testdata/testdataorg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheet).getLastRowNum();
		return rowcount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int celNum,String data) throws Throwable {
		FileInputStream fis = new FileInputStream("./testdata/testdataorg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
		
		FileOutputStream fos =new FileOutputStream(".tTestdata/testdataorg.xlsx");
		wb.write(fos);
		wb.close();
	}
}
