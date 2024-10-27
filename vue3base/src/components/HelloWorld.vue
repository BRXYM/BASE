<script setup lang="ts">
import { useCounterStore } from '@/stores/counter';
import { storeToRefs } from 'pinia';
const counter = useCounterStore()
// 基本数据类型转换为响应式后解构
const {count,doubleCount} = storeToRefs(counter)
// 存在pinia中的方法直接结构
const { increment } = counter

</script>

<template>

    <h1>{{ count }}</h1>
    <h1>{{ doubleCount }}</h1>

    <button @click="increment()">测试</button>

</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

.greetings h1,
.greetings h3 {
  text-align: center;
}

@media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
}
</style>
