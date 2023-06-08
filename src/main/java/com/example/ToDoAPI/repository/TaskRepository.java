package com.example.ToDoAPI.repository;

import com.example.ToDoAPI.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


   @Query(value = "SELECT * FROM task WHERE id = :taskId AND user = :userId", nativeQuery = true)
   Optional<Task> findByTaskIdAndUserId(@Param("taskId") Long taskId,
                                        @Param("userId") Long userId);
}
