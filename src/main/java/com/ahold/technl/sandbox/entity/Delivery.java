package com.ahold.technl.sandbox.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String vehicleId;
    private String address;
    private String startedAt;
    private String finishedAt;
    private String status;
}
