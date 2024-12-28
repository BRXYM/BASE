import axios from 'axios';
import { ElMessage } from 'element-plus'; // 引入ElMessage

const baseURL = '/api'
const instance = axios.create({baseURL})

export default instance;

// 修改: 在请求方法中添加错误处理逻辑
instance.interceptors.response.use(
  response => response,
  error => {
    ElMessage.error('网络错误'); // 弹出错误提示
    return Promise.reject(error);
  }
);

