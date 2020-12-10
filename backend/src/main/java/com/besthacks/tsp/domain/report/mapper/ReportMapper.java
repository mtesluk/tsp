package com.besthacks.tsp.domain.report.mapper;

import com.besthacks.tsp.domain.report.dto.CreateReportRequest;
import com.besthacks.tsp.domain.report.dto.ReportResponse;
import com.besthacks.tsp.domain.report.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public Report mapToReport(CreateReportRequest createReportRequest) {
        return Report.builder()
                .description(createReportRequest.getDescription())
                .city(createReportRequest.getCity())
                .reportStatus(createReportRequest.getReportStatus())
                .accountId(createReportRequest.getAccountId())
                .city(createReportRequest.getCity())
                .street(createReportRequest.getStreet())
                .streetNumber(createReportRequest.getStreetNumber())
                .latitude(createReportRequest.getLatitude())
                .longitude(createReportRequest.getLongitude())
                .build();
    }

    public ReportResponse mapToReportResponse(Report report) {
        return ReportResponse.builder()
                .id(report.getId())
                .description(report.getDescription())
                .reportStatus(report.getReportStatus())
                .city(report.getCity())
                .street(report.getStreet())
                .streetNumber(report.getStreetNumber())
                .latitude(report.getLatitude())
                .longitude(report.getLongitude())
                .accountId(report.getAccountId())
                .createdDateTime(report.getCreatedDateTime())
                .updatedDateTime(report.getUpdatedDateTime())
                .build();
    }
}
