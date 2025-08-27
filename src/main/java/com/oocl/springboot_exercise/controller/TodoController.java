package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController()
@RequestMapping("/api/v1")
public class TodoController {

    private Map<Integer, Todo> db = new HashMap<>();

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTodo(@RequestBody Todo todo) {
        int id = db.size() + 1;
        todo.setId(id);
        db.put(id, todo);
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return new ArrayList<>(db.values());
    }
}
