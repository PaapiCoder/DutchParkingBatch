package com.dutch.parking.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReportDetails {
	private String licenceNumber;
	private String streetName;
	private LocalDateTime date;
	@Override
	public String toString(){
		StringBuilder dataBuilder = new StringBuilder();
		appendFieldValue(dataBuilder, licenceNumber);
		appendFieldValue(dataBuilder, streetName);
		appendFieldValue(dataBuilder, date.toString());
		return dataBuilder.toString();
	}

	private void appendFieldValue(StringBuilder dataBuilder, String fieldValue) {
		if(fieldValue != null) {
			dataBuilder.append(fieldValue).append(",");
		} else {
			dataBuilder.append("").append(",");
		}
	}
}
