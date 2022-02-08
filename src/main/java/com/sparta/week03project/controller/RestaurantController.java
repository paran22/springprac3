package com.sparta.week03project.controller;

import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.entity.Restaurant;
import com.sparta.week03project.entity.UserRoleEnum;
import com.sparta.week03project.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    //음식점 등록
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/restaurant/register")
    public Restaurant postRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.addRestaurant(restaurantDto);
    }

    //배달 가능한 음식점 조회(3 이내)
    @Secured(UserRoleEnum.Authority.USER)
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantList(@RequestParam Long x, @RequestParam Long y) {
        return restaurantService.getRestaurantList(x, y);
    }

}
