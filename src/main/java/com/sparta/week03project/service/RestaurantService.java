package com.sparta.week03project.service;

import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.entity.Restaurant;
import com.sparta.week03project.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    //음식점 등록
    public Restaurant addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    //음식점 조회
    public List<Restaurant> getRestaurantList() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList;
    }

}
