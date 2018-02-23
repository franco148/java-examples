package com.fral.extreme.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void testSpy_InitialTest() {
		//Mocks return default values.
		List arrayListMock = mock(ArrayList.class);
		assertEquals(0, arrayListMock.size());
		
		stub(arrayListMock.size()).toReturn(5);
		arrayListMock.add("Dummy");//This operation does not affect when we are working with mocks.
		assertEquals(5, arrayListMock.size());
		
		//If i need to track operations over a mock we need to use spy
	}
	
	@Test
	public void testSpy_InitialTest_withSpy() {
		//The following statement is like creating a real arrayList
		List arrayListSpy = spy(ArrayList.class);
		assertEquals(0, arrayListSpy.size());
		arrayListSpy.add("Dummy");
		assertEquals(1, arrayListSpy.size());
		arrayListSpy.remove("Dummy");
		assertEquals(0, arrayListSpy.size());
		
		
	}
	
	//Spy is also called partial Mock
	@Test
	public void testSpy_InitialTest_withSpy_overridingMethods() {
		//The following statement is like creating a real arrayList
		List arrayListSpy = spy(ArrayList.class);
		stub(arrayListSpy.size()).toReturn(5);
		assertEquals(5, arrayListSpy.size());
		
		//The following is not going to work.
//		arrayListSpy.add("Dummy");
//		assertEquals(6, arrayListSpy.size());
	}
	
	@Test
	public void testSpy_InitialTest_withSpy_overridingMethods2() {
		//The following statement is like creating a real arrayList
		List arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Dummy");
		verify(arrayListSpy).add("Dummy");
		verify(arrayListSpy, never()).clear();
	}

}
