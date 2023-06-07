package com.example.ToDoAPI.repository;

import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
