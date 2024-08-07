package com.example.sensor_monitoring_test.services;

import com.example.sensor_monitoring_test.dto.Measurement;
import com.example.sensor_monitoring_test.dto.Sensor;
import com.example.sensor_monitoring_test.exceptions.SensorNotFoundException;
import com.example.sensor_monitoring_test.repositories.MeasurementRepository;
import com.example.sensor_monitoring_test.repositories.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MeasurementServiceTest {

    @InjectMocks
    private MeasurementService measurementService;

    @Mock
    private MeasurementRepository measurementRepository;

    @Mock
    private SensorRepository sensorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMeasurements() {
        Measurement measurement = new Measurement();
        measurement.setId(1L);

        when(measurementRepository.findAll()).thenReturn(List.of(measurement));

        List<Measurement> measurements = measurementService.getAllMeasurements();

        assertNotNull(measurements);
        assertEquals(1, measurements.size());
    }

    @Test
    public void testGetRainyDaysCount() {
        Measurement measurement = new Measurement();
        measurement.setId(1L);
        measurement.setRaining(true);

        when(measurementRepository.findByRainingTrue()).thenReturn(List.of(measurement));

        int result = measurementService.getRainyDaysCount();

        assertNotNull(result);
        assertEquals(1, result);
    }

    @Test
    public void testAddMeasurement() {
        Sensor sensor = new Sensor();
        sensor.setName("Sensor name");
        Measurement measurement = new Measurement();
        measurement.setId(1L);
        measurement.setValue(5.0);
        measurement.setRaining(true);
        measurement.setSensor(sensor);

        when(sensorRepository.findByName(sensor.getName())).thenReturn(sensor);
        when(measurementRepository.save(any())).thenReturn(measurement);

        measurementService.addMeasurement(measurement);

        verify(measurementRepository, times(1)).save(measurement);
    }

    @Test
    public void testAddMeasurementWithNoSensor() {
        Measurement measurement = new Measurement();
        measurement.setId(1L);
        measurement.setValue(5.0);
        measurement.setRaining(true);

        when(sensorRepository.findByName(anyString())).thenReturn(null);
        when(measurementRepository.save(any())).thenReturn(measurement);

        assertThrows(SensorNotFoundException.class, () -> measurementService.addMeasurement(measurement));
        verify(measurementRepository, times(0)).save(measurement);
    }

}
