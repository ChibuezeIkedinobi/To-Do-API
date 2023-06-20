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
@RequestMapping("/task")  // native URI
public class TaskController {

    TaskService taskService;

    @GetMapping("/{id}")  // get a particular task using the id
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")  // create task and add it to a user.
    public ResponseEntity<Task> addTaskToUser( @PathVariable Long id,  // takes in the id of the user whom the task is to be assigned to
                                               @Valid @RequestBody TaskRequest task) {    // takes the passed in request in the DTO instead of task for security reasons
        return new ResponseEntity<>(taskService.addTaskToUser(task, id), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/user/{userId}")  // update task details using the user id and the task id
    public ResponseEntity<Task> updateTask(@PathVariable Long userId, @PathVariable Long taskId,
                                           @Valid @RequestBody TaskRequest task) {   // takes in the corrected task JSON in the DTO
        return new ResponseEntity<>(taskService.updateTask(userId, taskId, task), HttpStatus.OK);
    }

    @PutMapping("/user/{taskId}/{userId}")  // update the status of a task taking the taskId, userId and a DTO
    public ResponseEntity<Task> updateStatus(@PathVariable Long userId,
                                             @PathVariable Long taskId,
                                             @Valid @RequestBody TaskStatusUpdateRequest status) {
        return new ResponseEntity<>(taskService.updateStatus(userId, taskId, status), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")   // get tasks assigned to a user by the status(pending,started,completed)
    public ResponseEntity<List<Task>> getUserTaskByStatus(@PathVariable Long id,  // takes in the id of the user
                                                          @RequestParam String status) {  // pass the parameter of the status as a string format
        return new ResponseEntity<>(taskService.getUserTaskByStatus(id, status), HttpStatus.OK);
    }

    @GetMapping("/allTask")  // get all available tasks by the value of their status
    public ResponseEntity<List<Task>> getAllTaskByStatus(@RequestParam String status) {  // pass the parameter of the status
        return new ResponseEntity<>(taskService.getAllTaskByStatus(status),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")  // delete a particular task by the id
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
