package com.example.sensor_monitoring_test.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity(name = "MEASUREMENT")
@Getter
@Setter
public class Measurement {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEASUREMENT_VALUE")
    @Min(value = -100, message = "Value should be between -100 and 100")
    @Max(value = 100, message = "Value should be between -100 and 100")
    private Double value;

    @Column(name = "RAINING")
    @NotNull(message = "Raining index should not be null")
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "SENSOR", referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "MEASUREMENT_TIME")
    private LocalDateTime measurementTime;
}
