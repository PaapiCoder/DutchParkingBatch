package com.dutch.parking;

import static org.assertj.core.api.Assertions.assertThat;

import com.dutch.parking.scheduler.ScheduledTasks;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DutchParkingBatchApplicationTest {

	@Autowired
	private ScheduledTasks tasks;

	@Test
	public void contextLoads() {
		// Basic integration test that shows the context starts up properly
		assertThat(tasks).isNotNull();
	}

}
