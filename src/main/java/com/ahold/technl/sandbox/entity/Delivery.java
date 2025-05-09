package com.ahold.technl.sandbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String vehicleId;
    private String address;
    @Column(nullable = false)
    private OffsetDateTime startedAt;
    private OffsetDateTime finishedAt;
    private String status;
}
