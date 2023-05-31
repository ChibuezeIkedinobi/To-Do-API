package com.example.ToDoAPI.controller;

import com.example.ToDoAPI.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    UserService userService;

}
