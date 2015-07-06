package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.data.filtering.MenuItemFilterTestData;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;

public class MenuItemFilterTestDataReader extends ExcelDataSupport implements
		ExcelDataReader<List<MenuItemFilterTestData>> {

	@Override
	public List<MenuItemFilterTestData> readData(final XSSFWorkbook workbook) throws IOException {
		List<MenuItemFilterTestData> filterTestData = new ArrayList<MenuItemFilterTestData>();
		Chains chains = (Chains)testSession.get("CHAIN_TEST_DATA");
		Restaurants restaurants = (Restaurants)testSession.get("RESTAURANT_TEST_DATA");
		ReferenceStore referenceStore = (ReferenceStore)testSession.get(REFERENCE_STORE);
		XSSFSheet sheet = workbook.getSheet("MenuItemFiltering");
		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				if (getCellValueAsString(row.getCell(0)) != null) {
					MenuItemFilterTestData testData = new MenuItemFilterTestData();
					Chain chain = chains.getChainByName(getCellValueAsString(row.getCell(0)));
					testData.setChainId(chain.getId());
					Restaurant restaurant = restaurants.getRestaurantByStreet(getCellValueAsString(row.getCell(1)));
					testData.setRestaurantId(restaurant.getId());
					testData.setCategoryId(referenceStore.getCategory(chain.getId(), restaurant.getId(), getCellValueAsString(row.getCell(2))).getId());
					
					String name = getCellValueAsString(row.getCell(3));
					if (name != null) {
						testData.addFilterData("name", name);
					}
					
					List<String> expSizeNames = getCellValueAsStringList(row.getCell(4));
					if (expSizeNames != null) {
						testData.setExpectedSizes(expSizeNames);
					}
					
					List<String> expContentNames = getCellValueAsStringList(row.getCell(5));
					if (expSizeNames != null) {
						testData.setExpectedContent(expContentNames);
					}
					filterTestData.add(testData);
				}
			}
		}
		return filterTestData;
	}

}
