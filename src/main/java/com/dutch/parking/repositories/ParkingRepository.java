package com.dutch.parking.repositories;

import com.dutch.parking.model.ParkingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<ParkingDetail, Long> {


}
