package com.dutch.parking.scheduler;

import com.dutch.parking.reports.ReportDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.util.Arrays;

@Component
public class ScheduledTasks {

	@Value("${dutch.parking.apis.url}")
	private String apiUrl;

	@Value("${output.report.file.path}")
	private String reportPath;

	@Scheduled(cron = "${cron.schedule.report.registered}")
	public void generateReportForRegisteredVehicle () {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response
				= restTemplate.getForEntity(apiUrl + "/registeredVehicleReport", String.class);
		System.out.println("I am Registered");
	}

	@Scheduled(cron = "${cron.schedule.report.not.registered}")
	public void generateReportForNotRegisteredVehicle () throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response
				= restTemplate.getForEntity(apiUrl + "/notRegisteredVehicleReport", String.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		ReportDetails[] reportDetails = mapper.readValue(response.getBody(), ReportDetails[].class);
		this.createReportForRegisteredVehicle(reportDetails);
	}

	private void createReportForRegisteredVehicle(ReportDetails[] data) throws Exception {
		PrintWriter writer = new PrintWriter(reportPath);
		//writer.println({"","",""});
		Arrays.stream(data).map(ReportDetails::toString).forEach(writer::println);
		writer.close();
		System.out.println(data);
	}
}
