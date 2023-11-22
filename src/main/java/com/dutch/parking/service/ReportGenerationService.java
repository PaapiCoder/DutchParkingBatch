package com.dutch.parking.service;

import com.dutch.parking.model.ParkingDetail;
import com.dutch.parking.model.ParkingMonitoringDetail;
import com.dutch.parking.report.ReportDetails;
import com.dutch.parking.repositories.ParkingMonitoringRepository;
import com.dutch.parking.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportGenerationService {
	@Autowired
	private ParkingMonitoringRepository parkingMonitoringRepository;

	@Autowired
	private ParkingRepository parkingRepository;

	@Scheduled(cron = "59 23 * * * *")
	public void reportUnregisteredVehicleJob() {
		LocalDate date = LocalDateTime.now().toLocalDate();
		//All Parked vehicle details for the day
		List<ParkingDetail> parkingDetails = parkingRepository.findByRegisterDatetimeBetween(
				LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date,LocalTime.MAX));
		//All monitoring details of parked vehicle.
		List<ParkingMonitoringDetail> monitoringDetails = parkingMonitoringRepository.findByRecordingDateBetween(
				LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date,LocalTime.MAX));

		List<ParkingMonitoringDetail> collect = new ArrayList<>();

		//Registered parking record doesn't contain monitoring record.
		monitoringDetails.forEach(item->{
			if (!parkingDetails.stream().map(m -> m.getLicenceNumber()).toList().contains(item.getLicenceNumber())){
				collect.add(item);
			}
		});

		// Monitoring record contain vehicle number but parked outside there registration  time period.
		collect.addAll(monitoringDetails.stream()
				.filter(m -> parkingDetails.stream()
						.anyMatch(p -> m.getLicenceNumber().equals(p.getLicenceNumber())
								&& m.getStreetName().equals(p.getStreetName())
								&& m.getRecordingDate().isAfter(p.getUnregisterDatetime())
								&& m.getRecordingDate().isBefore(p.getRegisterDatetime())))
				.collect(Collectors.toList()));

		List<ReportDetails> reportDetails = collect.stream().map(c ->
				new ReportDetails(c.getLicenceNumber(), c.getStreetName(), c.getRecordingDate())).collect(Collectors.toList());
		this.generateReport(reportDetails);
	}

	private void generateReport(List<ReportDetails> reportDetails) {
		// Put logic to generate report.
		reportDetails.stream().forEach(System.out::println);
	}

}
