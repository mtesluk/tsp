package com.besthacks.tsp.domain.report.dto;

import com.besthacks.tsp.domain.report.entity.ReportStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReportResponse {
    private Long id;
    private String description;
    private ReportStatus reportStatus;
    private String city;
    private String street;
    private Integer streetNumber;
    private Double latitude;
    private Double longitude;
    private Long accountId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDateTime;
}
