package com.fral.extreme.mockito.business;

import java.util.ArrayList;
import java.util.List;

import com.fral.extreme.mockito.data.api.TodoService;


//TodoBusinessImpl => SUT (System under test)
//TodoService => Dependency

// STUB is nothing but a sample implementation of a particular class.
public class TodoBusinessImpl {
	private TodoService todoService;

	TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retrieveTodosRelatedToSpring(String user) {
		List<String> filteredTodos = new ArrayList<String>();
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}
	
	public void deleteTodosNotRelatedToSpring(String user) {
		
		List<String> todos = todoService.retrieveTodos(user);
		
		for (String todo : todos) {
			if (!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}
	}
}