-- ============================================
-- 订单管理系统 - 数据库初始化脚本
-- 使用方式：在 PostgreSQL 中执行此脚本
-- ============================================

-- 1. 创建数据库（如果不存在）
-- 在 psql 中执行：CREATE DATABASE order_system;

-- 2. 连接到 order_system 数据库后执行以下 SQL

-- 产品表
CREATE TABLE IF NOT EXISTS products (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    price       DECIMAL(10,2) NOT NULL,
    stock       INTEGER NOT NULL DEFAULT 0
);

-- 订单表
CREATE TABLE IF NOT EXISTS orders (
    id            SERIAL PRIMARY KEY,
    order_no      VARCHAR(50) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    phone         VARCHAR(20) NOT NULL,
    address       VARCHAR(200),
    product_id    INTEGER NOT NULL,
    quantity      INTEGER NOT NULL,
    total_amount  DECIMAL(10,2) NOT NULL,
    status        VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    create_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 插入示例产品数据
INSERT INTO products (name, description, price, stock) VALUES
    ('iPhone 15', '苹果最新款智能手机', 6999.00, 100),
    ('MacBook Pro', '苹果笔记本电脑 16英寸', 19999.00, 50),
    ('AirPods Pro', '苹果无线降噪耳机', 1999.00, 200),
    ('iPad Air', '苹果平板电脑', 5999.00, 80),
    ('Apple Watch', '苹果智能手表 Series 9', 3999.00, 60);