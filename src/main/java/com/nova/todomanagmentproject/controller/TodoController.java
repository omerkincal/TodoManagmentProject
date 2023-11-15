package com.nova.todomanagmentproject.controller;

import com.nova.todomanagmentproject.dto.TodoDto;
import com.nova.todomanagmentproject.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoDto> save(@RequestBody TodoDto todoDto){
        TodoDto dto = service.save(todoDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable(name = "id") String todoId){
        TodoDto dto = service.getTodo(todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable(name = "id") String todoId,
                                              @RequestBody TodoDto todoDto){
        TodoDto dto = service.updateTodo(todoDto, todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        return new ResponseEntity<>(service.getAllTodos(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") String todoId){
        service.deleteTodo(todoId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable(name = "id") String todoId){
        TodoDto dto = service.completeTodo(todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable(name = "id") String todoId){
        TodoDto dto = service.inCompleteTodo(todoId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
