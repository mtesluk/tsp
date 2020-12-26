package com.besthacks.tsp.service;

import com.besthacks.tsp.domain.report.dto.ReportsRequestParamsTemplate;
import com.besthacks.tsp.domain.report.dto.UpdateReportRequest;
import com.besthacks.tsp.domain.report.entity.Report;
import com.besthacks.tsp.domain.report.entity.ReportStatus;
import com.besthacks.tsp.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ReportService {

    private ReportRepository repository;

    public List<Report> getReports(ReportsRequestParamsTemplate requestParamsTemplate) {
        return repository.findAll(buildSpecification(requestParamsTemplate));
    }

    public Report updateReport(Long reportId, UpdateReportRequest updateReportRequest) {
        Report report = repository.findById(reportId).orElseThrow(() -> new NoSuchElementException(""));
        report.setDescription(updateReportRequest.getDescription());
        report.setCity(updateReportRequest.getCity());
        report.setLatitude(updateReportRequest.getLatitude());
        report.setLongitude(updateReportRequest.getLongitude());
        report.setReportStatus(updateReportRequest.getReportStatus());

        return repository.save(report);
    }

    private Specification<Report> buildSpecification(ReportsRequestParamsTemplate requestParamsTemplate) {
        Specification<Report> specification = Specification.where(null);
        if (requestParamsTemplate.getReportStatus().isPresent()) {
            specification = specification.and(hasReportStatus(requestParamsTemplate.getReportStatus().get()));
        }
        if (requestParamsTemplate.getDescription().isPresent()) {
            specification = specification.and(hasDescription(requestParamsTemplate.getDescription().get()));
        }
        if(requestParamsTemplate.getLongitude().isPresent()) {
            specification = specification.and(hasLongitude(requestParamsTemplate.getLongitude().get()));
        }
        if(requestParamsTemplate.getLatitude().isPresent()) {
            specification = specification.and(hasLatitude(requestParamsTemplate.getLatitude().get()));
        }
        if(requestParamsTemplate.getCreatedDateTime().isPresent()) {
            specification = specification.and(hasCreatedDateTime(requestParamsTemplate.getCreatedDateTime().get()));
        }
        if(requestParamsTemplate.getCreatedDateTime().isPresent()) {
            specification = specification.and(hasCreatedDateTime(requestParamsTemplate.getCreatedDateTime().get()));
        }
        if(requestParamsTemplate.getStreet().isPresent()) {
            specification = specification.and(hasStreet(requestParamsTemplate.getStreet().get()));
        }
        if(requestParamsTemplate.getCity().isPresent()) {
            specification = specification.and(hasCity(requestParamsTemplate.getCity().get()));
        }
        if(requestParamsTemplate.getStreetNumber().isPresent()) {
            specification = specification.and(hasStreetNumber(requestParamsTemplate.getStreetNumber().get()));
        }
        if(requestParamsTemplate.getAccountId().isPresent()) {
            specification = specification.and(hasAccountId(requestParamsTemplate.getAccountId().get()));
        }
        return specification;
    }

    private Specification<Report> hasReportStatus(ReportStatus reportStatus) {
        return (report, cq, cb) -> cb.equal(report.get("reportStatus"), reportStatus);
    }

    private Specification<Report> hasDescription(String description) {
        return (report, cq, cb) -> cb.like(cb.lower(report.get("description")), "%" + description.toLowerCase() + "%");
    }

    private Specification<Report> hasLongitude(Double longitude) {
        return (report, cq, cb) -> cb.equal(report.get("longitude"), longitude);
    }

    private Specification<Report> hasLatitude(Double latitude) {
        return (report, cq, cb) -> cb.equal(report.get("latitude"), latitude);
    }

    private Specification<Report> hasCity(String city) {
        return (report, cq, cb) -> cb.like(cb.lower(report.get("city")), "%" + city.toLowerCase() + "%");
    }

    private Specification<Report> hasStreet(String street) {
        return (report, cq, cb) -> cb.like(cb.lower(report.get("street")), "%" + street.toLowerCase() + "%");
    }

    private Specification<Report> hasStreetNumber(Integer streetNumber) {
        return (report, cq, cb) -> cb.equal(report.get("streetNumber"), streetNumber);
    }

    private Specification<Report> hasCreatedDateTime(LocalDateTime createdDateTime) {
        return (report, cq, cb) -> cb.equal(report.get("createdDateTime"), createdDateTime);
    }

    private Specification<Report> hasAccountId(Long accountId) {
        return (report, cq, cb) -> cb.equal(report.get("accountId"), accountId);
    }
}
