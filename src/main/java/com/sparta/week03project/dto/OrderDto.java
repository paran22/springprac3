package com.sparta.week03project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDto {
    private Long restaurantId;
    private Long x;
    private Long y;
    private List<Foods> foods;

    @AllArgsConstructor
    @Getter
    public static class Foods {
        private Long id;
        private Long quantity;
    }
}

