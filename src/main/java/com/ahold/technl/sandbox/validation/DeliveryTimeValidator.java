package com.ahold.technl.sandbox.validation;

import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.dto.DeliveryStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class DeliveryTimeValidator implements ConstraintValidator<ValidDeliveryTime, DeliveryRecord> {
    @Override
    public boolean isValid(DeliveryRecord record, ConstraintValidatorContext context) {
        if (record == null) return true; // handled by @NotNull if needed

        boolean valid = true;

        // Validate format of startedAt
        if (!isValidOffsetDateTime(record.startedAt())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("startedAt must be in ISO_OFFSET_DATE_TIME format (e.g. 2025-05-12T10:00:00+02:00)")
                    .addPropertyNode("startedAt")
                    .addConstraintViolation();
            return false;
        }

        // Validate format of finishedAt if present
        if (record.finishedAt() != null && !isValidOffsetDateTime(record.finishedAt())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("finishedAt must be in ISO_OFFSET_DATE_TIME format (e.g. 2025-05-12T10:00:00+02:00)")
                    .addPropertyNode("finishedAt")
                    .addConstraintViolation();
            return false;
        }

        if (DeliveryStatus.IN_PROGRESS.equals(record.status()) && record.finishedAt() != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("finishedAt should be null for IN_PROGRESS deliveries")
                    .addPropertyNode("finishedAt").addConstraintViolation();
            valid = false;
        }else if (DeliveryStatus.DELIVERED.equals(record.status()) && record.finishedAt() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("finishedAt should not be null for DELIVERED deliveries")
                    .addPropertyNode("finishedAt").addConstraintViolation();
            valid = false;
        }

        return valid;

    }

    private boolean isValidOffsetDateTime(String value) {
        try {
            OffsetDateTime.parse(value);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
