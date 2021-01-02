package com.besthacks.tsp.controller;

import com.besthacks.tsp.dto.Measurement;
import com.besthacks.tsp.service.AirlyClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/measurements")
public class MeasureController {

    private AirlyClient airlyClient;

    @GetMapping("/nearest")
    public Measurement getNearestMeasurement(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Integer maxDistanceKM
    ) {
        return airlyClient.getNearestMeasurement(latitude, longitude, maxDistanceKM);
    }

}
