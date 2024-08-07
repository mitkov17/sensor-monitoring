package com.example.sensor_monitoring_test.controllers;

import com.example.sensor_monitoring_test.dto.Measurement;
import com.example.sensor_monitoring_test.dto.Sensor;
import com.example.sensor_monitoring_test.services.MeasurementService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasurementService measurementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllMeasurements() throws Exception {
        Measurement measurement = new Measurement();
        measurement.setId(1L);

        List<Measurement> measurements = Collections.singletonList(measurement);

        when(measurementService.getAllMeasurements()).thenReturn(measurements);

        mockMvc.perform(get("/api/measurements")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetRainyDaysCount() throws Exception {
        int rainyDaysCount = 5;

        when(measurementService.getRainyDaysCount()).thenReturn(rainyDaysCount);

        mockMvc.perform(get("/api/measurements/rainyDaysCount")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMeasurement() throws Exception {
        Measurement measurement = new Measurement();
        measurement.setSensor(new Sensor());
        measurement.setRaining(true);
        measurement.setValue(5.0);

        doNothing().when(measurementService).addMeasurement(measurement);

        mockMvc.perform(post("/api/measurements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measurement)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMeasurementWithValidationError() throws Exception {
        Measurement measurement = new Measurement();

        doNothing().when(measurementService).addMeasurement(measurement);

        mockMvc.perform(post("/api/measurements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measurement)))
                .andExpect(status().isBadRequest());
    }
}
