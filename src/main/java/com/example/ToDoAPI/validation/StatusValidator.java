package com.example.ToDoAPI.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StatusValidator implements ConstraintValidator<Status, String> {


    List<String> status = Arrays.asList(
            "PENDING",
            "STARTED",
            "COMPLETED"
    );


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        for (String string : status) {
            if (value.equals(string)) return true;
        }
        return false;
    }
}
