package com.example.sensor_monitoring_test.services;

import com.example.sensor_monitoring_test.dto.Sensor;
import com.example.sensor_monitoring_test.exceptions.SensorAlreadyExistsException;
import com.example.sensor_monitoring_test.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public void registerSensor(Sensor sensor) {
        if (sensorRepository.findByName(sensor.getName()) != null) {
            throw new SensorAlreadyExistsException(sensor.getName());
        }
        sensorRepository.save(sensor);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}
