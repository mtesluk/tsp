package com.besthacks.tsp.handler;

import com.besthacks.tsp.domain.report.dto.CreateReportRequest;
import com.besthacks.tsp.domain.report.dto.ReportResponse;
import com.besthacks.tsp.domain.report.dto.ReportsRequestParamsTemplate;
import com.besthacks.tsp.domain.report.dto.UpdateReportRequest;
import com.besthacks.tsp.domain.report.mapper.ReportMapper;
import com.besthacks.tsp.repository.ReportRepository;
import com.besthacks.tsp.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportHandler {

    private ReportService service;
    private ReportRepository repository;
    private ReportMapper mapper;

    public List<ReportResponse> getReports(ReportsRequestParamsTemplate requestParamsTemplate) {
        return service.getReports(requestParamsTemplate)
                .stream()
                .map(mapper::mapToReportResponse)
                .collect(Collectors.toList());
    }

    public ReportResponse getReport(Long id) {
        return repository.findById(id)
                .map(mapper::mapToReportResponse)
                .orElseThrow(() -> new NoSuchElementException("Report with id " + id + "not found!"));
    }

    public ReportResponse createReport(CreateReportRequest createReportRequest) {
        return mapper.mapToReportResponse(repository.save(mapper.mapToReport(createReportRequest)));
    }

    public ReportResponse updateReport(Long id, UpdateReportRequest updateReportRequest) {
        return mapper.mapToReportResponse(service.updateReport(id, updateReportRequest));
    }

}
