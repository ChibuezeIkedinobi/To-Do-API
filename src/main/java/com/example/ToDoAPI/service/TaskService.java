package com.example.ToDoAPI.service;

import com.example.ToDoAPI.dto.TaskRequest;
import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.enumClass.TaskStatus;
import com.example.ToDoAPI.exception.TaskNotFoundException;
import com.example.ToDoAPI.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskService {

    TaskRepository taskRepository;

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return unwrapTask(task, id);
    }

    public Task createTask(TaskRequest request) {
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.PENDING);

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskRequest taskRequest) {
        Optional<Task> request = taskRepository.findById(id);

        if (request.isPresent()) return createTask(taskRequest);
        else throw new TaskNotFoundException(id);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    static Task unwrapTask(Optional<Task> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new TaskNotFoundException(id);
    }


}
