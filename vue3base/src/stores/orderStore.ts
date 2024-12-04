// src/stores/orderStore.ts

import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { fetchOrders, addOrder, deleteOrder, updateOrder,fetchOrdersByUserId } from '@/api/index';
import type { Order } from '@/types';

export const useOrderStore = defineStore('order', () => {
  const orders = ref<Order[]>([]);

  const fetchAllOrders = async () => {
    try {
      const response = await fetchOrders();
      orders.value = response.data;
    } catch (error) {
      console.error('Error fetching orders:', error);
    }
  };

  const fetchIdOrders = async (order: Order) => {
    try {
      const response = await fetchOrdersByUserId(order);
      const {list} :any = response.data;
      orders.value = list;
    } catch (error) {
      console.error('Error fetching orders:', error);
    }
  };

  const addNewOrder = async (order: Order) => {
    try {
      const response = await addOrder(order);
      orders.value.push(response.data);
    } catch (error) {
      console.error('Error adding order:', error);
    }
  };

  const removeOrder = async (id: number) => {
    try {
      await deleteOrder(id);
      orders.value = orders.value.filter(o => o.order_id !== id);
    } catch (error) {
      console.error('Error deleting order:', error);
    }
  };

  const updateExistingOrder = async (order: Order) => {
    try {
      const response = await updateOrder(order);
      const index = orders.value.findIndex(o => o.order_id === order.order_id);
      if (index !== -1) {
        orders.value[index] = response.data;
      }
    } catch (error) {
      console.error('Error updating order:', error);
    }
  };

  return {
    orders,
    fetchAllOrders,
    fetchIdOrders,
    addNewOrder,
    removeOrder,
    updateExistingOrder,
  };
});
