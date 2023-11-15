package com.nova.todomanagmentproject.service;

import com.nova.todomanagmentproject.dto.TodoDto;

public interface TodoService {
    TodoDto save(TodoDto todoDto);
    TodoDto getTodo(String todoId);
    void deleteTodo(String todoId);
}
