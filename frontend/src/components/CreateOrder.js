import React, { useState, useEffect } from 'react';
import api from '../api';

/**
 * 下单组件 - 选择产品并创建订单
 */
function CreateOrder() {
    const [products, setProducts] = useState([]);
    const [form, setForm] = useState({
        productId: '',
        quantity: 1,
        customerName: '',
        phone: '',
        address: ''
    });
    const [message, setMessage] = useState(null);   // {type: 'success'|'error', text: ''}
    const [submitting, setSubmitting] = useState(false);

    // 加载产品列表（用于下拉选择）
    useEffect(() => {
        api.get('/products').then(res => setProducts(res.data)).catch(console.error);
    }, []);

    // 表单输入变化处理
    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    // 提交订单
    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage(null);

        // 基本校验
        if (!form.productId) return setMessage({ type: 'error', text: '请选择产品' });
        if (!form.customerName.trim()) return setMessage({ type: 'error', text: '请输入客户姓名' });
        if (!form.phone.trim()) return setMessage({ type: 'error', text: '请输入联系电话' });

        setSubmitting(true);
        try {
            const res = await api.post('/orders', form);
            setMessage({ type: 'success', text: `下单成功！订单编号：${res.data.orderNo}` });
            // 重置表单
            setForm({ productId: '', quantity: 1, customerName: '', phone: '', address: '' });
        } catch (err) {
            const errorMsg = err.response?.data?.error || '下单失败，请重试';
            setMessage({ type: 'error', text: errorMsg });
        } finally {
            setSubmitting(false);
        }
    };

    // 计算选中产品的价格（用于显示）
    const selectedProduct = products.find(p => p.id === Number(form.productId));
    const totalPrice = selectedProduct ? selectedProduct.price * form.quantity : 0;

    return (
        <div>
            <h2 style={{ marginBottom: 20 }}>📝 创建订单</h2>

            {/* 消息提示 */}
            {message && (
                <div className={`message ${message.type}`}>{message.text}</div>
            )}

            <form onSubmit={handleSubmit} style={{ maxWidth: 500 }}>
                {/* 选择产品 */}
                <div className="form-group">
                    <label>选择产品</label>
                    <select name="productId" value={form.productId} onChange={handleChange}>
                        <option value="">-- 请选择 --</option>
                        {products.map(p => (
                            <option key={p.id} value={p.id}>
                                {p.name} - ¥{p.price}（库存：{p.stock}）
                            </option>
                        ))}
                    </select>
                </div>

                {/* 购买数量 */}
                <div className="form-group">
                    <label>购买数量</label>
                    <input type="number" name="quantity" min="1"
                           value={form.quantity} onChange={handleChange} />
                </div>

                {/* 显示总价 */}
                {selectedProduct && (
                    <div style={{ marginBottom: 16, padding: '10px 14px', background: '#f0f8ff',
                                  borderRadius: 8, color: '#4a90d9', fontWeight: 500 }}>
                        单价：¥{selectedProduct.price} × {form.quantity} = <strong>¥{totalPrice}</strong>
                    </div>
                )}

                {/* 客户姓名 */}
                <div className="form-group">
                    <label>客户姓名</label>
                    <input type="text" name="customerName" placeholder="请输入姓名"
                           value={form.customerName} onChange={handleChange} />
                </div>

                {/* 联系电话 */}
                <div className="form-group">
                    <label>联系电话</label>
                    <input type="text" name="phone" placeholder="请输入手机号"
                           value={form.phone} onChange={handleChange} />
                </div>

                {/* 收货地址 */}
                <div className="form-group">
                    <label>收货地址（选填）</label>
                    <input type="text" name="address" placeholder="请输入地址"
                           value={form.address} onChange={handleChange} />
                </div>

                {/* 提交按钮 */}
                <button type="submit" className="btn btn-primary" disabled={submitting}>
                    {submitting ? '提交中...' : '确认下单'}
                </button>
            </form>
        </div>
    );
}

export default CreateOrder;