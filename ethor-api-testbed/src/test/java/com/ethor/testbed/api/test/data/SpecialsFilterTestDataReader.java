package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.data.filtering.SpecialsFilterTestData;

public class SpecialsFilterTestDataReader extends ExcelDataSupport implements
		ExcelDataReader<List<SpecialsFilterTestData>> {

	@Override
	public List<SpecialsFilterTestData> readData(final XSSFWorkbook workbook) throws IOException {
		List<SpecialsFilterTestData> filterTestData = new ArrayList<SpecialsFilterTestData>();
		Chains chains = (Chains)testSession.get("CHAIN_TEST_DATA");
		Restaurants restaurants = (Restaurants)testSession.get("RESTAURANT_TEST_DATA");
		XSSFSheet sheet = workbook.getSheet("SpecialFiltering");
		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				if (getCellValueAsString(row.getCell(0)) != null) {
					SpecialsFilterTestData testData = new SpecialsFilterTestData();
					testData.setChainId(chains.getChainByName(getCellValueAsString(row.getCell(0))).getId());
					testData.setRestaurantId(restaurants.getRestaurantByStreet(getCellValueAsString(row.getCell(1))).getId());
					String type = getCellValueAsString(row.getCell(2));
					if (type != null) {
						testData.addFilterData("type", type);
					}
					String isVisible = getCellValueAsString(row.getCell(3));
					if (isVisible != null) {
						testData.addFilterData("isVisible", isVisible);
					}
					String isExclusive = getCellValueAsString(row.getCell(4));
					if (isExclusive != null) {
						testData.addFilterData("isExclusive", isExclusive);
					}
					String couponCode = getCellValueAsString(row.getCell(5));
					if (couponCode != null) {
						testData.addFilterData("couponCode", couponCode);
					}
					
					List<String> expSpecialNames = getCellValueAsStringList(row.getCell(6));
					if (expSpecialNames != null) {
						testData.setExpectedSpecials(expSpecialNames);
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
