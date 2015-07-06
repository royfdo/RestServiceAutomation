package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.category.Categories;
import com.ethor.testbed.api.domain.category.Category;
import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.Restaurants;

public class CategoryTestDataReader extends ExcelDataSupport implements ExcelDataReader<Categories> {

	@Override
	public Categories readData(final XSSFWorkbook workbook) throws IOException {
		XSSFSheet sheet = workbook.getSheet("Categories");
		Chains chains = (Chains)testSession.get("CHAIN_TEST_DATA");
		Restaurants restaurants = (Restaurants)testSession.get("RESTAURANT_TEST_DATA");
		Categories categories = new Categories();
		List<Category> categoryList = new ArrayList<Category>();
		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
					Category category = new Category();
					Chain chain = chains.getChainByName(getCellValueAsString(row.getCell(0)));
					if (chain == null) {
						throw new IllegalArgumentException("Invalid chain id <" + getCellValueAsString(row.getCell(0))
								+ "> found while reading <Categories> spread sheet");
					}
					category.addMetaData("ChainId", chain.getId());
					Restaurant restaurant = restaurants.getRestaurantByStreet(getCellValueAsString(row.getCell(1)));
					if (restaurant == null) {
						throw new IllegalArgumentException("Invalid restaurant id <" + getCellValueAsString(row.getCell(1))
								+ "> found while reading <Categories> spread sheet");
					}
					category.addMetaData("RestaurantId", restaurant.getId());					
					category.setId(getCellValueAsString(row.getCell(2)));
					category.setName(getCellValueAsString(row.getCell(3)));
					categoryList.add(category);
				}
			}
		}

		categories.setCategories(categoryList);
		return categories;
	}

}
