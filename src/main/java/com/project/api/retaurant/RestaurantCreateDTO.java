package com.project.api.retaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RestaurantCreateDTO {
    private String name;
    private String address;
    private String businessName;
    private String propritor;
    private String category;
    //private Region region;
    private String latLng; // 위경도에 대한 JSON 값 저장
    private String telNum;
    private Character takeOut;
    private Long userId;
}
