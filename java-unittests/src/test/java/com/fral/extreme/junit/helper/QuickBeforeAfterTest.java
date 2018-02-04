package com.fral.extreme.junit.helper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickBeforeAfterTest {
	
	//This is going to be executed each time a unit test is executed, so we need to take into account.
	@Before
	public void setup() {
		System.out.println("Before Test");
	}

	@Test
	public void test1() {
		System.out.println("Test1 executed");
	}
	
	@Test
	public void test2() {
		System.out.println("Test2 executed");
	}
	
	@After
	public void teardown() {
		System.out.println("After test");
	}

}
/**
 * the result  of the test execution
 * 
Before Test
Test1 executed
After test
Before Test
Test2 executed
After test
 
 */
