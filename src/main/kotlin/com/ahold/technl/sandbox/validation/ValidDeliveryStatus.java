package com.ahold.technl.sandbox.validation;

import java.lang.annotation.RetentionPolicy;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
@Constraint(validatedBy = DeliveryStatusValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDeliveryStatus {
    Class<? extends Enum<?>> enumClass();
    String message() default "Invalid value. Allowed values are: {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
