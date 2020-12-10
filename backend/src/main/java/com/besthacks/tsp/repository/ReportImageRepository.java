package com.besthacks.tsp.repository;

import com.besthacks.tsp.domain.report.entity.ReportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportImageRepository extends JpaRepository<ReportImage, Long> {

    Optional<ReportImage> findByReportId(Long reportId);
}
