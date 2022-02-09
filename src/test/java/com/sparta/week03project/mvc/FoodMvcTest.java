package com.sparta.week03project.mvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.week03project.controller.FoodController;
import com.sparta.week03project.controller.RestaurantController;
import com.sparta.week03project.dto.FoodDto;
import com.sparta.week03project.dto.RestaurantDto;
import com.sparta.week03project.entity.Restaurant;
import com.sparta.week03project.repository.RestaurantRepository;
import com.sparta.week03project.service.FoodService;
import com.sparta.week03project.service.RestaurantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RestaurantController.class, FoodController.class})
public class FoodMvcTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    Long restaurantId = 1L;

    @MockBean
    RestaurantService restaurantService;

    @MockBean
    RestaurantRepository restaurantRepository;

    @MockBean
    FoodService foodService;

    @Nested
    @DisplayName("음식 등록")
    class RegistFood {

        @Test
        @WithMockUser(roles = "ADMIN")
        @DisplayName("신규 음식 등록_ADMIN")
        void test1() throws Exception {

            // given
            String name = "초밥정식";
            Long price = 10000L;
            FoodDto foodDto = new FoodDto(name, price);
            List<FoodDto> foodDtoList = new ArrayList<>();
            foodDtoList.add(foodDto);

            String postInfo = objectMapper.writeValueAsString(foodDtoList);

            // when - then
            mvc.perform(post("/restaurant/" + restaurantId + "/food/register")
                            .content(postInfo)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print());

        }

        @Test
        @WithMockUser(roles = "USER")
        @DisplayName("신규 음식 등록_USER")
        void test2() throws Exception {

            // given
            String name = "초밥정식";
            Long price = 10000L;
            FoodDto foodDto = new FoodDto(name, price);
            List<FoodDto> foodDtoList = new ArrayList<>();
            foodDtoList.add(foodDto);

            String postInfo = objectMapper.writeValueAsString(foodDtoList);

            // when - then
            mvc.perform(post("/restaurant/" + restaurantId + "/food/register")
                            .content(postInfo)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isForbidden())
                    .andDo(print());

        }

        @Test
        @WithMockUser(roles = "ADMIN")
        @DisplayName("음식 조회_ADMIN")
        void test3() throws Exception {
            mvc.perform(get("/restaurant/" + restaurantId + "/foods"))
                    .andExpect(status().isForbidden())
                    .andDo(print());
        }

        @Test
        @WithMockUser(roles = "USER")
        @DisplayName("음식 조회_USER")
        void test4() throws Exception {
            mvc.perform(get("/restaurant/" + restaurantId + "/foods"))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

    }
}
