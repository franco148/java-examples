package com.eextreme.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockManagementTests {

	@Test
	public void testCanGetACorrectLocatorCode() {
		
		ExternalIsbnDataService testService = new ExternalIsbnDataService() {
			
			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
			}
		};
		
		String isbn = "0140177396";
		StockManager stockManager = new StockManager(testService);
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

}
