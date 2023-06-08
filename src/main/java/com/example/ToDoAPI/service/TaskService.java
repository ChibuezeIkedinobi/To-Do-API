package com.example.ToDoAPI.service;

import com.example.ToDoAPI.dto.TaskRequest;
import com.example.ToDoAPI.dto.TaskStatusUpdateRequest;
import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.entity.User;
import com.example.ToDoAPI.enums.TaskStatus;
import com.example.ToDoAPI.exception.TaskNotFoundException;
import com.example.ToDoAPI.repository.TaskRepository;
import com.example.ToDoAPI.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class TaskService {

    TaskRepository taskRepository;
    UserRepository userRepository;

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return unwrapTask(task, id);
    }

    public Task addTaskToUser(TaskRequest request, Long id) {

        User user = UserService.unwrapUser(userRepository.findById(id), id);
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.PENDING);

        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(Long userId, Long taskId, TaskRequest taskRequest) {
        Optional<Task> verifyTask = taskRepository.findByTaskIdAndUserId(taskId, userId);

        if (verifyTask.isPresent()){
            Task task = verifyTask.get();
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            return taskRepository.save(task);
        }
        else throw new TaskNotFoundException(taskId);
    }

    public Task updateStatus(Long userId, Long taskId, @Valid TaskStatusUpdateRequest taskRequest) {
        Optional<Task> verifyTask = taskRepository.findByTaskIdAndUserId(taskId, userId);

        if (verifyTask.isPresent()){
            try {
                Task task = verifyTask.get();
                task.setStatus(TaskStatus.getStatus(taskRequest.getStatus()));
                return taskRepository.save(task);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                throw new TaskNotFoundException(taskId);
            }
        }
        else throw new TaskNotFoundException(taskId);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    static Task unwrapTask(Optional<Task> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new TaskNotFoundException(id);
    }


}
