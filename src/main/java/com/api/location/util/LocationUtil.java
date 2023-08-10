package com.api.location.util;

import org.springframework.stereotype.Component;

@Component
public class LocationUtil {

    private static final double EARTH_RADIUS = 6378.1;

    public Double getDistanceByLocation(Double startLatitude, Double startLongitude, Double endLatitude, Double endLongitude){

        Double dLat = Math.toRadians(endLatitude - startLatitude);
        Double dLon = Math.toRadians(endLongitude - startLongitude);

        Double cal1 = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(startLatitude)) * Math.cos(Math.toRadians(endLatitude))*Math.sin(dLon/2)*Math.sin(dLon/2);
        Double cal2 = 2 * Math.atan2(Math.sqrt(cal1), Math.sqrt(1-cal1));
        Double distance = EARTH_RADIUS* cal2 * 1000;

        return distance;
    }
}
