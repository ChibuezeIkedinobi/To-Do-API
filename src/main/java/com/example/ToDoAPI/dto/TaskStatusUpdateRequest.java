package com.example.ToDoAPI.dto;

import com.example.ToDoAPI.validation.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskStatusUpdateRequest {

    @NotBlank(message = "status shouldn't be blank")
    @Status
    private String status;
}
