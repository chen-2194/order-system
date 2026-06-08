package com.example.ordersystem.controller;

import com.example.ordersystem.entity.Product;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品控制器 - 提供产品查询接口
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")     // 允许跨域（前端开发时使用）
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /** 查询所有产品 */
    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

    /** 根据 ID 查询单个产品 */
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }
}