package com.fral.extreme.mockito.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fral.extreme.mockito.data.api.TodoService;
import com.fral.extreme.mockito.data.stub.TodoServiceStub;


/**
 * What is mocking?
 * Mocking is creating objects that simulate the behavior of real objects.
 * Unlike Stubs, mocks can be dynamically created from code - at runtime.
 * Mocks offer more functionality than stubbing.
 * You can verify method calls and a lot of other things.
 * 
 * @author Franco
 *
 */
public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
				
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
				
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(0, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		//Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
				
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		//Then		
		assertThat(filteredTodos.size(), is(2));
	}
	
}
