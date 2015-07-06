package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CustomerChainMapDataReader extends ExcelDataSupport implements ExcelDataReader<Map<String, List<String>>> {

	private static final CustomerChainMapDataReader instance = new CustomerChainMapDataReader();

	private CustomerChainMapDataReader() {

	}

	@Override
	public Map<String, List<String>> readData(XSSFWorkbook workbook) throws IOException {

		XSSFSheet sheet = workbook.getSheet("CustomerChainMap");
		Map<String, List<String>> cusChainMap = new HashMap<String, List<String>>();
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
					if (cusChainMap.containsKey(getCellValueAsString(row.getCell(0)))) {
						cusChainMap.get(getCellValueAsString(row.getCell(0))).add(getCellValueAsString(row.getCell(1)));
					} else {
						List<String> chainIdList = new ArrayList<String>();
						chainIdList.add(getCellValueAsString(row.getCell(1)));
						cusChainMap.put(getCellValueAsString(row.getCell(0)), chainIdList);
					}
				}
			}
		}

		return cusChainMap;
	}

	public static CustomerChainMapDataReader getInstance() {
		return instance;
	}

}
