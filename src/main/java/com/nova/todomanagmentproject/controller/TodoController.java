package com.nova.todomanagmentproject.controller;

import com.nova.todomanagmentproject.dto.TodoDto;
import com.nova.todomanagmentproject.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> save(@RequestBody TodoDto todoDto){
        TodoDto dto = service.save(todoDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable(name = "id") String todoId){
        TodoDto dto = service.getTodo(todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable(name = "id") String todoId,
                                              @RequestBody TodoDto todoDto){
        TodoDto dto = service.updateTodo(todoDto, todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        return new ResponseEntity<>(service.getAllTodos(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") String todoId){
        service.deleteTodo(todoId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable(name = "id") String todoId){
        TodoDto dto = service.completeTodo(todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable(name = "id") String todoId){
        TodoDto dto = service.inCompleteTodo(todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
