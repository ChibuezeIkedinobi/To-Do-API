package com.example.ToDoAPI.service;

import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.entity.User;
import com.example.ToDoAPI.exception.UserNotFoundException;
import com.example.ToDoAPI.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public List<Task> getAssignedTasks(Long id) {
        User user = getUserById(id);
        return user.getAllTasks();
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(id);
    }

}
