package com.sparta.week03project.validation;

import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.exception.CustomException;
import org.springframework.stereotype.Component;

import static com.sparta.week03project.exception.ErrorCode.INVALID_FOOD_PRICE;
import static com.sparta.week03project.exception.ErrorCode.TYPE_ERROR_FOOD_PRICE;

@Component
public class FoodValidation {

    public static void validationFoodInput(FoodDto foodDto) {
        //price 유효성 검사
        if(foodDto.getPrice() < 100 || foodDto.getPrice() > 1000000) {
            throw new CustomException(INVALID_FOOD_PRICE);
        }
        if(foodDto.getPrice() % 100 != 0) {
            throw new CustomException(TYPE_ERROR_FOOD_PRICE);
        }

    }
}
