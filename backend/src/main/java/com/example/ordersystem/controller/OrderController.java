package com.example.ordersystem.controller;

import com.example.ordersystem.entity.Order;
import com.example.ordersystem.entity.Product;
import com.example.ordersystem.repository.OrderRepository;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * 订单控制器 - 提供下单和查询接口
 */
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /** 查询所有订单（按时间倒序） */
    @GetMapping
    public List<Order> list() {
        return orderRepository.findAllByOrderByCreateTimeDesc();
    }

    /** 根据 ID 查询订单 */
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * 创建订单（下单）
     * 请求体示例：{"productId":1, "quantity":2, "customerName":"张三", "phone":"13800138000", "address":"北京市"}
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        // 1. 解析参数
        Long productId = Long.valueOf(params.get("productId").toString());
        Integer quantity = Integer.valueOf(params.get("quantity").toString());
        String customerName = (String) params.get("customerName");
        String phone = (String) params.get("phone");
        String address = (String) params.getOrDefault("address", "");

        // 2. 查询产品
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "产品不存在"));
        }

        // 3. 检查库存
        if (product.getStock() < quantity) {
            return ResponseEntity.badRequest().body(Map.of("error", "库存不足，当前库存：" + product.getStock()));
        }

        // 4. 计算总金额
        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(quantity));

        // 5. 生成订单编号（格式：ORD + yyyyMMddHHmmss + 3位随机数）
        String orderNo = "ORD" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + (int)(Math.random() * 900 + 100);

        // 6. 创建订单
        Order order = new Order(orderNo, customerName, phone, address,
                productId, quantity, totalAmount, "PENDING");

        // 7. 扣减库存
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        // 8. 保存订单
        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }
}