package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;

public class ChainTestDataReader extends ExcelDataSupport implements ExcelDataReader<Chains> {

	@Override
	public Chains readData(final XSSFWorkbook workbook) throws IOException {

		XSSFSheet sheet = workbook.getSheet("Chains");
		Chains chains = new Chains();
		List<Chain> chainList = new ArrayList<Chain>();
		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
					Chain chain = new Chain();
					chain.setId(getCellValueAsString(row.getCell(0)));
					chain.setName(getCellValueAsString(row.getCell(1)));
					chainList.add(chain);
				}
			}
		}

		chains.setChains(chainList);
		return chains;
	}

}
