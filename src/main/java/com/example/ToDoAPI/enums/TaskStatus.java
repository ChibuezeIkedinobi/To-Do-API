package com.example.ToDoAPI.enums;


import com.example.ToDoAPI.exception.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum TaskStatus {
    STARTED("started"),
    PENDING("pending"),
    COMPLETED("completed");


    @Setter
    @Getter
    private String message;

    private static Map<String, TaskStatus> statuses = new HashMap<>();

    static {
        for(TaskStatus status : values()) {
            statuses.put(status.getMessage().toLowerCase(), status);
        }
    }

    public static TaskStatus getStatus(String status) {
        TaskStatus status1 = statuses.get(status.toLowerCase());
        if (status1 != null) {
            return status1;
        }

        throw new InvalidInputException();
    }

}
