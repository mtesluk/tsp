package com.besthacks.tsp.domain.measurement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Standard {

    private String name;
    private String pollutant;
    private Double limit;
    private Double percent;
    private String averaging;
}
