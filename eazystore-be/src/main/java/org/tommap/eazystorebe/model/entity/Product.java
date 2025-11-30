package org.tommap.eazystorebe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer popularity;

    @Column(length = 500)
    private String imageUrl;

    @Builder
    public Product(String name, String description, BigDecimal price, Integer popularity, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.popularity = popularity;
        this.imageUrl = imageUrl;
    }
}
