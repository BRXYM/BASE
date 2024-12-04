// src/api/index.ts

import axios from 'axios';
import type { Admin, Ads, Good, Mess, Order, Sell, User } from '@/types';

const API_URL = 'http://localhost:3000';
// const API_URL = 'https://cn-bj-plc-2.ofalias.net:54284';

// Admin API
export const fetchAdmins = () => axios.get<Admin[]>(`${API_URL}/admin`);
export const addAdmin = (admin: Admin) => axios.post(`${API_URL}/admin/add`, admin);
export const deleteAdmin = (id: number) => axios.post(`${API_URL}/admin/delete`, { id });
export const updateAdmin = (admin: Admin) => axios.post(`${API_URL}/admin/update`, admin);

// Ads API
export const fetchAds = () => axios.get<Ads[]>(`${API_URL}/ads`);
export const addAds = (ads: Ads) => axios.post(`${API_URL}/ads/add`, ads);
export const deleteAds = (id: number) => axios.post(`${API_URL}/ads/delete`, { id });
export const updateAds = (ads: Ads) => axios.post(`${API_URL}/ads/update`, ads);

// Good API
export const fetchGoods = () => axios.get<Good[]>(`${API_URL}/good`);
export const addGood = (good: Good) => axios.post(`${API_URL}/good/add`, good);
export const deleteGood = (id: number) => axios.post(`${API_URL}/good/delete`, { id });
export const updateGood = (good: Good) => axios.post(`${API_URL}/good/update`, good);
export const fetchGoodsBySellId = (good : Good) => axios.post<Good>(`${API_URL}/good/getById`, good);

// Mess API
export const fetchMesses = () => axios.get<Mess[]>(`${API_URL}/mess`);
export const addMess = (mess: Mess) => axios.post(`${API_URL}/mess/add`, mess);
export const fetchMessesByOrderId = (orderId: number|undefined) => axios.post<Mess[]>(`${API_URL}/mess/byOrderId`, { 'order_id':orderId });
export const deleteMess = (id: number) => axios.post(`${API_URL}/mess/delete`, { id });
export const updateMess = (mess: Mess) => axios.post(`${API_URL}/mess/update`, mess);

// Order API
export const fetchOrders = () => axios.get<Order[]>(`${API_URL}/order`);
export const fetchOrdersByUserId = (order: Order) => axios.post<Order[]>(`${API_URL}/order/byUserId`, order);
export const addOrder = (order: Order) => axios.post(`${API_URL}/order/add`, order);
export const deleteOrder = (id: number) => axios.post(`${API_URL}/order/delete`, { id });
export const updateOrder = (order: Order) => axios.post(`${API_URL}/order/update`, order);

// Sell API
export const fetchSells = () => axios.get<Sell[]>(`${API_URL}/sell`);
export const addSell = (sell: Sell) => axios.post(`${API_URL}/sell/add`, sell);
export const deleteSell = (id: number) => axios.post(`${API_URL}/sell/delete`, { id });
export const updateSell = (sell: Sell) => axios.post(`${API_URL}/sell/update`, sell);

// User API
export const fetchUsers = () => axios.get<User[]>(`${API_URL}/user`);
export const addUser = (user: User) => axios.post(`${API_URL}/user/add`, user);
export const deleteUser = (id: number) => axios.post(`${API_URL}/user/delete`, { id });
export const loginUser = (userForm: User) => axios.post<User>(`${API_URL}/user/login`, userForm);
export const updateUser = (user: User) => axios.post(`${API_URL}/user/update`, user);
