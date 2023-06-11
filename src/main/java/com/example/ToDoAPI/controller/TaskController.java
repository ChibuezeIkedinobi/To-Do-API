package com.example.ToDoAPI.controller;

import com.example.ToDoAPI.dto.TaskRequest;
import com.example.ToDoAPI.dto.TaskStatusUpdateRequest;
import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.enums.TaskStatus;
import com.example.ToDoAPI.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<Task> addTaskToUser( @PathVariable Long id,
                                               @Valid @RequestBody TaskRequest task) {
        return new ResponseEntity<>(taskService.addTaskToUser(task, id), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/user/{userId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long userId, @PathVariable Long taskId,
                                           @Valid @RequestBody TaskRequest task) {
        return new ResponseEntity<>(taskService.updateTask(userId, taskId, task), HttpStatus.OK);
    }

    @PutMapping("/user/{taskId}/{userId}")
    public ResponseEntity<Task> updateStatus(@PathVariable Long userId,
                                             @PathVariable Long taskId,
                                             @Valid @RequestBody TaskStatusUpdateRequest status) {
        return new ResponseEntity<>(taskService.updateStatus(userId, taskId, status), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> getUserTaskByStatus(@PathVariable Long id,
                                                          @RequestParam String status) {
        return new ResponseEntity<>(taskService.getUserTaskByStatus(id, status), HttpStatus.OK);
    }

    @GetMapping("/allTask")
    public ResponseEntity<List<Task>> getAllTaskByStatus(@RequestParam String status) {
        return new ResponseEntity<>(taskService.getAllTaskByStatus(status),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
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
