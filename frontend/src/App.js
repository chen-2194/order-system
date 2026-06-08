import React, { useState } from 'react';
import ProductList from './components/ProductList';
import OrderList from './components/OrderList';
import CreateOrder from './components/CreateOrder';
import './App.css';

/**
 * 应用主组件 - 包含顶部导航和页面切换
 */
function App() {
    // 当前页面：products-产品列表, create-下单, orders-订单列表
    const [page, setPage] = useState('products');

    return (
        <div className="app">
            {/* 顶部导航栏 */}
            <header className="header">
                <h1>📦 订单管理系统</h1>
                <nav>
                    <button onClick={() => setPage('products')}
                            className={page === 'products' ? 'active' : ''}>
                        产品列表
                    </button>
                    <button onClick={() => setPage('create')}
                            className={page === 'create' ? 'active' : ''}>
                        下单
                    </button>
                    <button onClick={() => setPage('orders')}
                            className={page === 'orders' ? 'active' : ''}>
                        订单列表
                    </button>
                </nav>
            </header>

            {/* 页面内容 */}
            <main className="main">
                {page === 'products' && <ProductList />}
                {page === 'create' && <CreateOrder />}
                {page === 'orders' && <OrderList />}
            </main>
        </div>
    );
}

export default App;