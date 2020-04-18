package com.project.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ADDRESS")
    private String address;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private SalePointSpecific salePointSpecific;
    @ManyToMany
    @JoinColumn(name = "order_id")
    private Set<Order> order;

}
