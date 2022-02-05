package com.sparta.week03project.service;

import com.sparta.week03project.dto.OrderDto;
import com.sparta.week03project.dto.OrderResponseDto;
import com.sparta.week03project.entity.*;
import com.sparta.week03project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;
    private final OrderRepository orderRepository;

    public OrderResponseDto addOrder(OrderDto orderDto) {

        //주문 식당 찾기
        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        //주문 음식 List 생성
        List<OrderFood> orderFoodList = new ArrayList<>();
        for (int i = 0; i < orderDto.getFoods().size(); i++) {
            OrderDto.Foods foods = orderDto.getFoods().get(i);
            Food food = foodRepository.findById(foods.getId())
                    .orElseThrow(() -> new NullPointerException("해당 음식이 존재하지 않습니다."));
            OrderFood orderFood = new OrderFood(
                    food.getName(), foods.getQuantity(), food.getPrice());
            orderFoodList.add(orderFood);
        }
        //주문 음식 List 저장
        orderFoodRepository.saveAll(orderFoodList);

        //음식금액총합(sumPrice), 최종결제금액(totalPrice) 계산
        Long sumPrice = 0L;
        for (OrderFood orderfood : orderFoodList) {
            sumPrice += orderfood.getPrice();
        }
        //주문최소가격 유효성검사
        if (sumPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("주문 금액이 최소 주문 가격을 넘지 않습니다.");
        }
        Long deliveryFee = restaurant.getDeliveryFee();
        Long totalPrice = sumPrice + deliveryFee;

        //주문 저장
        Order order = Order.addOrder(restaurant, orderFoodList, totalPrice);
        orderRepository.save(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto(
                order.getOrderRestaurant().getName(),
                order.getFoods(), deliveryFee, totalPrice);

        return orderResponseDto;

    }

    public List<OrderResponseDto> getOrderList() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderResponseDto orderResponseDto = new OrderResponseDto(
                    order.getOrderRestaurant().getName(),
                    order.getFoods(),
                    order.getOrderRestaurant().getDeliveryFee(),
                    order.getTotalPrice());
            orderResponseDtoList.add(orderResponseDto);
        }
        return orderResponseDtoList;
    }

}

