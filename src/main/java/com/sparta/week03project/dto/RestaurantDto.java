package com.sparta.week03project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantDto {
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
    private Long x;
    private Long y;


}
