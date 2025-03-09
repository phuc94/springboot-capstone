package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Todo;
import com.cybersoft.capstone.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodo(@Valid @RequestBody Todo todo) {
        ResponseEntity<Todo> response;
        try {
            response = new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return response;
    }

}
