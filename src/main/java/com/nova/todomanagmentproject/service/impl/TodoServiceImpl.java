package com.nova.todomanagmentproject.service.impl;

import com.nova.todomanagmentproject.dto.TodoDto;
import com.nova.todomanagmentproject.entity.Todo;
import com.nova.todomanagmentproject.exception.ResourceNotFoundException;
import com.nova.todomanagmentproject.repository.TodoRepository;
import com.nova.todomanagmentproject.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Todo todo = repository.findById(Long.valueOf(todoId)).
                orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: " + todoId));
        return toDto(todo);
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, String todoId) {
        Todo todo = repository.findById(Long.valueOf(todoId))
                .orElseThrow(() -> new ResourceNotFoundException("Todo is not exist with id: " + todoId));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        todo = repository.save(todo);
        return toDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return repository.findAll()
                .stream()
                .map(todo -> toDto(todo))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTodo(String todoId) {
        Todo todo = repository.findById(Long.valueOf(todoId))
                .orElseThrow(()-> new ResourceNotFoundException("Todo is not exist with id: "+ todoId));
        repository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(String todoId) {
        Todo todo = repository.findById(Long.valueOf(todoId))
                .orElseThrow(()-> new ResourceNotFoundException(("Todo is not exist with id: "+ todoId)));
        todo.setCompleted(Boolean.TRUE);
        todo = repository.save(todo);
        return toDto(todo);
    }

    @Override
    public TodoDto inCompleteTodo(String todoId) {
        Todo todo = repository.findById(Long.valueOf(todoId))
                .orElseThrow(()-> new ResourceNotFoundException(("Todo is not exist with id: "+ todoId)));
        todo.setCompleted(Boolean.FALSE);
        todo = repository.save(todo);
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
