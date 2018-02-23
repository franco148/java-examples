package com.fral.extreme.mockito.business;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSizeMethod() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		
		assertEquals(2, listMock.size());
	}

	@Test
	public void letsMockListSizeMethod_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		//Argument matcher: listMock.get(anyInt())
		when(listMock.get(0)).thenReturn("eXtremeDevelopment");
		
		assertEquals("eXtremeDevelopment", listMock.get(0));
		assertEquals(null,  listMock.get(1));
	}
	
	@Test(expected=RuntimeException.class)
	public void letsMockListGet_ArgumentMatcher() {
		List listMock = mock(List.class);
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		
		listMock.get(0);
	}
	
	@Test(expected=RuntimeException.class)
	public void letsMockList_mixingUp() {
		List listMock = mock(List.class);
		when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException());
		listMock.subList(3,  20);
	}
}
