package com.ahold.technl.sandbox.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DeliveryTimeValidator.class)
@Documented
public @interface ValidDeliveryTime {
    String message() default "Invalid delivery status and finishedAt combination";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
