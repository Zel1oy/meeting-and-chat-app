package com.app.lab7.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String field;
    private String fieldToMatch;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldToMatch = constraintAnnotation.fieldToMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            Class<?> clazz = value.getClass();
            Field firstField = clazz.getDeclaredField(field);
            Field secondField = clazz.getDeclaredField(fieldToMatch);

            firstField.setAccessible(true);
            secondField.setAccessible(true);

            Object firstObj = firstField.get(value);
            Object secondObj = secondField.get(value);

            return firstObj == null && secondObj == null
                    || firstObj != null && firstObj.equals(secondObj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
