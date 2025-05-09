package com.ahold.technl.sandbox.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Delivery {

    @Id
    private String id = UUID.randomUUID().toString();
    private String vehicleId;
    private String address;
    private String startedAt;
    private String finishedAt;
    private String status;
}
