package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static final String TEST_DATA_PATH_SHEET = "src\\test\\resources\\testdata\\UserRegistration.xlsx";

	public static Object[][] getTestData(String sheetName) {
		Object[][] data = null;
		FileInputStream fi = null;
		Workbook book = null;

		try {
			fi = new FileInputStream(TEST_DATA_PATH_SHEET);
			book = WorkbookFactory.create(fi);
			Sheet sheet = book.getSheet(sheetName);

			System.out.println("Total rows are: " + sheet.getLastRowNum() + "Total columns are: "
					+ sheet.getRow(0).getLastCellNum());

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fi != null) {
					fi.close();
				}
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}
}
