package com.sparta.week03project.service;

import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.entity.Food;
import com.sparta.week03project.entity.Restaurant;
import com.sparta.week03project.repository.FoodRepository;
import com.sparta.week03project.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public Food addFood(Long restaurantId, FoodDto foodDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));
        //name 중복검사
        List<Food> foodList = restaurant.getFoodList();
        for (Food food : foodList) {
            if (food.getName().equals(foodDto.getName())) {
                throw new IllegalArgumentException("중복된 이름이 있습니다");
            }
        }
        Food food = new Food(foodDto, restaurant);
        foodRepository.save(food);
        return food;
    }

    public List<Food> getFoodList(Long restaurantId) {
//        List<Food> foodList = foodRepository.findAllByRestaurantId(restaurantId);
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));
        List<Food> foodList = restaurant.getFoodList();
        return foodList;
    }
}
