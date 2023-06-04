package com.example.ToDoAPI.controller;

import com.example.ToDoAPI.entity.User;
import com.example.ToDoAPI.enumClass.TaskStatus;
import com.example.ToDoAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getUsers() {
//        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

//    @GetMapping("/todo")
//    public ResponseEntity<HttpStatus> test(
//            @RequestParam(value = "status", required = false) TaskStatus status,
//            @RequestParam(value = "id", required = false) Long id,
//            @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
//            @RequestParam(value = "size", required = false, defaultValue = "10") Long size) {
//
//    }

}
