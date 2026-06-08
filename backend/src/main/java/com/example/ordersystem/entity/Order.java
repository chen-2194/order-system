package com.example.ordersystem.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String orderNo;             // 订单编号

    @Column(nullable = false, length = 100)
    private String customerName;        // 客户姓名

    @Column(nullable = false, length = 20)
    private String phone;               // 联系电话

    @Column(length = 200)
    private String address;             // 收货地址

    @Column(nullable = false)
    private Long productId;             // 产品 ID

    @Column(nullable = false)
    private Integer quantity;           // 购买数量

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;     // 订单总金额

    @Column(nullable = false, length = 20)
    private String status;              // 订单状态：PENDING-待处理, COMPLETED-已完成, CANCELLED-已取消

    @Column(nullable = false)
    private LocalDateTime createTime;   // 创建时间

    // ===== 构造方法 =====
    public Order() {}

    public Order(String orderNo, String customerName, String phone, String address,
                 Long productId, Integer quantity, BigDecimal totalAmount, String status) {
        this.orderNo = orderNo;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createTime = LocalDateTime.now();
    }

    // ===== Getter / Setter =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}