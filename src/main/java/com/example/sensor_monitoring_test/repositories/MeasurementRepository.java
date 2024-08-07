package com.example.sensor_monitoring_test.repositories;

import com.example.sensor_monitoring_test.dto.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findByRainingTrue();
}
