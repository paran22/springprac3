package com.sparta.week03project.controller;

import com.sparta.week03project.dto.OrderDto;
import com.sparta.week03project.dto.OrderResponseDto;
import com.sparta.week03project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    // 주문
    @PostMapping("/order/request")
    public OrderResponseDto addOrder(@RequestBody OrderDto orderDto){
        OrderResponseDto orderResponseDto = orderService.addOrder(orderDto);
        return orderResponseDto;
    }

    // 주문 조회
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders() {
        List<OrderResponseDto> orderResponseDtoList = orderService.getOrderList();
        return orderResponseDtoList;
    }
}
