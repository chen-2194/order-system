import React, { useState, useEffect } from 'react';
import api from '../api';

/**
 * 产品列表组件 - 展示所有可购买的产品
 */
function ProductList() {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);

    // 页面加载时获取产品列表
    useEffect(() => {
        api.get('/products')
            .then(res => setProducts(res.data))
            .catch(err => console.error('获取产品列表失败:', err))
            .finally(() => setLoading(false));
    }, []);

    if (loading) return <div className="loading">加载中...</div>;

    return (
        <div>
            <h2 style={{ marginBottom: 20 }}>产品列表</h2>
            {products.length === 0 ? (
                <div className="empty">暂无产品数据</div>
            ) : (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>产品名称</th>
                            <th>描述</th>
                            <th>价格</th>
                            <th>库存</th>
                        </tr>
                    </thead>
                    <tbody>
                        {products.map(p => (
                            <tr key={p.id}>
                                <td>{p.id}</td>
                                <td><strong>{p.name}</strong></td>
                                <td>{p.description}</td>
                                <td>¥{p.price}</td>
                                <td>{p.stock}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default ProductList;