package com.besthacks.tsp.dto;

import com.besthacks.tsp.entity.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReportRequest {
    private String description;
    private ReportStatus reportStatus;
    private String city;
    private String street;
    private Integer streetNumber;
    private Double latitude;
    private Double longitude;
}
