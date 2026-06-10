import axios from 'axios';

/**
 * API 配置 - 后端接口基础地址
 */
const api = axios.create({
  //  baseURL: 'http://localhost:8080/api',
  // baseURL: 'http://13.211.170.139:8080/api',
   baseURL: '/api',
    timeout: 5000,
    headers: { 'Content-Type': 'application/json' }
});

export default api;