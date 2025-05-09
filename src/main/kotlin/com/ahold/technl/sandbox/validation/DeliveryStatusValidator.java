package com.ahold.technl.sandbox.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class DeliveryStatusValidator implements ConstraintValidator<ValidDeliveryStatus, Enum<?>> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidDeliveryStatus annotation) {
        this.enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(value.name()));
    }
}
