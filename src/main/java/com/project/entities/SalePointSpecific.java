package com.project.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;

@Data
@Builder
@Table(name = "SalePoint")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SalePointSpecific {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "LABEL")
    private String label;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "salePointSpecific")
    private Point point;
}

