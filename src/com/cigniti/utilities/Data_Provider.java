package com.cigniti.utilities;

import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;

//import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;

//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;

public class Data_Provider {

	// public static Property configProps=new Property("config.properties");
	/**
	 * Read data from excel sheet using data provider
	 * 
	 * @param sheetName
	 * 
	 * @throws Exception
	 * @Date 19/02/2013
	 * @Revision History
	 * 
	 */

	public static String[][] getTableArray(String sheetName) throws Exception {
		try {
			Workbook workbook = Workbook.getWorkbook(new File("Data\\MIGNG_TestData.xls"));
			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			String[][] tabArray = new String[rows - 1][cols];

			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					tabArray[i - 1][j] = sheet.getCell(j, i).getContents();
				}
			}
			workbook.close();
			return (tabArray);
		} catch (Exception e) {
			System.out.println(e + Thread.currentThread().getStackTrace()[1].getClassName() + " dataprovider");
			return null;
		}
	}
	
//	public static String[][] getTableIterator(String sheetName) throws Exception {
//		try {
//			Workbook workbook = Workbook.getWorkbook(new File(
//					"Data\\MIGNG_TestData.xls"));
//			Sheet sheet = workbook.getSheet(sheetName);
//			int rows = sheet.getRows();
//			int cols = sheet.getColumns();
//			String[][] tabArray = new String[rows - 1][cols];
//
//			for (int i = 1; i < rows; i++) {
//				for (int j = 0; j < cols; j++) {
//					tabArray[i - 1][j] = sheet.getCell(j, i).getContents();
//				}
//			}
//			workbook.close();
//			
//			ArrayList myData = new ArrayList();
//	        FileInputStream fis = new FileInputStream("Data\\MIGNG_TestData.xls");
//	        Workbook wb = (Workbook) WorkbookFactory.create(fis);
//	        Sheet sh = wb.getSheet(sheetName);
//	        int numOfRows = sheet.getRows();
//	        int numOfRows = sh.getLastRowNum();
//	        int numOfCols = sh.get
//	        String userName, pass, expTitle;
//	        for(int i=0; i<numOfRows; i++){
//	            userName = sh.getRow(i).getCell(0).getStringCellValue();
//	            pass = sh.getRow(i).getCell(1).getStringCellValue();
//	            expTitle = sh.getRow(i).getCell(2).getStringCellValue();
//	            myData.add(new Object[]{userName,pass,expTitle});
//	        }
//	        return myData.iterator();
//	        
//			return (tabArray);
//		} catch (Exception e) {
//			System.out.println(e
//					+ Thread.currentThread().getStackTrace()[1].getClassName()
//					+ " dataprovider");
//			return null;
//		}
//	}

//	public static void setTableData(String sheetName, String uname,	String text, int offset) throws Exception {
//		Workbook work = Workbook.getWorkbook(new File("Data\\MIGNG_TestData.xls"));
//		WritableWorkbook writer = Workbook.createWorkbook(new File(
//				"TestData\\TestData.xls"), work);
//		// Workbook workbook = Workbook.getWorkbook(new
//		// File("TestData\\TestData.xls"));
//		WritableSheet sheet = writer.getSheet(sheetName);
//		LabelCell cell = sheet.findLabelCell(uname);
//		// Cell cell = sheet.findCell(uname, 0, 0, 1000, 1000, false);
//
//		sheet.addCell(new jxl.write.Label(cell.getColumn() + offset, cell
//				.getRow(), text));
//
//		writer.write();
//		writer.close();
//		work.close();
//	}

}
