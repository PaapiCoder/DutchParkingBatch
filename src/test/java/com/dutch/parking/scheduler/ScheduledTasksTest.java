package com.dutch.parking.scheduler;

import org.awaitility.Durations;
import org.junit.Ignore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ScheduledTasksTest {

	@SpyBean
	ScheduledTasks tasks;

	@Test
	public void unregistered() {
		await().atMost(Durations.TEN_SECONDS.multipliedBy(3l)).untilAsserted(() -> {
			verify(tasks, atLeast(1)).generateReportForNotRegisteredVehicle();
		});
	}

	@Test
	public void Registered() {
		await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
			verify(tasks, atLeast(1)).generateReportForNotRegisteredVehicle();
		});
	}
}
