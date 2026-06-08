# 订单管理系统

一个简单的订单管理系统，包含产品浏览、下单和订单查询功能。

## 技术栈

- **前端**：React 18
- **后端**：Java 17 + Spring Boot 3.2
- **数据库**：PostgreSQL

## 项目结构

```
order-system/
├── backend/                    # 后端项目
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/example/ordersystem/
│       │   ├── OrderSystemApplication.java    # 启动类
│       │   ├── config/
│       │   │   ├── CorsConfig.java            # 跨域配置
│       │   │   └── DataInitializer.java       # 数据初始化
│       │   ├── controller/
│       │   │   ├── ProductController.java     # 产品接口
│       │   │   └── OrderController.java       # 订单接口
│       │   ├── entity/
│       │   │   ├── Product.java               # 产品实体
│       │   │   └── Order.java                 # 订单实体
│       │   └── repository/
│       │       ├── ProductRepository.java     # 产品 DAO
│       │       └── OrderRepository.java       # 订单 DAO
│       └── resources/
│           └── application.yml                # 配置文件
├── frontend/                   # 前端项目
│   ├── package.json
│   ├── public/index.html
│   └── src/
│       ├── index.js
│       ├── App.js              # 主组件
│       ├── App.css             # 样式
│       ├── api.js              # API 配置
│       └── components/
│           ├── ProductList.js  # 产品列表
│           ├── CreateOrder.js  # 下单
│           └── OrderList.js    # 订单列表
├── init.sql                    # 数据库初始化脚本
└── README.md
```

## 启动方式

### 1. 启动数据库

确保已安装 PostgreSQL，创建数据库：

```sql
CREATE DATABASE order_system;
```

### 2. 启动后端

```bash
cd backend
mvn spring-boot:run
```

后端启动后会自动创建表结构并插入示例产品数据。

### 3. 启动前端

```bash
cd frontend
npm install
npm start
```

前端默认在 http://localhost:3000 启动。

## 接口说明

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | /api/products | 获取所有产品 |
| GET | /api/products/{id} | 获取单个产品 |
| GET | /api/orders | 获取所有订单 |
| GET | /api/orders/{id} | 获取单个订单 |
| POST | /api/orders | 创建订单 |