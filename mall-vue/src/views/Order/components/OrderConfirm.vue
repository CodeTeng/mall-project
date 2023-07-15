<template>
  <div>
    <Search title="" :isShowLi="false" :isSHowSearch="true"/>
    <OrderTimeHeader :productOrderId="productOrderId" />
    <div class="content">
      <h1>我已收到货，同意支付宝付款</h1>
      <div class="order_info">
        <h2>确认订单信息</h2>
        <table class="table_order_orderItem">
          <thead>
          <tr>
            <th>店铺宝贝</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
          </tr>
          </thead>
          <tbody>
          <tr class="tr_product_info" v-for="item in orderConfirmList" :key="item.productOrderItemId">
            <td width="500px">
              <img
                  :src="bindImg(item.productImageSrc)"
                  style="width: 50px; height: 50px"
              /><span class="span_product_name"
            ><router-link :to="'/product/'+item.productId"
            >{{item.productName}}</router-link
            ></span
            >
            </td>
            <td><span class="span_product_sale_price">{{item.productSalePrice}}</span></td>
            <td><span class="span_productOrderItem_number">{{item.productOrderItemNumber}}</span></td>
            <td>
                <span
                    class="span_productOrderItem_price"
                    style="font-weight: bold"
                >{{item.productOrderItemPrice}}</span
                >
            </td>
          </tr>


          <!--   实付款   -->
          <tr class="order-ft">
            <td colspan="4">
              <div class="total-price">
                实付款：￥<strong>{{totalMoney}}</strong>
              </div>
            </td>
          </tr>

          </tbody>

          <!--    订单信息    -->
          <tbody class="misc-info">
          <tr class="set-row">
            <td colspan="4"></td>
          </tr>
          <tr>
            <td colspan="4">
              <span class="info_label">订单编号：</span>
              <span class="info_value">{{orderConfirmList[0]?.productOrderCode}}</span>
            </td>
          </tr>
          <tr>
            <td colspan="4">
              <span class="info_label">卖家商铺昵称：</span>
              <span class="info_value">贤趣{{orderConfirmList[0]?.categoryName}}旗舰店</span>
            </td>
          </tr>
          <tr>
            <td colspan="4">
              <span class="info_label">成交时间：</span>
              <span class="info_value">{{orderConfirmList[0]?.productOrderPayDate}}</span>
            </td>
          </tr>
          </tbody>

        </table>

        <div class="order-dashboard">
          <div class="bd">
            <ul>
              <li>请收到货后，再确认收货！否则您可能钱货两空！</li>
              <li class="message">提示：本系统不会进行真实交易，请放心测试</li>
            </ul>
            <a href="javascript:void(0)" @click="OnOrderConfirm()">确定</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import {updateOrderStatusApi} from "../../../api/order";
import {ElMessage} from "element-plus";
import {OrderConfirmVO} from "../../../api/cart/type";
import {getCartListByOrderIdApi} from "../../../api/cart";
import {bindImg} from "../../../utils";
import OrderTimeHeader from './OrderTimeHeader.vue'

const route = useRoute();
const router = useRouter()
const productOrderId = ref<any>(route.params.id);
const totalMoney = ref<string>("0")

// 确认收货信息
const orderConfirmList = ref<OrderConfirmVO[]>([])

const doGetOrderConfirmList = ()=>{
  getCartListByOrderIdApi(productOrderId.value).then(res=>{
    if(res.code===0){
      orderConfirmList.value = res.data;
      let money:number  = 0
      orderConfirmList.value.forEach((value)=>{
        money = value.productOrderItemPrice +money;
      })
      totalMoney.value = money.toFixed(2)
      console.log(orderConfirmList.value)
    }else{
      ElMessage.error(res.message)
    }
  })
}

// 确认订单
const OnOrderConfirm = () => {
  const data = {
    productOrderId: productOrderId.value,
    productOrderStatus: 3
  };
  updateOrderStatusApi(data).then((res) => {
    if (res.code === 0) {
      ElMessage.success("确认收货成功");
      router.push("/order")
    } else {
      ElMessage.error("确认收货失败：" + res.message);
    }
  });
};

onMounted(()=>{
  doGetOrderConfirmList();
})
</script>

<style lang="scss" scoped>
.content {
  width: 1230px;
  margin: 100px auto 60px auto;
  min-height: 400px;
  color: #666;
}

.content > h1 {
  border-bottom: 1px solid #adc8e6;
  font-size: 16px;
  color: #111111;
  padding-bottom: 10px;
  margin-bottom: 10px;
  font-family: Arial, serif;
  font-weight: bold;
}

.content > .order_info {
  padding: 10px 20px;
}

.order_info > h2 {
  font-family: Arial, serif;
  line-height: 25px;
  color: #333;
  font-weight: 700;
  font-size: 14px;
}

.order_info > .table_order_orderItem {
  width: 100%;
}

.table_order_orderItem > thead th {
  text-align: center;
  font-family: Arial, serif;
  font-weight: normal;
  font-size: 12px;
  border-bottom: 3px solid #b2d1ff;
}

.table_order_orderItem > tbody td {
  font-family: Arial, serif;
  font-weight: normal;
  font-size: 12px;
}

.table_order_orderItem > tbody > tr.tr_shop {
  border-bottom: 1px dotted #80b2ff;
}

.tr_shop > td {
  padding-top: 25px;
  height: 22px;
}

.table_order_orderItem > tbody > tr.tr_product_info {
  border-bottom: 1px dotted #ddd;
}

.table_order_orderItem > tbody > tr.order-ft {
  text-align: right;
}

.order-ft > td {
  padding: 8px 10px;
  line-height: 130%;
  text-align: center;
  overflow: hidden;
}

.order-ft > td > div {
  text-align: right;
  float: right;
  margin-top: 20px;
  color: black;
}

.order-ft > td > div > strong {
  font-size: 18px;
  font-weight: 400;
  color: #c00;
}

.tr_product_info > td {
  text-align: center;
  padding: 10px 0;
}

.tr_product_info > td:first-child {
  text-align: left;
}

.tr_product_info > td > img {
  padding-left: 10px;
}

.tr_product_info > td > span.span_product_name > a {
  display: inline-block;
  padding-left: 10px;
}

.misc-info td {
  padding: 5px 10px;
  line-height: 25px;
  color: black;
}

.set-row > td {
  border-bottom: 1px solid #ddd;
}

.misc-info td > .info_label {
  display: block;
  float: left;
  width: 90px;
  text-align: right;
  margin-right: 10px;
}

.order_info > .order-dashboard {
  margin-top: 10px;
  border: 1px solid #e5e5e5;
}

.order-dashboard > .bd {
  padding: 26px 100px;
  border: 1px solid #f58b0f;
}

.bd > ul > li {
  list-style: disc;
  color: #f00000;
  font-weight: 700;
  font-size: 14px;
  line-height: 28px;
  font-family: Arial, serif;
}

.bd > ul > li.message {
  color: black;
  font-weight: normal;
}

.bd > a {
  box-sizing: border-box;
  height: 31px;
  display: inline-block;
  vertical-align: middle;
  text-align: left;
  font-size: 14px;
  text-decoration: none;
  background: url(../../../assets/images/fore/WebsiteImage/1eOlqqn1qX.png) 0 -163px no-repeat;
  padding: 0 16px;
  border-radius: 3px;
  line-height: 31px;
  color: white;
  font-family: tohoma, serif;
  font-weight: bold;
  margin-top: 20px;
}
</style>
