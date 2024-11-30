// src/stores/adsStore.ts

import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { fetchAds, addAds, deleteAds, updateAds } from '@/api/index';
import type { Ads } from '@/types';

export const useAdsStore = defineStore('ads', () => {
  const ads = ref<Ads[]>([]);

  const fetchAllAds = async () => {
    try {
      const response = await fetchAds();
      ads.value = response.data;
    } catch (error) {
      console.error('Error fetching ads:', error);
    }
  };

  const addNewAd = async (ad: Ads) => {
    try {
      const response = await addAds(ad);
      ads.value.push(response.data);
    } catch (error) {
      console.error('Error adding ad:', error);
    }
  };

  const removeAd = async (id: number) => {
    try {
      await deleteAds(id);
      ads.value = ads.value.filter(ad => ad.ads_id !== id);
    } catch (error) {
      console.error('Error deleting ad:', error);
    }
  };

  const updateExistingAd = async (ad: Ads) => {
    try {
      const response = await updateAds(ad);
      const index = ads.value.findIndex(a => a.ads_id === ad.ads_id);
      if (index !== -1) {
        ads.value[index] = response.data;
      }
    } catch (error) {
      console.error('Error updating ad:', error);
    }
  };

  return {
    ads,
    fetchAllAds,
    addNewAd,
    removeAd,
    updateExistingAd,
  };
});
