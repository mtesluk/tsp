package com.besthacks.tsp.service;

import com.besthacks.tsp.domain.index.Index;
import com.besthacks.tsp.domain.index.Level;
import com.besthacks.tsp.domain.installation.Address;
import com.besthacks.tsp.domain.installation.Installation;
import com.besthacks.tsp.domain.installation.Location;
import com.besthacks.tsp.domain.measurement.Measurement;
import com.besthacks.tsp.domain.measurement.MeasurementEntry;
import com.besthacks.tsp.domain.measurement.MeasurementIndex;
import com.besthacks.tsp.domain.measurement.MeasurementValue;
import com.besthacks.tsp.domain.measurement.Standard;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MockedAirlyClient implements AirlyClient {
    @Override
    public List<Installation> getInstallations(double latitude, double longitude, int maxDistanceKM, int maxResults) {
        return Arrays.asList(
                new Installation(
                        8077L, new Location(50.062006, 19.940984),
                        8077L, new Address("Poland", "Krakow", "Mikołajska", "1", "Krakow", "Mikołajska"),
                        220.38
                ));
    }

    @Override
    public Measurement getNearestMeasurement(double latitude, double longitude, int maxDistanceKM) {
        return new Measurement(
                new MeasurementEntry(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        Arrays.asList(
                                new MeasurementValue("PM1", 26.73),
                                new MeasurementValue("PM25", 41.45)
                        ),
                        Arrays.asList(new MeasurementIndex(
                                "AIRLY_CAQI", 66.79, "MEDIUM", "Well... It's been better.",
                                "Things were good once... Let's hope it doesn't get worse!",
                                "#EFBB0F"
                        )),
                        Arrays.asList(new Standard(
                                "WHO", "PM25", 25.0, 165.7, "24h"
                        ))
                ),
                Arrays.asList(new MeasurementEntry(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        Arrays.asList(
                                new MeasurementValue("PM1", 26.73),
                                new MeasurementValue("PM25", 41.45)
                        ),
                        Arrays.asList(new MeasurementIndex(
                                "AIRLY_CAQI", 66.79, "MEDIUM", "Well... It's been better.",
                                "Things were good once... Let's hope it doesn't get worse!",
                                "#EFBB0F"
                        )),
                        Arrays.asList(new Standard(
                                "WHO", "PM25", 25.0, 165.7, "24h"
                        ))
                ))
        );
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(
                new Index(
                        "AIRLY_CAQI",
                        Arrays.asList(
                                new Level("0-25", "VERY_LOW", "Very Low", "#6BC926"),
                                new Level("25-50", "LOW", "Low", "#D1CF1E"),
                                new Level("50-75", "MEDIUM", "Medium", "#EFBB0F"),
                                new Level("75-87.5", "HIGH", "High", "#EF7120"),
                                new Level("87.5-100", "VERY_HIGH", "Very High", "#EF2A36"),
                                new Level("100-125", "EXTREME", "Extreme", "#B00057"),
                                new Level("125+", "AIRMAGEDDON", "Airmageddon!", "#770078")
                        )

                ),
                new Index(
                        "CAQI",
                        Arrays.asList(
                                new Level("0-25", "VERY_LOW", "Very Low", "#6BC926"),
                                new Level("25-50", "LOW", "Low", "#D1CF1E"),
                                new Level("50-75", "MEDIUM", "Medium", "#EFBB0F"),
                                new Level("75-100", "HIGH", "High", "#EF7120"),
                                new Level("100+", "VERY_HIGH", "Very High", "#EF2A36")
                        )

                )

        );
    }
}
