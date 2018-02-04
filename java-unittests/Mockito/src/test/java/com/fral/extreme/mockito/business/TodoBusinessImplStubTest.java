package com.fral.extreme.mockito.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.fral.extreme.mockito.data.api.TodoService;
import com.fral.extreme.mockito.data.stub.TodoServiceStub;


/**
 * Problem in Stubbing
 * -  Dynamic Conditions
 * -  Service Definition
 * 
 * @author Franco
 *
 */
public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("MyUser");
		
		assertEquals(2, filteredTodos.size());
	}
	
}
