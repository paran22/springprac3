package com.sparta.week03project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "restaurant_id")
    private Restaurant orderRestaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderFood> foods = new ArrayList<>();

    @Column(nullable = false)
    private Long totalPrice;


    //연관관계 메소드
    public void setOrderRestaurant(Restaurant restaurant) {
        this.orderRestaurant = restaurant;
        restaurant.getOrderList().add(this);
    }

    public void addOrderFoodList(List<OrderFood> foods, Long totalPrice) {
        this.foods = foods;
        for (OrderFood orderFood : foods) {
            orderFood.setOrder(this);
        }
        this.totalPrice = totalPrice;
    }


    //생성 메소드

    public static Order addOrder(Restaurant restaurant,
                                 List<OrderFood> foods, Long totalPrice) {
        Order order = new Order();
        order.setOrderRestaurant(restaurant);
        order.addOrderFoodList(foods, totalPrice);

        return order;
    }


    //전체 주문가격 조회
//   public Long getTotalPrice() {
//        Long foodPrice = 0L;
//        Long totalPrice = 0L;
//        for (OrderFood orderFood: orderFoodList) {
//            foodPrice += orderFood.getPrice();
//        }
//        totalPrice = foodPrice + orderRestaurant.getDeliveryFee();
//        return totalPrice;
//    }

}
