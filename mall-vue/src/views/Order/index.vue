<template>
  <Search title="我的订单" :is-show-li="true" isSHowSearch="true" />
  <div class="content">
    <el-tabs v-model="activeName" class="content-tabs" >

      <el-tab-pane label="所有订单" :name="1" >
        <OrderList :status="-1"
        />
      </el-tab-pane>
      <el-tab-pane label="待付款" :name="2" lazy
        ><OrderList :status="0"
      /></el-tab-pane>
      <el-tab-pane label="待发货" :name="3" lazy
        ><OrderList :status="1"
      /></el-tab-pane>
      <el-tab-pane label="待收货" :name="4" lazy
        ><OrderList :status="2"
      /></el-tab-pane>
      <el-tab-pane label="已完成" :name="5" lazy
        ><OrderList :status="3"
      /></el-tab-pane>


    </el-tabs>
  </div>
</template>

<script setup lang="ts">
// @ts-ignore
import OrderList from "./components/OrderList.vue";
import { ref } from "vue";

const activeName = ref<number>(1);

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
