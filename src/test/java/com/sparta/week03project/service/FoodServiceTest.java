package com.sparta.week03project.service;

import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.exception.CustomException;
import com.sparta.week03project.exception.ErrorCode;
import com.sparta.week03project.repository.FoodRepository;
import com.sparta.week03project.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @Mock
    FoodRepository foodRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    @Test
    @DisplayName("음식 등록_예외1")
    public void registFood_Exception1() {
        //given
        Long restaurantId = 100L;

        String name = "초밥정식";
        Long price = 10000L;
        FoodDto foodDto = new FoodDto(name, price);
        List<FoodDto> foodDtoList = new ArrayList<>();
        foodDtoList.add(foodDto);

        when(restaurantRepository.findById(restaurantId)).thenThrow(new CustomException(ErrorCode.RESTAURANT_NOT_FOUND));

        //when
        FoodService foodService = new FoodService(foodRepository, restaurantRepository);
        Exception exception = assertThrows(CustomException.class, () -> {
            foodService.addFood(restaurantId, foodDtoList);
                });

        //then
        assertEquals("해당 음식점이 존재하지 않습니다.", exception.getMessage());

    }

}
