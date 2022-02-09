package com.sparta.week03project.mvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.week03project.controller.OrderController;
import com.sparta.week03project.dto.OrderDto;
import com.sparta.week03project.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = OrderController.class)
public class OrderMvcTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    OrderService orderService;

    @Nested
    @DisplayName("주문 등록")
    class RegistOrder {

        @Test
        @WithMockUser(roles = "ADMIN")
        @DisplayName("주문 등록_ADMIN")
        void test1() throws Exception {

            // given
            Long restaurantId = 1L;
            Long x = 3L;
            Long y = 4L;
            Long id = 1L;
            Long quantity = 2L;
            OrderDto.Foods orderFood = new OrderDto.Foods(id, quantity);
            List<OrderDto.Foods> orderFoodList = new ArrayList<>();
            orderFoodList.add(orderFood);
            OrderDto orderDto = new OrderDto(restaurantId, x, y, orderFoodList);

            String postInfo = objectMapper.writeValueAsString(orderDto);

            // when - then
            mvc.perform(post("/order/request")
                            .content(postInfo)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isForbidden())
                    .andDo(print());

        }

        @Test
        @WithMockUser(roles = "USER")
        @DisplayName("주문 등록_USER")
        void test2() throws Exception {
            // given
            Long restaurantId = 1L;
            Long x = 3L;
            Long y = 4L;
            Long id = 1L;
            Long quantity = 2L;
            OrderDto.Foods orderFood = new OrderDto.Foods(id, quantity);
            List<OrderDto.Foods> orderFoodList = new ArrayList<>();
            orderFoodList.add(orderFood);
            OrderDto orderDto = new OrderDto(restaurantId, x, y, orderFoodList);

            String postInfo = objectMapper.writeValueAsString(orderDto);

            // when - then
            mvc.perform(post("/order/request")
                            .content(postInfo)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print());

        }

        @Nested
        @DisplayName("주문 조회")
        class GetOrder {

            @Test
            @WithMockUser(roles = "ADMIN")
            @DisplayName("주문 조회_ADMIN")
            void test3() throws Exception {
                mvc.perform(get("/orders"))
                        .andExpect(status().isForbidden())
                        .andDo(print());
            }

            @Test
            @WithMockUser(roles = "USER")
            @DisplayName("주문 조회_USER")
            void test4() throws Exception {
                mvc.perform(get("/orders"))
                        .andExpect(status().isOk())
                        .andDo(print());
            }
        }

    }
}