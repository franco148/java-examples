package org.fral.examples;

public class Ledger {
	
	private String name;

	public Ledger() {
    }

    public Ledger(String name) {
        this.name = name;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
