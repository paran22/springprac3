package com.sparta.week03project.validation;

import com.sparta.week03project.exception.CustomException;
import org.springframework.stereotype.Component;

import static com.sparta.week03project.exception.ErrorCode.INVALID_ORDER_QUANTITY;

@Component
public class OrderFoodValidation {
    public static void validationOrderFoodInput(Long quantity) {
        if(quantity < 1 || quantity > 100) {
            throw new CustomException(INVALID_ORDER_QUANTITY);
        }
    }
}
