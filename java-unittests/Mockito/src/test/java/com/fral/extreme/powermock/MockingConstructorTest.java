package com.fral.extreme.powermock;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fral.extreme.mockito.business.TodoBusinessImpl;
import com.fral.extreme.mockito.data.api.TodoService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class MockingConstructorTest {

	//Prepare the Class for test
	//PrepareForTest => SystemUnderTest.class ArrayList.class
	//Override the constructor
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Mock
	ArrayList mocklist;

	//In a good project we should avoid to do this.
	@Test
	public void testContructor() throws Exception {
		List<Integer> allStats = Arrays.asList(1, 2, 3);
		
		when(mocklist.size()).thenReturn(10);
		
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mocklist);
		
		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		
		assertEquals(10, size);
		
	}

}