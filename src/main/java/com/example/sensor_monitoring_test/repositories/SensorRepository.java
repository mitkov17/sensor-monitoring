package com.example.sensor_monitoring_test.repositories;

import com.example.sensor_monitoring_test.dto.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Sensor findByName(String name);

}
