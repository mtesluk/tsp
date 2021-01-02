package com.besthacks.tsp.service;

import com.besthacks.tsp.dto.Index;
import com.besthacks.tsp.dto.Installation;
import com.besthacks.tsp.dto.Measurement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class DefaultAirlyClient implements AirlyClient {

    private RestTemplate airlyRestTemplate;

    @Override
    public List<Installation> getInstallations(double latitude, double longitude, int maxDistanceKM, int maxResults) {
        ResponseEntity<Installation[]> installations = airlyRestTemplate.getForEntity(
                "/v2/installations/nearest?lat=" + latitude + "&lng=" + longitude + "&maxDistanceKM=" + maxDistanceKM + "&maxResults=" + maxResults, Installation[].class
        );
        return Arrays.asList(installations.getBody());
    }

    @Override
    public Measurement getNearestMeasurement(double latitude, double longitude, int maxDistanceKM) {
        ResponseEntity<Measurement> measurements = airlyRestTemplate.getForEntity(
                "/v2/measurements/nearest?lat=" + latitude + "&lng=" + longitude + "&maxDistanceKM=" + maxDistanceKM, Measurement.class
        );
        return measurements.getBody();
    }

    @Override
    public List<Index> getIndexes() {
        ResponseEntity<Index[]> indexes = airlyRestTemplate.getForEntity(
                "/v2/meta/indexes", Index[].class
        );
        return Arrays.asList(indexes.getBody());
    }

}
