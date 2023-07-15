<template>
  <el-steps :active="activeNumber" align-center finish-status="success">
    <el-step title="付款到支付宝" :description="OrderTimeInfo.productOrderPayDate" />
    <el-step title="卖家发货" :description="OrderTimeInfo.productOrderDeliveryDate"/>
    <el-step title="确认收货" :description="OrderTimeInfo.productOrderConfirmDate" />
  </el-steps>
</template>

<script setup lang="ts">

import {getOrderTimeApi} from "../../../api/order";
import {defineProps} from "vue";

const activeNumber = ref<number>(1)
const props =  defineProps({
  productOrderId:{
    type:Number,
    default:0
  }
})

type OrderTimeVO = {
  productOrderConfirmDate: string;
  productOrderDeliveryDate: string;
  productOrderPayDate: string;
}
const OrderTimeInfo = ref<OrderTimeVO>({
  productOrderConfirmDate: "",
  productOrderDeliveryDate: "",
  productOrderPayDate: ""
})

const doGetOrderTime = ()=>{
  getOrderTimeApi(props.productOrderId).then(res=>{
    if(res.code===0){
      OrderTimeInfo.value = res.data;
      if(OrderTimeInfo.value.productOrderPayDate==null){
        activeNumber.value = 0
      }
      if(OrderTimeInfo.value.productOrderDeliveryDate==null){
        activeNumber.value = 1
      }
      if(OrderTimeInfo.value.productOrderConfirmDate==null){
        activeNumber.value = 2
      }
      console.log( OrderTimeInfo.value)
    }else{
      console.log("获取订单时间列表失败")
    }
  })
}

onMounted(()=>{
  doGetOrderTime()
})


</script>

<style scoped>

</style>