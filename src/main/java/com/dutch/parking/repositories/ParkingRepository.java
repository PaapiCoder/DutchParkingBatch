package com.dutch.parking.repositories;

import com.dutch.parking.model.ParkingDetail;
import com.dutch.parking.model.ParkingMonitoringDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingRepository extends JpaRepository<ParkingDetail, Long> {

	List<ParkingDetail> findByRegisterDatetimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
