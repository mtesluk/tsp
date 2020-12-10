package com.besthacks.tsp.domain.report.dto;

import com.besthacks.tsp.domain.report.entity.ReportStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder
public class ReportsRequestParamsTemplate {

    private ReportStatus reportStatus;
    private String description;
    private Double longitude;
    private Double latitude;
    private String city;
    private String street;
    private Integer streetNumber;
    private Long accountId;
    private LocalDateTime createdDateTime;

    public Optional<ReportStatus> getReportStatus() {
        return Optional.ofNullable(reportStatus);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<Double> getLongitude() {
        return Optional.ofNullable(longitude);
    }

    public Optional<Double> getLatitude() {
        return Optional.ofNullable(latitude);
    }

    public Optional<LocalDateTime> getCreatedDateTime() {
        return Optional.ofNullable(createdDateTime);
    }

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    public Optional<Integer> getStreetNumber() {
        return Optional.ofNullable(streetNumber);
    }

    public Optional<Long> getAccountId() {
        return Optional.ofNullable(accountId);
    }

    public Optional<String> getStreet() {
        return Optional.ofNullable(street);
    }


}
