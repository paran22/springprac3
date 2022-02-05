package com.sparta.week03project.repository;

import com.sparta.week03project.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
List<Food> findAllByRestaurantId(Long restaurantId);

}
