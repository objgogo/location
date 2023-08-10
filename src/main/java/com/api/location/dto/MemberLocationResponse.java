package com.api.location.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberLocationResponse {

    private Long locationId;

    private LocationDto start; // 기준점

    private List<LocationDistance> distanceList;




}
