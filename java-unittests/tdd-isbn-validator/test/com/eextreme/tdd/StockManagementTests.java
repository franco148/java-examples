package com.eextreme.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockManagementTests {

	@Test
	public void testCanGetACorrectLocatorCode() {
		
		ExternalIsbnDataService testWebService = new ExternalIsbnDataService() {
			
			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
			}
		};
		
		ExternalIsbnDataService testDbService = new ExternalIsbnDataService() {
			
			@Override
			public Book lookup(String isbn) {
				return null;
			}
		};
		
		StockManager stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDbService(testDbService);
		
		String isbn = "0140177396";		
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

}
