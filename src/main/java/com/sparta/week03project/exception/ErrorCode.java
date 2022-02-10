package com.sparta.week03project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 400 Bad Request
    INVALID_MIN_ORDER_PRICE(HttpStatus.BAD_REQUEST, "400_1", "1,000원 ~ 100,000원 사이를 입력해주세요."),
    TYPE_ERROR_MIN_ORDER_PRICE(HttpStatus.BAD_REQUEST, "400_2", "100원 단위로 입력해주세요."),
    INVALID_DELIVERY_FEE(HttpStatus.BAD_REQUEST, "400_3", "0원 ~ 10,000원 사이를 입력해주세요."),
    TYPE_ERROR_DELIVERY_FEE(HttpStatus.BAD_REQUEST, "400_4", "500원 단위로 입력해주세요."),
    INVALID_X_Y(HttpStatus.BAD_REQUEST, "400_5", "0~99 사이로 입력해주세요."),
    DUPLICATE_FOOD_NAME(HttpStatus.BAD_REQUEST, "400_6", "중복된 이름이 있습니다."),
    DUPLICATE_FOOD(HttpStatus.BAD_REQUEST, "400_7", "이미 등록된 음식입니다."),
    INVALID_FOOD_PRICE(HttpStatus.BAD_REQUEST, "400_8", "가격은 100원 ~ 1,000,000원 사이로 입력하세요."),
    TYPE_ERROR_FOOD_PRICE(HttpStatus.BAD_REQUEST, "400_9", "가격은 100원 단위로 입력하세요."),
    INVALID_ORDER_PRICE(HttpStatus.BAD_REQUEST, "400_10", "주문 금액이 최소 주문 가격을 넘지 않습니다."),
    INVALID_ORDER_QUANTITY(HttpStatus.BAD_REQUEST, "400_11", "1~100 사이로 입력해주세요."),
    DUPLICATE_USER_NAME(HttpStatus.BAD_REQUEST, "400_12", "중복된 사용자 ID 가 존재합니다."),
    INVALID_ADMIN_TOKEN(HttpStatus.BAD_REQUEST, "400_13", "관리자 암호가 틀려 등록이 불가능합니다."),

    // 403 Forbidden
    NOT_ALLOWED(HttpStatus.FORBIDDEN, "403", "인가되지 않은 페이지입니다."),


    // 404 Not Found
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "404_1", "해당 음식점이 존재하지 않습니다."),
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "404_2", "해당 음식이 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
