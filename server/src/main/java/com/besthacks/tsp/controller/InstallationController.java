package com.besthacks.tsp.controller;

import com.besthacks.tsp.dto.Installation;
import com.besthacks.tsp.service.AirlyClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/installations")
public class InstallationController {

    private AirlyClient airlyClient;

    @GetMapping
    public List<Installation> getInstallations(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Integer maxDistanceKM,
            @RequestParam Integer maxResults
    ) {
        return airlyClient.getInstallations(latitude, longitude, maxDistanceKM, maxResults);
    }

}
