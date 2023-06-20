package com.example.ToDoAPI.entity;

import com.example.ToDoAPI.enums.TaskStatus;
//import com.example.ToDoAPI.validation.Status;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")  // where the table name is task
@SQLDelete(sql = "UPDATE task SET deleted_at = NOW() WHERE id=?", check = ResultCheckStyle.COUNT)  // initiating soft delete
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull(message = "title shouldn't be null")
    private String title;

    @NotBlank
    @NotNull(message = "Description shouldn't be null")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Task(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user", referencedColumnName = "id")  // use user_id next time. name of the foreign key in the task table
    private User user;  //where all tasks associated to one particular user is stored. many tasks to one user
}
