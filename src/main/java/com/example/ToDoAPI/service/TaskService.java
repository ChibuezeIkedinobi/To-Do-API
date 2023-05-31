package com.example.ToDoAPI.service;

import com.example.ToDoAPI.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskService {

    TaskRepository taskRepository;
}
