package com.ahold.technl.sandbox.repository;

import com.ahold.technl.sandbox.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<Delivery,String> {
}
