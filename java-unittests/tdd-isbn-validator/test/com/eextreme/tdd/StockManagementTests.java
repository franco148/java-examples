package com.eextreme.tdd;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

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
	
	/**
	 * Here we need another concept and this is called a MOCK. A mock is similar to a stub we've already built
	 * but with a mark that we get additional features. We get to find out whether or not a method was caught so we
	 * can write a line of code.
	 */
	@Test
	public void databaseIsUsedIfDataIsPresent() {
		ExternalIsbnDataService databaseService = mock(ExternalIsbnDataService.class);
		ExternalIsbnDataService webService = mock(ExternalIsbnDataService.class);
		
		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
		
		StockManager stockManager = new StockManager();
		stockManager.setWebService(webService);
		stockManager.setDbService(databaseService);
		
		String isbn = "0140177396";		
		String locatorCode = stockManager.getLocatorCode(isbn);
		// assertEquals("7396J4", locatorCode);
		
		verify(databaseService, times(1)).lookup(isbn);
		verify(webService, times(0)).lookup(anyString());
	}
	
	@Test
	public void webServerIsUsedIfDataIsNotPresentInDatabase() {
		ExternalIsbnDataService databaseService = mock(ExternalIsbnDataService.class);
		ExternalIsbnDataService webService = mock(ExternalIsbnDataService.class);
		
		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
		
		StockManager stockManager = new StockManager();
		stockManager.setWebService(webService);
		stockManager.setDbService(databaseService);
		
		String isbn = "0140177396";		
		String locatorCode = stockManager.getLocatorCode(isbn);
		// assertEquals("7396J4", locatorCode);
		
		verify(databaseService, times(1)).lookup(isbn);
		verify(webService, times(1)).lookup(anyString());
	}

}
