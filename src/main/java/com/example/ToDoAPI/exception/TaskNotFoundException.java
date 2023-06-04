package com.example.ToDoAPI.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(Long id) {
        super("The Task id '"+ id +"' does not exist");
    }

}
