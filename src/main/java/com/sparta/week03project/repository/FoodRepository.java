package com.sparta.week03project.repository;

import com.sparta.week03project.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {


}
