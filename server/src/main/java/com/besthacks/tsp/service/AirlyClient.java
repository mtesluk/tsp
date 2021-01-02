package com.besthacks.tsp.service;

import com.besthacks.tsp.dto.Index;
import com.besthacks.tsp.dto.Installation;
import com.besthacks.tsp.dto.Measurement;

import java.util.List;

public interface AirlyClient {

    List<Installation> getInstallations(double latitude, double longitude, int maxDistanceKM, int maxResults);

    Measurement getNearestMeasurement(double latitude, double longitude, int maxDistanceKM);

    List<Index> getIndexes();

}
