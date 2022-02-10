package com.sparta.week03project.validation;

import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.exception.CustomException;
import org.springframework.stereotype.Component;

import static com.sparta.week03project.exception.ErrorCode.*;

@Component
public class RestaurantValidation {

    public static void validationRestaurantInput(RestaurantDto restaurantDto){

        //minOrderPrice 유효성 검사
        if(restaurantDto.getMinOrderPrice() < 1000 || restaurantDto.getMinOrderPrice() > 100000) {
            throw new CustomException(INVALID_MIN_ORDER_PRICE);
        }

        if(restaurantDto.getMinOrderPrice() % 100 != 0) {
            throw new CustomException(TYPE_ERROR_MIN_ORDER_PRICE);
        }

        //deliveryFee 유효성 검사
        if(restaurantDto.getDeliveryFee() < 0 || restaurantDto.getDeliveryFee() > 10000) {
            throw new CustomException(INVALID_DELIVERY_FEE);
        }

        if(restaurantDto.getDeliveryFee() % 500 != 0) {
            throw new CustomException(TYPE_ERROR_DELIVERY_FEE);
        }

        // 배달 가능 거리 x, y 유효성 검사
        if(restaurantDto.getX() < 0 || restaurantDto.getX() > 99) {
            throw new CustomException(INVALID_X_Y);
        }

        if(restaurantDto.getY() < 0 || restaurantDto.getY() > 99) {
            throw new CustomException(INVALID_X_Y);
        }
    }
}
