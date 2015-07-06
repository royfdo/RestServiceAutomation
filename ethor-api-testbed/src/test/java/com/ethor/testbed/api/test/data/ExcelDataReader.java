package com.ethor.testbed.api.test.data;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelDataReader<T> {
	
	T readData(XSSFWorkbook workbook) throws IOException;

}
