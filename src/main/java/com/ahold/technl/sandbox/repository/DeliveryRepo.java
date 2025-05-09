package com.ahold.technl.sandbox.repository;

import com.ahold.technl.sandbox.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface DeliveryRepo extends JpaRepository<Delivery,String> {
    List<Delivery> findAllByStartedAtBetween(OffsetDateTime start, OffsetDateTime end);

}
