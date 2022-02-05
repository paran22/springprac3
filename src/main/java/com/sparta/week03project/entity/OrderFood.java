package com.sparta.week03project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class OrderFood {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "orderfood_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderFood(String foodName, Long quantity, Long foodPrice) {
        this.name = foodName;
        this.quantity = quantity;
        this.price = quantity * foodPrice;
    }
}
