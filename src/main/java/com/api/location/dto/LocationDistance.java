package com.api.location.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDistance {

    private Double startLongitude;
    private Double startLatitude;

    private Double endLongitude;
    private Double endLatitude;

    private Double distance;
}
