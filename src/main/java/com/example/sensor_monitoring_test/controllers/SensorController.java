package com.example.sensor_monitoring_test.controllers;

import com.example.sensor_monitoring_test.dto.Sensor;
import com.example.sensor_monitoring_test.services.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PostMapping("/registration")
    public void registerSensor(@Valid @RequestBody Sensor sensor) {
        sensorService.registerSensor(sensor);
    }

    @GetMapping()
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }
}
