package com.example.sensor_monitoring_test.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Size;

@Entity(name = "SENSOR")
@Getter
@Setter
public class Sensor {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    @Size(min = 3, max = 30, message = "Name length should be between 3 and 30 symbols")
    private String name;
}
