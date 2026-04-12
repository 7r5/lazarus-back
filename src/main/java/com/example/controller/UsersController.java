package com.example.controller;

import com.example.model.Users;
import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getAll() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<Users> create(@RequestBody Users users) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.saveUsers(users));
    }
}
