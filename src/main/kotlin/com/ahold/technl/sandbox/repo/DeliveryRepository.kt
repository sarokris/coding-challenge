package com.ahold.technl.sandbox.repo

import com.ahold.technl.sandbox.entity.Delivery
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DeliveryRepository : JpaRepository<Delivery, UUID>