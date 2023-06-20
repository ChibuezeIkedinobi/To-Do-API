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
    private String message;  // used lombok annotation for the string alone

    private static Map<String, TaskStatus> statuses = new HashMap<>();

    static {
        for(TaskStatus status : values()) {  // loops through the message values of the enum and saves it in the map in lowercase as a string cos a string is being passed at the request param
            statuses.put(status.getMessage().toLowerCase(), status);
        }
    }

    public static TaskStatus getStatus(String status) { // validation to get status..
        TaskStatus status1 = statuses.get(status.toLowerCase());  // compare the passed status to those inside the hashmap for validation
        if (status1 != null) {
            return status1;
        }

        throw new InvalidInputException();
    }

}
