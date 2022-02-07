package com.sparta.week03project.validation;

import com.sparta.week03project.dto.RestaurantDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidation {

    public static void validationRestaurantInput(RestaurantDto restaurantDto){

        //minOrderPrice 유효성 검사
        if(restaurantDto.getMinOrderPrice() < 1000 || restaurantDto.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("1,000원 ~ 100,000원 사이를 입력해주세요");
        }

        if(restaurantDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로 입력해주세요");
        }

        //deliveryFee 유효성 검사
        if(restaurantDto.getDeliveryFee() < 0 || restaurantDto.getDeliveryFee() > 10000) {
            throw new IllegalArgumentException("0원 ~ 10,000원 사이를 입력해주세요");
        }

        if(restaurantDto.getDeliveryFee() % 500 != 0) {
            throw new IllegalArgumentException("500원 단위로 입력해주세요");
        }

        // 배달 가능 거리 x, y 유효성 검사
        if(restaurantDto.getX() < 0 || restaurantDto.getX() > 99) {
            throw new IllegalArgumentException("0~99 사이로 입력해주세요");
        }

        if(restaurantDto.getY() < 0 || restaurantDto.getY() > 99) {
            throw new IllegalArgumentException("0~99 사이로 입력해주세요");
        }
    }
}
