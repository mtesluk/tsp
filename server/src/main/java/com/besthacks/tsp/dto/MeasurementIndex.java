package com.besthacks.tsp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementIndex {

    private String name;
    private Double value;
    private String level;
    private String description;
    private String advice;
    private String color;
}
