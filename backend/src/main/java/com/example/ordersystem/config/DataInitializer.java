package com.example.ordersystem.config;

import com.example.ordersystem.entity.Product;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 数据初始化 - 项目启动时自动插入示例产品数据
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // 如果数据库中没有产品，则插入示例数据
        if (productRepository.count() == 0) {
            productRepository.save(new Product("iPhone 15", "苹果最新款智能手机", new BigDecimal("6999"), 100));
            productRepository.save(new Product("MacBook Pro", "苹果笔记本电脑 16英寸", new BigDecimal("19999"), 50));
            productRepository.save(new Product("AirPods Pro", "苹果无线降噪耳机", new BigDecimal("1999"), 200));
            productRepository.save(new Product("iPad Air", "苹果平板电脑", new BigDecimal("5999"), 80));
            productRepository.save(new Product("Apple Watch", "苹果智能手表 Series 9", new BigDecimal("3999"), 60));
            System.out.println("===== 示例产品数据初始化完成 =====");
        }
    }
}