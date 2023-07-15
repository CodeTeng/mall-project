<template>
  <div>
    <Search title="" :isShowLi="false" :isSHowSearch="true" />
    <div class="content">
      <div class="order_div">
        <img
          src="../../../assets/images/fore/WebsiteImage/payCode.png"
          :width="100"
          :height="100"
        />

        <div class="order_name">
          <span>订单号：{{orderConfirmList[0]?.productOrderCode}}</span>
        </div>

        <div class="order_price">
          <span class="price_value">{{totalMoney}}</span>
          <span class="price_unit">元</span>
        </div>
      </div>

      <div class="order_pay_div">
        <input class="order_pay_btn" type="submit"
          value="点击跳转支付" @click="btnSubmit"/>
      </div>

      <div v-html="PageUrl" name="pay-main">
      </div>
    </div>
  </div>

</template>

<script setup lang="ts">
import { useRoute,useRouter } from "vue-router";
import {alipayApi} from "../../../api/pay";
import {PayType} from "../../../api/pay/type";
import {OrderConfirmVO} from "../../../api/cart/type";
import {getCartListByOrderIdApi} from "../../../api/cart";
import {ElMessage} from "element-plus";
const route = useRoute();
const router = useRouter();



const productOrderId = ref<any>(route.params.id);
const PageUrl = ref<any>()
// 订单信息
const orderConfirmList = ref<OrderConfirmVO[]>([])
const totalMoney = ref<string>("")
const proNumber = ref<number>(0)

const doGetOrderConfirmList = async ()=>{
  getCartListByOrderIdApi(productOrderId.value).then(async (res)=>{
    if(res.code===0){
      orderConfirmList.value = res.data;
      let money:number  = 0
      let num:number = 0;
      orderConfirmList.value.forEach((value)=>{
        money = value.productOrderItemPrice +money;
        num++;
      })
      proNumber.value = num;
      totalMoney.value = money.toFixed(2)
      doAlipayApi()
    }else{
      ElMessage.error(res.message)
    }
  })
}

const PayInfo = ref<PayType>({
  money: "",
  productOrderId: "",
  subject: ""
})

const doAlipayApi = ()=>{
  PayInfo.value.money = totalMoney.value;
  PayInfo.value.productOrderId = productOrderId.value
  PayInfo.value.subject = "MAll商城订单:"+proNumber.value+"笔"

  alipayApi(PayInfo.value).then(res=>{
    PageUrl.value = res
    console.log(res)
  })
}

// 订单提交
const btnSubmit  = ()=>{
  document.getElementsByName("punchout_form").item(0).submit()
  nextTick()
}

onMounted( ()=>{
  doGetOrderConfirmList()
})



</script >


<style lang="scss" scoped>
.pay-main{
  width: 100%;
  height: 500px;
}

.content {
  width: 950px;
  margin: 30px auto auto;
  min-height: 400px;
  padding-bottom: 60px;
  color: #666;
  position: relative;
}

.content > .order_div {
  position: relative;
}

.content > .order_div > img {
  position: absolute;
  left: 0;
  vertical-align: top;
}

.content > .order_div > .order_name,
.content > .order_div > .order_shop_name {
  width: 600px;
  display: inline-block;
  color: #333;
  font-size: 12px;
  margin-left: 116px;
  line-height: 25px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.content > .order_div > .order_name {
  margin-top: 25px;
}

.content > .order_div > .order_price {
  position: absolute;
  right: 27px;
  top: 30px;
  float: right;
}

.order_price > .price_value {
  color: #ff8208;
  font-weight: 700;
  font-size: 22px;
}

.content > .order_pay_div {
  overflow: hidden;
  margin-top: 50px;
  text-align: center;
}

.order_pay_div .order_pay_btn {
  display: inline-block;
  width: 110px;
  height: 32px;
  color: #fff;
  border: 1px solid #0ae;
  background-color: #0ae;
  vertical-align: middle;
  cursor: pointer;
  font-size: 14px;
  font-weight: 700;
  border-radius: 2px;
  padding: 0 20px;
  margin: 0 auto;
  line-height: 32px;
}

.order_pay_btn:hover {
  background-color: #00a3d2;
  transition: background-color 0.5s;
  text-decoration: none;
}
</style>
