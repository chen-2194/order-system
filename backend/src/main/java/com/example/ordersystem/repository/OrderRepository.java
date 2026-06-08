package com.example.ordersystem.repository;

import com.example.ordersystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单数据访问层
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /** 按创建时间降序查询所有订单 */
    List<Order> findAllByOrderByCreateTimeDesc();
}