package com.dutch.parking.repositories;

import com.dutch.parking.model.ParkingMonitoringDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingMonitoringRepository  extends JpaRepository<ParkingMonitoringDetail, Long> {

}
