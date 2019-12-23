package org.fral.examples;

import java.util.List;

public interface LedgerServiceInterface {

	List<String> getEntries(int count);
}
