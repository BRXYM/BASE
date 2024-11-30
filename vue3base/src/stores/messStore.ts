// src/stores/messStore.ts

import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { fetchMesses, addMess, fetchMessesByOrderId, deleteMess, updateMess } from '@/api/index';
import type { Mess } from '@/types';

export const useMessStore = defineStore('mess', () => {
  const messes = ref<Mess[]>([]);

  const fetchAllMesses = async () => {
    try {
      const response = await fetchMesses();
      messes.value = response.data;
    } catch (error) {
      console.error('Error fetching messes:', error);
    }
  };

  const fetchMessesByOrder = async (orderId: number) => {
    try {
      const response = await fetchMessesByOrderId(orderId);
      messes.value = response.data;
    } catch (error) {
      console.error('Error fetching messes by order:', error);
    }
  };

  const addNewMess = async (mess: Mess) => {
    try {
      const response = await addMess(mess);
      messes.value.push(response.data);
    } catch (error) {
      console.error('Error adding mess:', error);
    }
  };

  const removeMess = async (id: number) => {
    try {
      await deleteMess(id);
      messes.value = messes.value.filter(m => m.mess_id !== id);
    } catch (error) {
      console.error('Error deleting mess:', error);
    }
  };

  const updateExistingMess = async (mess: Mess) => {
    try {
      const response = await updateMess(mess);
      const index = messes.value.findIndex(m => m.mess_id === mess.mess_id);
      if (index !== -1) {
        messes.value[index] = response.data;
      }
    } catch (error) {
      console.error('Error updating mess:', error);
    }
  };

  return {
    messes,
    fetchAllMesses,
    fetchMessesByOrder,
    addNewMess,
    removeMess,
    updateExistingMess,
  };
});
