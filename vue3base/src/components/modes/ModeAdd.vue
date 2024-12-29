<template>
  <div>
    <h1>添加模组</h1>
    <form @submit.prevent="addMode">
      <label for="name">模组名称:</label>
      <input type="text" id="name" v-model="mode.name" required />
      <br />
      <label for="file">上传文件:</label>
      <input type="file" id="file" @change="handleFileUpload" required />
      <br />
      <button type="submit">添加模组</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      mode: {
        name: '',
        file: null,
      },
      message: '',
    };
  },
  methods: {
    handleFileUpload(event) {
      this.mode.file = event.target.files[0];
    },
    async addMode() {
      const formData = new FormData();
      formData.append('name', this.mode.name);
      formData.append('file', this.mode.file);

      try {
        const response = await axios.post('http://localhost:3000/mode/addMode', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        this.message = response.data.msg;
      } catch (error) {
        this.message = error.response.data.msg;
      }
    },
  },
};
</script>

<style scoped>
/* 添加一些样式 */
</style>
