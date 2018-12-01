package com.fral.extreme.mockito.business;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.CoreMatchers;
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
		//The following is not possible, because the combination of a generic and specific does not work in mockito. 
		when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException());
		listMock.subList(3,  20);
	}
	
	/**
	 * As a good practice we can have the following parts in a Unit tests
	 * 
	 * -Given - setup of the test
	 * 
	 * - When - actual method call
	 * 
	 * -Then - where check the asserts.
	 */
	@Test
	public void letsMockListGet_usingBDD() {
		//Given
		List listMock = mock(List.class);
		//Argument matcher: listMock.get(anyInt())
		given(listMock.get(anyInt())).willReturn("eXtremeDevelopment");
		
		//When
		Object firstElement = listMock.get(0);
		
		//Then
		assertThat(firstElement, CoreMatchers.<Object>is("eXtremeDevelopment"));
	}
}
