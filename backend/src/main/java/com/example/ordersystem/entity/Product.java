package com.example.ordersystem.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * 产品实体类
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;            // 产品名称

    @Column(length = 500)
    private String description;     // 产品描述

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;       // 产品价格

    @Column(nullable = false)
    private Integer stock;          // 库存数量

    // ===== 构造方法 =====
    public Product() {}

    public Product(String name, String description, BigDecimal price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // ===== Getter / Setter =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}