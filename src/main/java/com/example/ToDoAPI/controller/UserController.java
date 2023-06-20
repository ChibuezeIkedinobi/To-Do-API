package com.example.ToDoAPI.controller;

import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.entity.User;
import com.example.ToDoAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")  // native URI
public class UserController {

    UserService userService;

    @GetMapping("/{id}")  // get a particular user using the id
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping  // create a user
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/all")   // get all users available
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")  // delete a particular user using the id
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks")   // get all tasks assigned to a particular user using the user ID
    public ResponseEntity<List<Task>> getAssignedTasks(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getAssignedTasks(id), HttpStatus.OK);
    }

}
