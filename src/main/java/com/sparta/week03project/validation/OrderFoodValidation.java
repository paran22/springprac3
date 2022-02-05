package com.sparta.week03project.validation;

import org.springframework.stereotype.Component;

@Component
public class OrderFoodValidation {
    public static void validationOrderFoodInput(Long quantity) {
        if(quantity < 1 || quantity > 100) {
            throw new IllegalArgumentException("1~100 사이로 입력해주세요");
        }
    }
}
