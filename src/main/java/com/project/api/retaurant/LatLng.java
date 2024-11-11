package com.project.api.retaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LatLng {
    private double lat;
    private double lon;
}
