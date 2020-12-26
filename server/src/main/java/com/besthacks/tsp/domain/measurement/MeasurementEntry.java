package com.besthacks.tsp.domain.measurement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementEntry {

    private LocalDateTime fromDateTime;
    private LocalDateTime tillDateTime;
    private List<MeasurementValue> values;
    private List<MeasurementIndex> indexes;
    private List<Standard> standards;
}
