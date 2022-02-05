package com.sparta.week03project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDto {
    private Long restaurantId;
    private List<Foods> foods;

    @Getter
    public static class Foods {
        private Long id;
        private Long quantity;
    }

}

