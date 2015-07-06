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

import com.ethor.testbed.api.domain.menuitem.AmountSelection;
import com.ethor.testbed.api.domain.menuitem.ContentGroups;
import com.ethor.testbed.api.domain.menuitem.Contents;
import com.ethor.testbed.api.domain.menuitem.MenuItem;
import com.ethor.testbed.api.domain.menuitem.MenuItems;
import com.ethor.testbed.api.domain.menuitem.PortionSelection;
import com.ethor.testbed.api.domain.menuitem.Size;

public class MenuItemTestDataReader extends ExcelDataSupport implements ExcelDataReader<MenuItems> {

	@Override
	public MenuItems readData(final XSSFWorkbook workbook) throws IOException {
		XSSFSheet sheet = workbook.getSheet("BasicMenuItemDetails");
		Map<String, List<Contents>> menuItemContentMap = getContents(workbook.getSheet("MenuItemContents"));
		MenuItems menuItems = new MenuItems();
		List<MenuItem> manueItemList = new ArrayList<MenuItem>();
		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
					MenuItem menuItem = new MenuItem();

					String testMenuItemId = getCellValueAsString(row.getCell(0));

					menuItem.addMetaData("ChainId", getId(getCellValueAsString(row.getCell(1))));
					menuItem.addMetaData("ChainName", getName(getCellValueAsString(row.getCell(1))));
					menuItem.addMetaData("RestaurantId", getId(getCellValueAsString(row.getCell(2))));
					menuItem.addMetaData("RestaurantName", getName(getCellValueAsString(row.getCell(2))));
					menuItem.addMetaData("CategoryId", getId(getCellValueAsString(row.getCell(3))));
					menuItem.addMetaData("CategoryName", getName(getCellValueAsString(row.getCell(3))));

					menuItem.setName(getName(getCellValueAsString(row.getCell(4))));
					menuItem.setId(getId(getCellValueAsString(row.getCell(4))));
					menuItem.setDescription(getName(getId(getCellValueAsString(row.getCell(5)))));
					menuItem.setImage(getName(getId(getCellValueAsString(row.getCell(6)))));

					List<String> sizeList = getCellValueAsStringList(row.getCell(7));
					List<Size> sizes = new ArrayList<Size>();
					for (String name : sizeList) {
						Size size = new Size();
						size.setName(name);
						sizes.add(size);
					}
					menuItem.setSizes(sizes);

					List<ContentGroups> contentGroupList = new ArrayList<ContentGroups>();
					ContentGroups contentGroups = new ContentGroups();
					contentGroups.setMinContents(getCellValueAsString(row.getCell(8)));
					contentGroups.setMaxContents(getCellValueAsString(row.getCell(9)));

					contentGroups.setContents(menuItemContentMap.get(testMenuItemId));
					contentGroupList.add(contentGroups);
					menuItem.setContentGroups(contentGroupList);
					manueItemList.add(menuItem);
				}

			}
		}
		menuItems.setMenuItems(manueItemList);

		return menuItems;
	}

	private String getId(final String idName) {
		int lastIndexOf = idName.lastIndexOf("]");
		if (lastIndexOf >= 0) {
			return idName.substring(1, lastIndexOf);
		}
		return idName;
	}

	private String getName(final String idName) {
		int lastIndexOf = idName.lastIndexOf("]");
		if (lastIndexOf >= 0) {
			return idName.substring(lastIndexOf + 1).trim();
		}
		return idName;
	}

	private Map<String, List<Contents>> getContents(final XSSFSheet sheet) {
		Map<String, List<Contents>> menuItemContentMap = new HashMap<String, List<Contents>>();

		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
					String testMenuItemId = getCellValueAsString(row.getCell(0));

					Contents content = new Contents();
					content.setName(getCellValueAsString(row.getCell(1)));
					content.setPortionSelections(getPortionSelections(row.getCell(2)));
					content.setAmountSelections(getAmountSelections(row.getCell(3)));

					if (menuItemContentMap.containsKey(testMenuItemId)) {
						List<Contents> contents = menuItemContentMap.get(testMenuItemId);
						contents.add(content);
					} else {
						List<Contents> contents = new ArrayList<Contents>();
						contents.add(content);
						menuItemContentMap.put(testMenuItemId, contents);
					}
				}
			}
		}

		return menuItemContentMap;
	}

	private List<PortionSelection> getPortionSelections(Cell cell) {
		List<PortionSelection> portionSelections = new ArrayList<PortionSelection>();
		List<String> portions = getCellValueAsStringList(cell);
		for (String portionString : portions) {
			PortionSelection portionSelection = new PortionSelection();
			portionSelection.setName(portionString);
			portionSelections.add(portionSelection);
		}
		return portionSelections;
	}

	private List<AmountSelection> getAmountSelections(Cell cell) {
		List<AmountSelection> amountSelections = new ArrayList<AmountSelection>();
		List<String> amounts = getCellValueAsStringList(cell);

		for (String amountString : amounts) {
			AmountSelection amountSelection = new AmountSelection();
			amountSelection.setName(amountString);
			amountSelections.add(amountSelection);
		}

		return amountSelections;
	}

}
