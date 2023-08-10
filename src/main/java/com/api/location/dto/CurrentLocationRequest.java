package com.api.location.dto;

import com.api.location.common.LocationState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentLocationRequest {

    //위도
    private Double latitude;
    //경도
    private Double longitude;

    //상태
    private LocationState state;

}
