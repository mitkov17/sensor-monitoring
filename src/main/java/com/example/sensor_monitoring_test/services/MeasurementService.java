package com.example.sensor_monitoring_test.services;

import com.example.sensor_monitoring_test.dto.Measurement;
import com.example.sensor_monitoring_test.dto.Sensor;
import com.example.sensor_monitoring_test.exceptions.SensorNotFoundException;
import com.example.sensor_monitoring_test.repositories.MeasurementRepository;
import com.example.sensor_monitoring_test.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final SensorRepository sensorRepository;

    public void addMeasurement(Measurement measurement) {
        if (measurement.getSensor() == null || sensorRepository.findByName(measurement.getSensor().getName()) == null) {
            throw new SensorNotFoundException();
        }
        Sensor sensor = sensorRepository.findByName(measurement.getSensor().getName());
        measurement.setSensor(sensor);
        measurement.setMeasurementTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Integer getRainyDaysCount() {
        return measurementRepository.findByRainingTrue().size();
    }
}
