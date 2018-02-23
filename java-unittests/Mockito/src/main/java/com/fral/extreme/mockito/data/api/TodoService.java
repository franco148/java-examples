package com.fral.extreme.mockito.data.api;

import java.util.List;

// External Service - Lets say this comes from WunderList
//Create TodoServiceStub
//Test TodoBusinessImpl using TodoServiceStub
public interface TodoService {
	public List<String> retrieveTodos(String user);
	public void deleteTodo(String todo);
}