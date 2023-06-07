package com.example.ToDoAPI.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException() {
        super("Invalid input");
    }
}