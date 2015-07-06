package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.customer.Address;
import com.ethor.testbed.api.domain.customer.Customer;
import com.ethor.testbed.api.domain.customer.Customers;
import com.ethor.testbed.api.domain.customer.PhoneNumber;
import com.ethor.testbed.api.domain.customer.PhoneNumbers;

public class CustomerTestDataReader extends ExcelDataSupport implements ExcelDataReader<Customers> {

	@Override
	public Customers readData(final XSSFWorkbook workbook) throws IOException {
		XSSFSheet sheet = workbook.getSheet("Customer");
		Customers customers = new Customers();
		List<Customer> customerList = new ArrayList<Customer>();
		for (Row row : sheet) {
			if (row.getRowNum() > 1) {
				if (getCellValueAsString(row.getCell(0)) != null) {
					Customer customer = new Customer();
					customer.setId(getCellValueAsString(row.getCell(0)));
					customer.setEmail(getCellValueAsString(row.getCell(1)));
					customer.setFirstName(getCellValueAsString(row.getCell(2)));
					customer.setLastName(getCellValueAsString(row.getCell(3)));
					customer.setGender(getCellValueAsString(row.getCell(4)));
					customer.setBirthDate(getCellValueAsString(row.getCell(5)));
					customer.setBillingAddress(extractBillingAddress(row));
					PhoneNumbers phoneNumbers = new PhoneNumbers();
					phoneNumbers.add(extractPhoneNumber(row));
					customer.setPhoneNumbers(phoneNumbers);
					customerList.add(customer);

				}
			}
		}
		customers.setCustomers(customerList);
		return customers;
	}

	private PhoneNumber extractPhoneNumber(Row row) {
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setType(getCellValueAsString(row.getCell(14)));
		phoneNumber.setAreaCode(getCellValueAsString(row.getCell(15)));
		phoneNumber.setNumber(getCellValueAsString(row.getCell(16)));
		return phoneNumber;
	}

	private Address extractBillingAddress(final Row row) {
		Address address = new Address();
		address.setType(getCellValueAsString(row.getCell(6)));
		address.setSuiteNumber(getCellValueAsString(row.getCell(7)));
		address.setStreetNumber(getCellValueAsString(row.getCell(8)));
		address.setStreet(getCellValueAsString(row.getCell(9)));
		address.setCity(getCellValueAsString(row.getCell(10)));
		address.setPostalZip(getCellValueAsString(row.getCell(11)));
		address.setProvState(getCellValueAsString(row.getCell(12)));
		address.setCountry(getCellValueAsString(row.getCell(13)));
		return address;
	}
}
