package com.sparta.week03project.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.week03project.controller.RestaurantController;
import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.service.FoodService;
import com.sparta.week03project.service.OrderService;
import com.sparta.week03project.service.RestaurantService;
import com.sparta.week03project.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = RestaurantController.class)
public class UserMvcTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    RestaurantService restaurantService;

    @MockBean
    FoodService foodService;

    @MockBean
    OrderService orderService;

    @Nested
    @DisplayName("음식점 등록")
    class Restaurant {

        @Test
        @WithMockUser(roles = "ADMIN")
        @DisplayName("신규 음식점 등록_ADMIN")
        void test1() throws Exception {

            // given
            String name = "일식집";
            Long minOrderPrice = 10000L;
            Long deliveryFee = 2000L;
            Long x = 3L;
            Long y = 4L;
            RestaurantDto restaurantDto = new RestaurantDto(
                    name, minOrderPrice, deliveryFee, x, y
            );

            String postInfo = objectMapper.writeValueAsString(restaurantDto);

            // when - then
            mvc.perform(post("/restaurant/register")
                            .content(postInfo)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print());

        }

        @Test
        @WithMockUser(roles = "USER")
        @DisplayName("신규 음식점 등록_USER_403")
        void test2() throws Exception {

            // given
            String name = "일식집";
            Long minOrderPrice = 10000L;
            Long deliveryFee = 2000L;
            Long x = 3L;
            Long y = 4L;
            RestaurantDto restaurantDto = new RestaurantDto(
                    name, minOrderPrice, deliveryFee, x, y
            );

            String postInfo = objectMapper.writeValueAsString(restaurantDto);

            // when - then
            mvc.perform(post("/restaurant/register")
                            .content(postInfo)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isForbidden())
                    .andDo(print());
        }

        @Test
        @WithMockUser(roles = "ADMIN")
        @DisplayName("신규 음식점 등록_ADMIN")
        void test3() throws Exception {
            mvc.perform(get("/restaurants")
                            .param("x","3").param("y","4"))
                    .andExpect(status().isForbidden())
                    .andDo(print());
        }

        @Test
        @WithMockUser(roles = "USER")
        @DisplayName("신규 음식점 등록_USER")
        void test4() throws Exception {
            mvc.perform(get("/restaurants")
                            .param("x","3").param("y","4"))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

}