package com.sparta.week03project.service;

import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.entity.Food;
import com.sparta.week03project.entity.Restaurant;
import com.sparta.week03project.repository.FoodRepository;
import com.sparta.week03project.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(
            FoodRepository foodRepository,
            RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public void addFood(Long restaurantId, List<FoodDto> foodDtoList) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        // 중복검사
        List<FoodDto> checkedFoodDtoList = duplicateCheck(restaurant,foodDtoList);

        // 저장하기
        List<Food> foodList = new ArrayList<>();
        for (FoodDto foodDto : checkedFoodDtoList) {
            Food food = new Food(foodDto, restaurant);
            foodList.add(food);
        }
        foodRepository.saveAll(foodList);
    }

    // 중복검사
    private List<FoodDto> duplicateCheck(Restaurant restaurant, List<FoodDto> foodDtoList) {
        //name 중복검사(입력값들)
        for (int i = 0; i < foodDtoList.size()-1; i++) {
            for(int j = i+1; j < foodDtoList.size(); j++) {
                if(foodDtoList.get(i).getName().equals(foodDtoList.get(j).getName())) {
                    throw new IllegalArgumentException("중복된 이름이 있습니다");
                }
            }
        }
        //name 중복검사(기존 입력값)
        List<FoodDto> checkedFoodDtoList = new ArrayList<>();
        List<Food> savedFoodList = restaurant.getFoodList();
        for (FoodDto foodDto : foodDtoList) {
            for (Food food : savedFoodList) {
                if(foodDto.getName().equals(food.getName())) {
                    throw new IllegalArgumentException("이미 등록된 음식입니다");
                }
            }
            checkedFoodDtoList.add(foodDto);
        }
        return checkedFoodDtoList;
    }


    public List<Food> getFoodList(Long restaurantId) {
//        List<Food> foodList = foodRepository.findAllByRestaurantId(restaurantId);
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));
        List<Food> foodList = restaurant.getFoodList();
        return foodList;
    }
}
