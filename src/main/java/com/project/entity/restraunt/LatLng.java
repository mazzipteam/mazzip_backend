package com.project.entity.restraunt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LatLng {
    private Double lat;
    private Double lon;
}
