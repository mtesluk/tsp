package com.besthacks.tsp.controller;

import com.besthacks.tsp.dto.AccountDto;
import com.besthacks.tsp.entity.AccountRole;
import com.besthacks.tsp.dto.ReportDto;
import com.besthacks.tsp.dto.ReportsRequestParamsTemplate;
import com.besthacks.tsp.dto.UpdateReportRequest;
import com.besthacks.tsp.entity.ReportImage;
import com.besthacks.tsp.entity.ReportStatus;
import com.besthacks.tsp.service.AccountService;
import com.besthacks.tsp.service.ReportImageService;
import com.besthacks.tsp.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
public class ReportController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportImageService reportSImageervice;

    @GetMapping
    public List<ReportDto> getReports(
            @RequestParam(required = false) ReportStatus reportStatus,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) Integer streetNumber,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdDateTime,
            Authentication authentication
    ) {
        AccountDto accountDto = accountService.loadAccountByUsername(authentication.getName());
        if (accountDto.getRole() == AccountRole.USER) {
            accountId = accountDto.getId();
        }
        List<ReportDto> reports = reportService.getReports(ReportsRequestParamsTemplate.builder()
                .description(description)
                .reportStatus(reportStatus)
                .longitude(longitude)
                .latitude(latitude)
                .createdDateTime(createdDateTime)
                .city(city)
                .street(street)
                .accountId(accountId)
                .streetNumber(streetNumber)
                .build());
        return reports;
    }

    @GetMapping("/{id}")
    public ReportDto getReport(@PathVariable Long id) {
        return reportService.getReport(id);
    }

    @PostMapping
    public ReportDto createReport(@RequestBody ReportDto reportDto, Authentication authentication) {
        AccountDto accountDto = accountService.loadAccountByUsername(authentication.getName());
        reportDto.setAccount(accountDto);
        reportDto.setReportStatus(ReportStatus.NEW);
        return reportService.createReport(reportDto);
    }

    @PutMapping("/{id}")
    public ReportDto updateReport(@PathVariable Long id, @RequestBody UpdateReportRequest updateReportRequest) {
        return reportService.updateReport(id, updateReportRequest);
    }

    @PostMapping("/{id}/image")
    public void uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        reportSImageervice.saveImage(id, file);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id) {
        ReportImage image = reportSImageervice.getImageByReportId(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

}
