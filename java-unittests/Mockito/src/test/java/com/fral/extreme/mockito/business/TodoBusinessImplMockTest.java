package com.fral.extreme.mockito.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

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
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD2() {
		//Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
				
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then: Verifying that deleteTodo method has called in TodoService with "Learn to Dance" parameter.		
		//verify(todoServiceMock).deleteTodo("Learn to Dance");
		then(todoServiceMock).should().deleteTodo("Learn to Dance");
		
		//verifying how many times is called the method
		//verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		then(todoServiceMock).should(times(1)).deleteTodo("Learn to Dance");
		
		//verifying that a method never is called when:
		//verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_argumentCapture() {
		
		//Declare an Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		//Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
				
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then:
		//Define an Argument Captor on specific method call
		//Capture the argument.
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));		
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_argumentCapture_multipleTimes() {
		
		//Declare an Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		//Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Rock And Roll", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
				
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then:
		//Define an Argument Captor on specific method call
		//Capture the argument.
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));		
	}
	
}
