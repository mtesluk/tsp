package com.besthacks.tsp.domain.report.mapper;

import com.besthacks.tsp.domain.report.dto.ReportDto;
import com.besthacks.tsp.domain.report.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(source = "id", target = "id", ignore = true)
    Report toReport(ReportDto reportDto);

    @Mapping(source = "account", target = "account", ignore = true)
    ReportDto toReportDto(Report report);
}
