package com.example.ToDoAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Title shouldn't be blank")
    private String title;

    @NotBlank(message = "Description shouldn't be blank")
    private String description;
}
