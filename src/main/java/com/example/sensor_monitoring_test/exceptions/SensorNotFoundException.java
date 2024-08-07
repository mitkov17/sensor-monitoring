package com.example.sensor_monitoring_test.exceptions;

public class SensorNotFoundException extends RuntimeException {

    public SensorNotFoundException() {
        super("Sensor is not found" );
    }
}
