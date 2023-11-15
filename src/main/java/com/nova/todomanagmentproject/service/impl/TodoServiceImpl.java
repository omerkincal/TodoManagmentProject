package com.nova.todomanagmentproject.service.impl;

import com.nova.todomanagmentproject.dto.TodoDto;
import com.nova.todomanagmentproject.entity.Todo;
import com.nova.todomanagmentproject.repository.TodoRepository;
import com.nova.todomanagmentproject.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;

    @Override
    public TodoDto save(TodoDto todoDto) {
        Todo todo = toEntity(todoDto);
        todo = repository.save(todo);
        return toDto(todo);
    }

    @Override
    public TodoDto getTodo(String todoId) {
        Todo todo = repository.findById(Long.valueOf(todoId)).get();

        return toDto(todo);
    }


    public TodoDto toDto(Todo todo){
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setTitle(todo.getTitle());
        todoDto.setDescription(todo.getDescription());
        todoDto.setCompleted(todo.isCompleted());
        return todoDto;
    }

    public Todo toEntity(TodoDto todoDto){
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        return todo;
    }
}
