package com.sparta.week03project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.validation.FoodValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "food_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Food(FoodDto foodDto, Restaurant restaurant) {
        //유효성 검사
        FoodValidation.validationFoodInput(foodDto);

        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurant = restaurant;
    }
}
