package com.nova.todomanagmentproject.service;

import com.nova.todomanagmentproject.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto save(TodoDto todoDto);
    TodoDto getTodo(String todoId);
    TodoDto updateTodo(TodoDto todoDto, String todoId);
    List<TodoDto> getAllTodos();
    void deleteTodo(String todoId);
    TodoDto completeTodo(String todoId);
    TodoDto inCompleteTodo(String todoId);
}
