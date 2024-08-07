package com.example.sensor_monitoring_test.controllers;

import com.example.sensor_monitoring_test.dto.Measurement;
import com.example.sensor_monitoring_test.services.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/measurements")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping("/add")
    public void addMeasurement(@Valid @RequestBody Measurement measurement) {
        measurementService.addMeasurement(measurement);
    }

    @GetMapping()
    public List<Measurement> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

}
