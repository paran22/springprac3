package com.sparta.week03project.validation;

import com.sparta.week03project.dto.FoodDto;
import org.springframework.stereotype.Component;

@Component
public class FoodValidation {

    public static void validationFoodInput(FoodDto foodDto) {
        //price 유효성 검사
        if(foodDto.getPrice() < 100 || foodDto.getPrice() > 1000000) {
            throw new IllegalArgumentException("가격은 100원 ~ 1,000,000원 사이로 입력하세요.");
        }

    }
}
