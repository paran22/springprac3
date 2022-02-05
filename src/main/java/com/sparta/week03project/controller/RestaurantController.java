package com.sparta.week03project.controller;

import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.entity.Restaurant;
import com.sparta.week03project.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

//    @PostMapping(value = "/restaurant/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    //음식점 등록
    @PostMapping("/restaurant/register")
    public Long postRestaurant(@RequestBody RestaurantDto restaurantDto) throws IllegalArgumentException {
        Restaurant restaurant = restaurantService.addRestaurant(restaurantDto);
        return restaurant.getId();
    }

    //음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantList() {
        List<Restaurant> restaurantList = restaurantService.getRestaurantList();
        return restaurantList;
    }

}
