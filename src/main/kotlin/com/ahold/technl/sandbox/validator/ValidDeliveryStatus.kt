package com.ahold.technl.sandbox.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [DeliveryStatusValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidDeliveryStatus(
    val message: String = "Invalid status. Allowed values are IN_PROGRESS, DELIVERED",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
