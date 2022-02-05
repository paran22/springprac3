package com.sparta.week03project.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.validation.RestaurantValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "restrant_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long minOrderPrice;

    @Column(nullable = false)
    private Long deliveryFee;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Food> foodList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "orderRestaurant")
    private List<Order> orderList = new ArrayList<>();

    public Restaurant(RestaurantDto restaurantDto) {
        //입력값 validation
        RestaurantValidation.validationRestaurantInput(restaurantDto);

        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }



}
