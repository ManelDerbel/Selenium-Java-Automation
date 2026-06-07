package com.qa.opencart.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtils {

	private static final String TEST_DATA_CSV_PATH = "src/test/resources/testdata/";

	public static Object[][] getDataCSV(String csvName) {
		String csvFile = TEST_DATA_CSV_PATH + csvName + ".csv";
		List<String[]> rows = null;
		CSVReader reader = null;

		try {
			reader = new CSVReader(new FileReader(csvFile));
			rows = reader.readAll();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (rows == null) {
			return new Object[0][];
		}

		Object[][] data = new Object[rows.size()][];
		for (int i = 0; i < rows.size(); i++) {
			data[i] = rows.get(i);
		}

		return data;
	}
}
