package com.example.ToDoAPI.repository;

import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
