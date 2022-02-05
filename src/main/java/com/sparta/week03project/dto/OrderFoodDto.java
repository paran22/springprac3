package com.sparta.week03project.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderFoodDto {
    private Long restaurantId;
    private List<Foods> foods;

    @Getter
    public static class Foods {
        private Long id;
        private Long quantity;
    }

}

