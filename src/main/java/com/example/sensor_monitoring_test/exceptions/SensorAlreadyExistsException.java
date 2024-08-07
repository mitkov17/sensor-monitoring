package com.example.sensor_monitoring_test.exceptions;

public class SensorAlreadyExistsException extends RuntimeException {

    public SensorAlreadyExistsException(String sensorName) {
        super("Sensor \"" + sensorName + "\" already exists!");
    }

}
