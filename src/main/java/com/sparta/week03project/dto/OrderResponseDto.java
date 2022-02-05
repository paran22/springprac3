package com.sparta.week03project.dto;

import com.sparta.week03project.entity.OrderFood;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderFood> foods;
    private Long deliveryFee;
    private Long totalPrice;

    public OrderResponseDto(String restaurantName, List<OrderFood> orderFoodList,
                            Long deliveryFee, Long totalPrice) {
        this.restaurantName = restaurantName;
        this.foods = orderFoodList;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
