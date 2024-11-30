// src/stores/sellStore.ts

import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { fetchSells, addSell, deleteSell, updateSell } from '@/api/index';
import type { Sell } from '@/types';

export const useSellStore = defineStore('sell', () => {
  const sells = ref<Sell[]>([]);

  const fetchAllSells = async () => {
    try {
      const response = await fetchSells();
      sells.value = response.data;
    } catch (error) {
      console.error('Error fetching sells:', error);
    }
  };

  const addNewSell = async (sell: Sell) => {
    try {
      const response = await addSell(sell);
      sells.value.push(response.data);
    } catch (error) {
      console.error('Error adding sell:', error);
    }
  };

  const removeSell = async (id: number) => {
    try {
      await deleteSell(id);
      sells.value = sells.value.filter(s => s.sell_id !== id);
    } catch (error) {
      console.error('Error deleting sell:', error);
    }
  };

  const updateExistingSell = async (sell: Sell) => {
    try {
      const response = await updateSell(sell);
      const index = sells.value.findIndex(s => s.sell_id === sell.sell_id);
      if (index !== -1) {
        sells.value[index] = response.data;
      }
    } catch (error) {
      console.error('Error updating sell:', error);
    }
  };

  return {
    sells,
    fetchAllSells,
    addNewSell,
    removeSell,
    updateExistingSell,
  };
});
