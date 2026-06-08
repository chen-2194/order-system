import React, { useState, useEffect } from 'react';
import api from '../api';

/**
 * 订单列表组件 - 展示所有订单信息
 */
function OrderList() {
    const [orders, setOrders] = useState([]);
    const [products, setProducts] = useState({});   // {产品ID: 产品名称}
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // 同时获取订单和产品数据
        Promise.all([
            api.get('/orders'),
            api.get('/products')
        ]).then(([ordersRes, productsRes]) => {
            setOrders(ordersRes.data);
            // 构建产品ID -> 产品名称的映射
            const productMap = {};
            productsRes.data.forEach(p => { productMap[p.id] = p.name; });
            setProducts(productMap);
        }).catch(err => console.error('获取数据失败:', err))
          .finally(() => setLoading(false));
    }, []);

    // 格式化时间
    const formatTime = (timeStr) => {
        return new Date(timeStr).toLocaleString('zh-CN');
    };

    // 状态标签
    const statusLabel = (status) => {
        const map = {
            'PENDING': { text: '待处理', className: 'status-pending' },
            'COMPLETED': { text: '已完成', className: 'status-completed' },
            'CANCELLED': { text: '已取消', className: 'status-cancelled' }
        };
        const s = map[status] || { text: status, className: '' };
        return <span className={`status ${s.className}`}>{s.text}</span>;
    };

    if (loading) return <div className="loading">加载中...</div>;

    return (
        <div>
            <h2 style={{ marginBottom: 20 }}>📋 订单列表</h2>
            {orders.length === 0 ? (
                <div className="empty">暂无订单数据</div>
            ) : (
                <table>
                    <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>客户</th>
                            <th>产品</th>
                            <th>数量</th>
                            <th>总金额</th>
                            <th>状态</th>
                            <th>下单时间</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders.map(o => (
                            <tr key={o.id}>
                                <td><strong>{o.orderNo}</strong></td>
                                <td>{o.customerName}<br/><small style={{color:'#999'}}>{o.phone}</small></td>
                                <td>{products[o.productId] || '未知产品'}</td>
                                <td>{o.quantity}</td>
                                <td><strong>¥{o.totalAmount}</strong></td>
                                <td>{statusLabel(o.status)}</td>
                                <td>{formatTime(o.createTime)}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default OrderList;