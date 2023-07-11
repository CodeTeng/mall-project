<template>
  <Search title="我的订单" :is-show-li="true" isSHowSearch="true" />
  <div class="content">
    <el-tabs v-model="activeName" class="content-tabs" @tab-click="handleClick">
      <el-tab-pane label="所有订单" name="first" lazy
        ><OrderList
      /></el-tab-pane>
      <el-tab-pane label="待付款" name="second" lazy><OrderList /></el-tab-pane>
      <el-tab-pane label="待发货" name="third" lazy><OrderList /></el-tab-pane>
      <el-tab-pane label="待收货" name="fourth" lazy><OrderList /></el-tab-pane>
      <el-tab-pane label="已收货" name="five" lazy><OrderList /></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import OrderList from "./components/OrderList.vue";
import { ref } from "vue";

const activeName = ref("first");

const handleClick = (tab: any, event: Event) => {
  console.log(tab, event);
};
</script>

<style lang="scss" scoped>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #e13c3c;
  font-size: 32px;
  font-weight: 600;
}
.content {
  width: 1300px;
  margin-left: auto;
  margin-right: auto;
}
</style>
