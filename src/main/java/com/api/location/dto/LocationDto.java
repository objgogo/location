package com.api.location.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {

    //위도
    private Double latitude;
    //경도
    private Double longitude;
}
