package com.sparta.week03project.service;

import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.entity.Restaurant;
import com.sparta.week03project.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //배달 가능한 음식점 조회
    public List<Restaurant> getRestaurantList(Long x, Long y) {
        List<Restaurant> restaurantListAll = restaurantRepository.findAll();
        List<Restaurant> restaurantList = new ArrayList<>();
        // 3 이내의 음식점만 리스트에 추가
        for (Restaurant restaurant : restaurantListAll) {
            if(Math.abs(restaurant.getX() - x) + Math.abs(restaurant.getY() - y) <= 3) {
                restaurantList.add(restaurant);
            }
        }
        return restaurantList;
    }

}
