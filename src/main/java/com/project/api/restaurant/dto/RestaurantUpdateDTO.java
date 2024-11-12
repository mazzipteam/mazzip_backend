package com.project.api.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RestaurantUpdateDTO {
    private Long restaurantId;
    private String name;
    private String address;
    private String businessName;
    private String propritor;
    private String category;
    private String latLng; // 위경도에 대한 JSON 값 저장 // 주소와 일치해야함
    private String telNum;
    private String takeOut;
    private String userId; // Long으로 타입 변환 필요(null로 반환 가능하게 하기 위함)
}
