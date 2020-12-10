package com.besthacks.tsp.domain.installation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Installation {

    private Long id;
    private Location location;
    private Long locationId;
    private Address address;
    private Double elevation;

}
