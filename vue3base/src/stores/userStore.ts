// src/stores/userStore.ts

import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { fetchUsers, addUser, deleteUser, loginUser, updateUser } from '@/api/index';
import type { User } from '@/types';

export const useUserStore = defineStore('user', () => {
  const users = ref<User[]>([]);
  const currentUser = ref<User | null>(null);

  const fetchAllUsers = async () => {
    try {
      const response = await fetchUsers();
      users.value = response.data;
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

  const addNewUser = async (user: User) => {
    try {
      const response = await addUser(user);
      users.value.push(response.data);
      return true;
    } catch (error) {
      console.error('Error adding user:', error);
      return false;
    }
  };

  const removeUser = async (id: number) => {
    try {
      await deleteUser(id);
      users.value = users.value.filter(user => user.user_id !== id);
      return true;
    } catch (error) {
      console.error('Error deleting user:', error);
      return false;
    }
  };

  const login = async (userForm: User) => {
    try {
      const {data:{statusCode,code,list,message}}:any = await loginUser(userForm);
      currentUser.value = list;
      return true;
    } catch (error) {
      console.error('Error logging in:', error);
      return false;
    }
  };

  const update = async (user: User) => {
    try {
      const response = await updateUser(user);
      const index = users.value.findIndex(u => u.user_id === user.user_id);
      if (index !== -1) {
        users.value[index] = response.data;
      }
      return true;
    } catch (error) {
      console.error('Error updating user:', error);
      return false;
    }
  };

  const logout = () => {
    currentUser.value = null;
  };

  const isLogin = computed(() => !!currentUser.value);

  return {
    users,
    currentUser,
    isLogin,
    fetchAllUsers,
    addNewUser,
    removeUser,
    login,
    update,
    logout,
  };
});
