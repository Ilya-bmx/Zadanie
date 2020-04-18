package com.project.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "ORDER_DETAIL")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String productTitle;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToMany(mappedBy = "order")
    private Set<Point> point;
}
