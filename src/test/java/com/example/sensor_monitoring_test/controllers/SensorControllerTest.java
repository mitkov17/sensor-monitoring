package com.example.sensor_monitoring_test.controllers;

import com.example.sensor_monitoring_test.dto.Sensor;
import com.example.sensor_monitoring_test.services.SensorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSensors() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Sensor name");

        List<Sensor> sensors = Collections.singletonList(sensor);

        when(sensorService.getAllSensors()).thenReturn(sensors);

        mockMvc.perform(get("/api/sensors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(sensor.getName())));
    }

    @Test
    public void testRegisterSensor() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Sensor name");

        doNothing().when(sensorService).registerSensor(sensor);

        mockMvc.perform(post("/api/sensors/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensor)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterSensorWithValidationError() throws Exception {
        Sensor sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("S");

        mockMvc.perform(post("/api/sensors/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensor)))
                .andExpect(status().isBadRequest());
    }
}
