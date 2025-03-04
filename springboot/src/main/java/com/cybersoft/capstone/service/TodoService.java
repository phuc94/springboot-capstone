package com.cybersoft.capstone.service;

import com.cybersoft.capstone.entity.Todo;
import com.cybersoft.capstone.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Integer id){
        return todoRepository.findById(id);
    }

    public Todo update(Todo todo){
        return todoRepository.save(todo);
    }
}
