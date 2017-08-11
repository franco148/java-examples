package org.fral.examples;

import java.util.Arrays;
import java.util.List;

public class LedgerServiceImpl implements LedgerServiceInterface {

	String[] returnArray = {"entry1","entry2","entry3"};
	
	public List<String> getEntries(int count) {
		return Arrays.asList(returnArray);
	}

}
