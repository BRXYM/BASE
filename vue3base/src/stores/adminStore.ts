// src/stores/adminStore.ts

import { defineStore } from 'pinia';
import { ref } from 'vue';
import { fetchAdmins, addAdmin, deleteAdmin, updateAdmin } from '@/api';
import type { Admin } from '@/types';

export const useAdminStore = defineStore('admin', () => {
  const admins = ref<Admin[]>([]);

  const getAdmins = async () => {
    const response = await fetchAdmins();
    admins.value = response.data;
  };

  const createAdmin = async (admin: Admin) => {
    await addAdmin(admin);
    await getAdmins();
  };

  const removeAdmin = async (id: number) => {
    await deleteAdmin(id);
    await getAdmins();
  };

  const editAdmin = async (admin: Admin) => {
    await updateAdmin(admin);
    await getAdmins();
  };

  return { admins, getAdmins, createAdmin, removeAdmin, editAdmin };
});
