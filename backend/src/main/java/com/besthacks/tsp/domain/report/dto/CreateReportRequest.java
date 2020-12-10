package com.besthacks.tsp.domain.report.dto;

import com.besthacks.tsp.domain.report.entity.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportRequest {
    private String description;
    private ReportStatus reportStatus;
    private String city;
    private String street;
    private Integer streetNumber;
    private Double latitude;
    private Double longitude;
    private Long accountId;
}
