package com.besthacks.tsp.controller;

import com.besthacks.tsp.domain.report.dto.CreateReportRequest;
import com.besthacks.tsp.domain.report.dto.ReportResponse;
import com.besthacks.tsp.domain.report.dto.ReportsRequestParamsTemplate;
import com.besthacks.tsp.domain.report.dto.UpdateReportRequest;
import com.besthacks.tsp.domain.report.entity.ReportImage;
import com.besthacks.tsp.domain.report.entity.ReportStatus;
import com.besthacks.tsp.handler.ReportHandler;
import com.besthacks.tsp.handler.ReportImageHandler;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
public class ReportController {

    private ReportHandler reportHandler;
    private ReportImageHandler reportImageHandler;

    @GetMapping
    public List<ReportResponse> getReports(
            @RequestParam(required = false) ReportStatus reportStatus,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) Integer streetNumber,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdDateTime
    ) {
        return reportHandler.getReports(
                ReportsRequestParamsTemplate.builder()
                        .description(description)
                        .reportStatus(reportStatus)
                        .longitude(longitude)
                        .latitude(latitude)
                        .createdDateTime(createdDateTime)
                        .city(city)
                        .street(street)
                        .accountId(accountId)
                        .streetNumber(streetNumber)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ReportResponse getReport(@PathVariable Long id) {
        return reportHandler.getReport(id);
    }

    @PostMapping
    public ReportResponse createReport(@RequestBody CreateReportRequest createReportRequest) {
        return reportHandler.createReport(createReportRequest);
    }

    @PutMapping("/{id}")
    public ReportResponse updateReport(@PathVariable Long id, @RequestBody UpdateReportRequest updateReportRequest) {
        return reportHandler.updateReport(id, updateReportRequest);
    }

    @PostMapping("/{id}/image")
    public void uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        reportImageHandler.saveImage(id, file);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id) {
        ReportImage image = reportImageHandler.getImageByReportId(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

}
