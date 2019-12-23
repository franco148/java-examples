package com.eextreme.tdd;

public class StockManager {
	
	private ExternalIsbnDataService webService;
	private ExternalIsbnDataService dbService;
	
	
	public void setWebService(ExternalIsbnDataService webService) {
		this.webService = webService;
	}

	public void setDbService(ExternalIsbnDataService dbService) {
		this.dbService = dbService;
	}


	// This logic is when we were seeing examples regarding to STUBs	
	/*public String getLocatorCode(String isbn) {
		Book book = webService.lookup(isbn);
		StringBuilder locator = new StringBuilder();
		locator.append(isbn.substring(isbn.length() - 4));
		locator.append(book.getAuthor().substring(0, 1));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();
	}*/
	
	public String getLocatorCode(String isbn) {
		Book book = dbService.lookup(isbn);
		if (book == null) {
			book = webService.lookup(isbn);			
		}
		
		StringBuilder locator = new StringBuilder();
		locator.append(isbn.substring(isbn.length() - 4));
		locator.append(book.getAuthor().substring(0, 1));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();
	}

}
