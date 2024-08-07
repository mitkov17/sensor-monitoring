package com.example.sensor_monitoring_test.services;

import com.example.sensor_monitoring_test.dto.Sensor;
import com.example.sensor_monitoring_test.exceptions.SensorAlreadyExistsException;
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

public class SensorServiceTest {

    @InjectMocks
    private SensorService sensorService;

    @Mock
    private SensorRepository sensorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSensors() {
        Sensor sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Sensor Name");

        when(sensorRepository.findAll()).thenReturn(List.of(sensor));

        List<Sensor> sensors = sensorService.getAllSensors();

        assertNotNull(sensors);
        assertEquals(1, sensors.size());
        assertEquals("Sensor Name", sensors.get(0).getName());
    }

    @Test
    public void testRegisterSensor() {
        Sensor sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Sensor Name");

        when(sensorRepository.findByName(sensor.getName())).thenReturn(null);
        when(sensorRepository.save(any())).thenReturn(sensor);

        sensorService.registerSensor(sensor);

        verify(sensorRepository, times(1)).save(sensor);
    }

    @Test
    public void testRegisterSensorWithDuplicate() {
        Sensor sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Sensor name");

        when(sensorRepository.findByName(sensor.getName())).thenReturn(sensor);

        assertThrows(SensorAlreadyExistsException.class, () -> sensorService.registerSensor(sensor));
        verify(sensorRepository, times(0)).save(sensor);
    }

}
