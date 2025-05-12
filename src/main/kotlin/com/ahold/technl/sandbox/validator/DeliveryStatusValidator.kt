package com.ahold.technl.sandbox.validator

import com.ahold.technl.sandbox.dto.DeliveryStatus
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class DeliveryStatusValidator : ConstraintValidator<ValidDeliveryStatus, DeliveryStatus> {

    override fun isValid(status: DeliveryStatus?, context: ConstraintValidatorContext): Boolean {
        return status == DeliveryStatus.IN_PROGRESS || status == DeliveryStatus.DELIVERED
    }
}
