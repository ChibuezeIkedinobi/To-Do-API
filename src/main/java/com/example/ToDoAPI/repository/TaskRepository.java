package com.example.ToDoAPI.repository;

import com.example.ToDoAPI.entity.Task;
import com.example.ToDoAPI.enums.TaskStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


   @Query(value = "SELECT * FROM task WHERE id = :taskId AND user = :userId", nativeQuery = true)
   Optional<Task> findByTaskIdAndUserId(@Param("taskId") Long taskId,
                                        @Param("userId") Long userId);

   @Query(value = "SELECT * FROM task WHERE LOWER(status) = LOWER(:status)", nativeQuery = true)
   List<Task> findByStatus(@Param("status") String status);

   @Query(value = "SELECT * FROM task WHERE user = :userId AND LOWER(status) = LOWER(:status)", nativeQuery = true)
   List<Task> findByUserIdAndStatus(@Param("userId") Long userId,
                              @Param("status") String status);

}
