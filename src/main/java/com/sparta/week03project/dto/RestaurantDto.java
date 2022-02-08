package com.sparta.week03project.dto;

import lombok.Getter;


@Getter
public class RestaurantDto {
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
    private Long x;
    private Long y;


    //테스트에서 사용
    public RestaurantDto(String name, Long minOrderPrice, Long deliveryFee, Long x, Long y) {
    }
}
