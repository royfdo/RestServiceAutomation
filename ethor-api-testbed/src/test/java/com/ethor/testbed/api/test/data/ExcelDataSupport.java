package com.ethor.testbed.api.test.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;

import com.ethor.testbed.api.test.AbstractAPITest;

public abstract class ExcelDataSupport extends AbstractAPITest {

	protected static String getCellValueAsString(Cell cell) {
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return null;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf((int) cell.getNumericCellValue());
		} else {
			return cell.getStringCellValue().trim();
		}
	}

	protected static List<String> getCellValueAsStringList(Cell cell) {
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return null;
		} else {
			List<String> strings = new ArrayList<String>();
			String multiValuedStr = cell.getStringCellValue().trim();
			if (multiValuedStr.indexOf("|") > 0) {
				StringTokenizer stringTokenizer = new StringTokenizer(multiValuedStr, "|");
				while (stringTokenizer.hasMoreTokens()) {
					strings.add(stringTokenizer.nextToken().trim());
				}
			} else {
				strings.add(multiValuedStr.trim());
			}
			return strings;
		}
	}

	protected static Set<String> getCellValueAsStringSet(Cell cell) {
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return null;
		} else {
			Set<String> strings = new HashSet<String>();
			String multiValuedStr = cell.getStringCellValue().trim();
			if (multiValuedStr.indexOf("|") > 0) {
				StringTokenizer stringTokenizer = new StringTokenizer(multiValuedStr, "|");
				while (stringTokenizer.hasMoreTokens()) {
					strings.add(stringTokenizer.nextToken().trim());
				}
			} else {
				strings.add(multiValuedStr.trim());
			}
			return strings;
		}
	}

}
