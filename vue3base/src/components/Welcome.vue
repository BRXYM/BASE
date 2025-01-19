<template>
  <el-calendar ref="calendar" @click="handleDateClick">
    <template #header="{ date }">
      <span>欢迎来到青训</span>
      <span>{{ date }}</span>
      <el-button-group>
        <el-button size="small" @click="selectDate('prev-year')">上一年</el-button>
        <el-button size="small" @click="selectDate('prev-month')">上一月</el-button>
        <el-button size="small" @click="selectDate('today')">今天</el-button>
        <el-button size="small" @click="selectDate('next-month')">下一月</el-button>
        <el-button size="small" @click="selectDate('next-year')">下一年</el-button>
      </el-button-group>
    </template>
    <template #dateCell="{ data }">
      <div>
        <p>{{ data.day.split('-').slice(2).join('-') }}</p>
        <p v-if="filteredClassesByDate(data.day).length">
          {{ filteredClassesByDate(data.day).map(cla => cla.name).join(', ') }}
        </p>
      </div>
    </template>
  </el-calendar>
  <Class></Class>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue'
import { useClaStore } from '@/stores/store'
import type { CalendarDateType, CalendarInstance } from 'element-plus'
import Class from "@/components/classes/Class.vue";

const calendar = ref<CalendarInstance>()
const claStore = useClaStore()

// 获取所有课程信息
claStore.fetchClasses()

const selectDate = (val: CalendarDateType) => {
  if (!calendar.value) return
  calendar.value.selectDate(val)
}

const handleDateClick = (date: string) => {
  console.log('Clicked date:', date)
  // 可以在这里添加更多逻辑，比如显示对话框等
}

const filteredClassesByDate = computed(() => (date: string) => {
  return claStore.classes?.filter(cla => cla.date === date) || []
})
</script>

<style scoped>
/* 添加一些样式以美化日历 */
</style>
