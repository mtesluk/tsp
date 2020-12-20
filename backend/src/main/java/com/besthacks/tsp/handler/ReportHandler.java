package com.besthacks.tsp.handler;

import com.besthacks.tsp.domain.report.dto.ReportDto;
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

    public List<ReportDto> getReports(ReportsRequestParamsTemplate requestParamsTemplate) {
        return service.getReports(requestParamsTemplate)
                .stream()
                .map(mapper::toReportDto)
                .collect(Collectors.toList());
    }

    public ReportDto getReport(Long id) {
        return repository.findById(id)
                .map(mapper::toReportDto)
                .orElseThrow(() -> new NoSuchElementException("Report with id " + id + "not found!"));
    }

    public ReportDto createReport(ReportDto reportDto) {
        return mapper.toReportDto(repository.save(mapper.toReport(reportDto)));
    }

    public ReportDto updateReport(Long id, UpdateReportRequest updateReportRequest) {
        return mapper.toReportDto(service.updateReport(id, updateReportRequest));
    }

}
