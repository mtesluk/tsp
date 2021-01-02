package com.besthacks.tsp.mapper;

import com.besthacks.tsp.dto.ReportDto;
import com.besthacks.tsp.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(source = "id", target = "id", ignore = true)
    Report toReport(ReportDto reportDto);

    @Mapping(source = "account", target = "account", ignore = true)
    ReportDto toReportDto(Report report);
}
