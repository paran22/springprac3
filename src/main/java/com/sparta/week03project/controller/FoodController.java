package com.sparta.week03project.controller;

import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.entity.Food;
import com.sparta.week03project.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFood(@PathVariable Long restaurantId,
                        @RequestBody List<FoodDto> foodDtoList
    ) {
        foodService.addFood(restaurantId, foodDtoList);
    }

    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoodList(@PathVariable Long restaurantId){
        List<Food> foodList = foodService.getFoodList(restaurantId);
        return foodList;
    }
}
