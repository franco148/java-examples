package com.fral.extreme.mockito.data.stub;

import java.util.Arrays;
import java.util.List;

import com.fral.extreme.mockito.data.api.TodoService;


//Stub is nothing but a class which returns some kind of dummy data.
public class TodoServiceStub implements TodoService {
	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring",
				"Learn to Dance");
	}
}