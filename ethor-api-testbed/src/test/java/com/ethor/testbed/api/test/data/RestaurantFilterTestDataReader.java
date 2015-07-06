package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.test.data.filtering.RestaurantFilterTestData;

public class RestaurantFilterTestDataReader extends ExcelDataSupport implements
		ExcelDataReader<List<RestaurantFilterTestData>> {

	@Override
	public List<RestaurantFilterTestData> readData(final XSSFWorkbook workbook) throws IOException {
		List<RestaurantFilterTestData> filterTestData = new ArrayList<RestaurantFilterTestData>();
		Chains chains = (Chains)testSession.get("CHAIN_TEST_DATA");
		XSSFSheet sheet = workbook.getSheet("RestaurantFiltering");
		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				if (getCellValueAsString(row.getCell(0)) != null) {
					RestaurantFilterTestData testData = new RestaurantFilterTestData();
					testData.setChainId(chains.getChainByName(getCellValueAsString(row.getCell(0))).getId());
					String city = getCellValueAsString(row.getCell(1));
					if (city != null) {
						testData.addFilterData("city", city);
					}
					String postalZip = getCellValueAsString(row.getCell(2));
					if (postalZip != null) {
						testData.addFilterData("postalZip", postalZip);
					}
					String openFrom = getCellValueAsString(row.getCell(3));
					if (openFrom != null) {
						testData.addFilterData("openFrom", openFrom);
					}
					String openTo = getCellValueAsString(row.getCell(4));
					if (openTo != null) {
						testData.addFilterData("openTo", openTo);
					}
					String status = getCellValueAsString(row.getCell(5));
					if (status != null) {
						testData.addFilterData("status ", status);
					}

					List<String> expResNames = getCellValueAsStringList(row.getCell(6));
					if (expResNames != null) {
						testData.setExpectedRestaurants(expResNames);
					}

					String count = getCellValueAsString(row.getCell(7));
					if (count != null) {
						testData.setExpectedCount(Integer.parseInt(count));
					}

					filterTestData.add(testData);
				}
			}
		}

		return filterTestData;
	}

}
