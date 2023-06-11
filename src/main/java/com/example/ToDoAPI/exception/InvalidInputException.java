package com.example.ToDoAPI.exception;

import com.example.ToDoAPI.dto.TaskStatusUpdateRequest;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException() {
        super("The input is Invalid");
    }
}