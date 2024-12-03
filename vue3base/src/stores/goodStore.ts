// src/stores/goodStore.ts

import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { fetchGoods, addGood, deleteGood, updateGood } from '@/api/index';
import type { Good } from '@/types';

export const useGoodStore = defineStore('good', () => {
  const goods = ref<Good[]>([]);

  const fetchAllGoods = async () => {
    try {
      const response = await fetchGoods();
      const {list} :any = response.data;
      goods.value = list;
    } catch (error) {
      console.error('Error fetching goods:', error);
    }
  };

  const addNewGood = async (good: Good) => {
    try {
      const response = await addGood(good);
      goods.value.push(response.data);
    } catch (error) {
      console.error('Error adding good:', error);
    }
  };

  const removeGood = async (id: number) => {
    try {
      await deleteGood(id);
      goods.value = goods.value.filter(g => g.good_id !== id);
    } catch (error) {
      console.error('Error deleting good:', error);
    }
  };

  const updateExistingGood = async (good: Good) => {
    try {
      const response = await updateGood(good);
      const index = goods.value.findIndex(g => g.good_id === good.good_id);
      if (index !== -1) {
        goods.value[index] = response.data;
      }
    } catch (error) {
      console.error('Error updating good:', error);
    }
  };

  return {
    goods,
    fetchAllGoods,
    addNewGood,
    removeGood,
    updateExistingGood,
  };
});
