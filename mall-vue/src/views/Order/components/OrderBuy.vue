<template>
  <div>
    <Search title="" :isShowLi="false" :isSHowSearch="true"/>
    <div class="content">
      <!-- 订单的地址信息表单 -->
      <div class="order_address">
        <el-card shadow="hover">
          <template #header>
            <div>
              <span>收货人地址信息填写</span>
            </div>
          </template>
          <!-- card body -->
          <el-form
              ref="ruleFormRef"
              :model="addressForm"
              :rules="addressFormRules"
              label-width="120px"
              class="address_form"
              status-icon
          >
            <!-- 地址选取 -->
            <el-form-item
                class="form-item"
                label="居住地址:"
                prop="regionAddress"
            >
              <el-select
                  v-model="addressForm.provinceAddress"
                  filterable
                  placeholder="省"
                  class="el-select-item"
                  @change="getCityAddressOptions(addressForm.provinceAddress)"
              >
                <el-option
                    v-for="item in provinceAddressOptions"
                    :key="item.addressAreaId"
                    :label="item.addressName"
                    :value="item.addressAreaId"
                />
              </el-select>
              <!-- 市 -->
              <el-select
                  v-model="addressForm.cityAddress"
                  filterable
                  placeholder="市"
                  class="el-select-item"
                  @change="getRegionAddressOptions(addressForm.cityAddress)"
              >
                <el-option
                    v-for="item in cityAddressOptions"
                    :key="item.addressAreaId"
                    :label="item.addressName"
                    :value="item.addressAreaId"
                />
              </el-select>

              <!-- 区 -->
              <el-select
                  v-model="addressForm.regionAddress"
                  filterable
                  placeholder="区"
                  class="el-select-item"
              >
                <el-option
                    v-for="item in regionAddressOptions"
                    :key="item.addressAreaId"
                    :label="item.addressName"
                    :value="item.addressAreaId"
                />
              </el-select>
            </el-form-item>

            <!-- 详细地址 -->
            <el-form-item
                class="form-item"
                label="详细地址:"
                prop="productOrderDetailAddress"
            >
              <el-input type="textarea" v-model="addressForm.productOrderDetailAddress"/>
            </el-form-item>
            <!-- 邮政编码 -->
            <el-form-item class="form-item" label="邮政编码:" prop="productOrderPost">
              <el-input type="text" v-model="addressForm.productOrderPost"/>
            </el-form-item>
            <!-- 收货人姓名 -->
            <el-form-item
                class="form-item"
                label="收货人姓名:"
                prop="productOrderReceiver"
            >
              <el-input type="text" v-model="addressForm.productOrderReceiver"/>
            </el-form-item>
            <!-- 手机号码 -->
            <el-form-item class="form-item" label="手机号码:" prop="productOrderMobile">
              <el-input type="text" v-model="addressForm.productOrderMobile"/>
            </el-form-item>
          </el-form>
        </el-card>
      </div>

      <!-- 订单信息 -->
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

          <div v-for="item in productItemInfoList" :key="item.productOrderItemId" class="tab-tr-div">
            <!--   店铺信息   -->
            <tr class="tr_shop">
              <td>
                <span class="span_shopTitle">店铺：</span
                ><span class="span_shopName">贤趣{{ item.categoryName }}旗舰店</span>
              </td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <!--  商品信息          -->
            <tr class="tr_product_info">
              <td>
                <img
                    :src="bindImg(item.productImageSrc)"
                    style="width: 50px; height: 50px"
                /><span class="span_product_name"
              ><router-link :to="'/product/info/'+item.productId"
              >{{ item.productName }}</router-link
              ></span
              >
              </td>
              <td><span class="span_product_sale_price">{{ item.productSalePrice }}</span></td>
              <td><span class="span_productOrderItem_number">{{ item.productOrderItemNumber }}</span></td>
              <td><span
                  class="span_productOrderItem_price">{{
                  (item.productSalePrice * item.productOrderItemNumber).toFixed(2)
                }}</span>
              </td>
            </tr>
            <!--  卖家留言   -->
            <tr class="tr_userMessage">
              <td>
                <label for="input_userMessage_1">给卖家留言：</label
                ><textarea
                  maxlength="500"
                  id="input_userMessage_1"
                  placeholder="选填:填写内容已和卖家协商确认"
                  class="input_userMessage"
                  v-model="item.productOrderItemUserMessage"
              ></textarea>
              </td>
              <td></td>
              <td></td>
            </tr>
            <!--  价格        -->
            <tr class="tr_orderCount">
              <td colspan="3"></td>
              <td>
                <span class="span_price_name">店铺合计(含运费)</span
                ><span
                  class="span_price_value">￥{{
                  (item.productSalePrice * item.productOrderItemNumber).toFixed(2)
                }}</span>
              </td>
            </tr>
          </div>

          </tbody>
        </table>
      </div>

      <!-- 订单提交信息 -->
      <div class="order_count_div">
        <div class="order_count_div_main">
          <div class="order_count_div_content">
            <h1 class="order_count_div_price">
              <span class="order-title">实付款：</span
              ><span class="realPay-price_unit">￥</span
            ><span class="realPay-price">{{ realPayPrice }}</span>
            </h1>
            <h1 class="order_count_div_address">
              <span class="order-title">寄送至：</span
              ><span class="order-value address_province"></span
            ><span class="order-value address_city"></span
            ><span class="order-value address_district"></span
            ><span class="order-value address_details">{{ addressForm.productOrderDetailAddress }}</span>
            </h1>
            <h1 class="order_count_div_phone">
              <span class="order-title">收货人：</span
              ><span class="order-value user-name">{{ addressForm.productOrderReceiver }}</span
            ><span class="order-value user-phone">{{ addressForm.productOrderMobile }}</span>
            </h1>
          </div>
        </div>
      </div>

      <div class="order_info_last">
        <a
            href="javascript:void(0)"
            title="提交订单"
            class="go-btn"
            @click="submitOrder(ruleFormRef)"
        >提交订单</a
        >
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useRoute,useRouter} from "vue-router";
import {getAddressApi, getProvinceApi} from "../../../api/address";
import {bindImg} from "../../../utils";
import {ProductCartInfoVO} from "../../../api/product/type";
import {getCartListByItemIdApi} from "../../../api/cart";
import {addOrderItemVO, OrderAddRequest} from "../../../api/order/type";
import {addOrderApi} from "../../../api/order";
import {AddressDTO} from "../../../api/user/types";

const route = useRoute();
const router = useRouter();


interface productItemInfoVO extends ProductCartInfoVO {
  productOrderItemUserMessage?: string;
}

// 获得商品ID字符串列表
const productItemIds = ref<any>(route.query?.list);
const productItemInfoList = ref<productItemInfoVO[]>([])

// 获得商品订单信息列表
const doGetProductItemInfoList = () => {
  getCartListByItemIdApi(productItemIds.value).then(res => {
    if (res.code === 0) {
      productItemInfoList.value = res.data;
      let price: number = 0;
      productItemInfoList.value.forEach((value) => {
        price = price + value.productOrderItemPrice;
      })
      realPayPrice.value = price.toFixed(2);
    } else {
      ElMessage.error("获取订单信息失败")
    }
  })
}

// 总需要的付款数
const realPayPrice = ref<string>("")

// 添加订单信息
const OrderAddInfo = ref<OrderAddRequest>({
  addOrderItemDTOList: [],
  productOrderAddress: "",
  productOrderDetailAddress: "",
  productOrderMobile: "",
  productOrderPost: "",
  productOrderReceiver: ""
})

// 省 市 区
const provinceAddressOptions = ref<AddressType[]>();
const cityAddressOptions = ref<AddressDTO[]>();
const regionAddressOptions = ref<AddressDTO[]>();

const getProvinceAddressOptions = () => {
  getProvinceApi().then((res) => {
    if (res.code === 0) {
      provinceAddressOptions.value = res.data;
    } else {
      ElMessage.error("获取省份失败");
    }
  });
};

const getRegionAddressOptions = (address: string) => {
  getAddressApi(address).then((res) => {
    console.log(res);
    if (res.code === 0) {
      regionAddressOptions.value = res.data;
    } else {
      ElMessage.error("获取地区失败");
    }
  });
};

const getCityAddressOptions = (address: string) => {
  getAddressApi(address).then((res) => {
    console.log(res);
    if (res.code === 0) {
      cityAddressOptions.value = res.data;
    } else {
      ElMessage.error("获取市区失败");
    }
  });
};

// 订单地址信息
interface OrderAddressForm {
  // 地址
  provinceAddress: string;
  cityAddress: string;
  regionAddress: string;

  // 订单详细地址
  productOrderDetailAddress: string;
  // 邮政地址
  productOrderPost: string;
  // 收货人
  productOrderReceiver: string;
  // 电话号码
  productOrderMobile: string;
}

const ruleFormRef = ref<any>();

// 地址表单
const addressForm = ref<OrderAddressForm>({
  // 地址
  provinceAddress: "",
  cityAddress: "",
  regionAddress: "",
  // 订单详细地址
  productOrderDetailAddress: "",
  // 邮政地址
  productOrderPost: "",
  // 收货人
  productOrderReceiver: "",
  // 电话号码
  productOrderMobile: "",
});

// 表单校验
const addressFormRules = ref<any>({
  regionAddress: [{required: true, message: "输入不能为空", trigger: "blur"}],
  productOrderDetailAddress: [{required: true, message: "输入不能为空", trigger: "blur"}],
  productOrderReceiver: [{required: true, message: "输入不能为空", trigger: "blur"}],
  productOrderMobile: [{required: true, message: "输入不能为空", trigger: "blur"}],
});

// 提交订单
const submitOrder = async (formEl: any) => {
  if (!formEl) return;
  await formEl.validate((valid: any, fields: any) => {
    if (valid) {
      const orderItem: any = {
        productOrderItemId: 0,
        productOrderItemUserMessage: ""
      }
      productItemInfoList.value.forEach((value) => {
          orderItem.productOrderItemId = value.productOrderItemId;
          orderItem.productOrderItemUserMessage = value.productOrderItemUserMessage==undefined? "":value.productOrderItemUserMessage;
          OrderAddInfo.value.addOrderItemDTOList.push(orderItem);
      })
      OrderAddInfo.value.productOrderAddress = addressForm.value.regionAddress;
      OrderAddInfo.value.productOrderDetailAddress = addressForm.value.productOrderDetailAddress;
      OrderAddInfo.value.productOrderMobile = addressForm.value.productOrderMobile;
      OrderAddInfo.value.productOrderReceiver = addressForm.value.productOrderReceiver;

      console.log(OrderAddInfo.value)
      addOrderApi(OrderAddInfo.value).then(res=>{
        if(res.code===0){
          ElMessage.success("提交订单成功")
          router.push('/order/pay/'+res.data)
        }else{
          ElMessage.error("提交订单失败")
        }
      })
      console.log("submit!");
    } else {
      ElMessage.error("请填写完整信息")
    }
  });
};

onMounted(() => {
  getProvinceAddressOptions();
  doGetProductItemInfoList();
});


</script>

<style scoped>
.tab-tr-div {
  display: contents;
}

.el-select-item {
  margin-right: 10px;
}

.content {
  width: 1230px;
  margin: auto;
  min-height: 400px;
  padding-bottom: 60px;
  color: #666;
}

.content > .order_address {
  margin: 30px 0;
}

.order_address > h2, .order_info > h2 {
  font-family: Arial, serif;
  margin-bottom: 15px;
  line-height: 25px;
  color: #333;
  font-weight: 700;
  font-size: 14px;
}

.order_address > label {
  min-width: 70px;
  color: #333333;
  font: 400 12px/1.6 arial, sans-serif;
}

.order_address > span.mustValue {
  display: inline-block;
  color: #FF0036;
  padding: 0 2px;
  box-sizing: border-box;
  width: 10px;
}

.order_address > .br {
  height: 10px;
}

.order_address > textarea {
  font-size: 12px;
  vertical-align: top;
  width: 392px;
  height: 62px;
  border: 1px solid #ccc;
  color: #333;
  border-radius: 3px;
  padding: 5px;
}

.order_address > input[type=text] {
  font-size: 12px;
  color: #333;
  border-radius: 3px;
  height: 25px;
  border: 1px solid #cccccc;
  padding: 0 5px;
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

.tr_shop > td > span.span_shopName {
  color: #3c3c3c;
}

.table_order_orderItem > tbody > tr.tr_product_info {
  border-bottom: 1px dotted #ddd;
  background-color: #FBFCFF;
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
  color: #666;
}

.tr_product_info > td > span.span_product_name > a:hover {
  text-decoration: none;
}

.table_order_orderItem > tbody > tr.tr_userMessage {
  border-bottom: 1px solid #FFFFFF;
  background-color: #f2f7ff;
}

.tr_userMessage > td {
  padding: 10px;
}

.tr_userMessage > td > label {
  position: relative;
  top: 3px;
  font-weight: normal;
  vertical-align: top;
}

.tr_userMessage > td > textarea {
  box-sizing: content-box;
  vertical-align: top;
  width: 328px;
  resize: none;
  height: 18px;
  line-height: 18px;
  text-indent: 4px;
  border: 1px solid #cccccc;
  outline: none;
  overflow: auto;
  transition: height 0.7s;
}

.tr_userMessage > td > textarea:focus {
  transition: height 0.7s;
  border: 1px solid #FF0036;
  height: 55px;
}

.table_order_orderItem > tbody > tr.tr_orderCount {
  border-bottom: 1px dotted #80b2ff;
  background-color: #f2f7ff;
}

.tr_orderCount > td {
  height: 45px;
  padding: 3px 0;
  text-align: right;
}

.tr_orderCount > td > span.span_price_name {
  color: #333333;
}

.tr_orderCount > td > span.span_price_value {
  color: #FF0036;
  margin: 0 10px;
  font-size: 16px;
  font-weight: bolder;
  vertical-align: middle;
  font-family: Verdana, Arial, serif;
}

.content > .order_count_div {
  text-align: right;
  margin-top: 15px;
}

.order_count_div > .order_count_div_main {
  display: inline-block;
  border: 1px solid #FF0036;
}

.order_count_div_main > .order_count_div_content {
  border: 3px solid #FFF0E8;
  min-width: 230px;
  padding: 10px 10px 20px 20px;
}

.order_count_div_content > h1 {
  color: #666;
  text-align: right;
  display: block;
}

.order_count_div_content > h1 > span.order-title {
  font: 12px/1.5 tahoma, arial, "\5b8b\4f53", serif;
  font-weight: 700;
  color: #333333;
}

.order_count_div_content > h1 > span.order-value {
  margin-left: 5px;
  font: 12px/1.5 Arial;
}

.order_count_div_content > h1 > span.realPay-price_unit {
  font-size: 26px;
  margin-right: 4px;
  color: #999;
  font-family: Verdana, Arial, serif;
}

.order_count_div_content > h1 > span.realPay-price {
  color: #FF0036;
  font: 700 26px Tahoma;
}

.order_count_div_content > .order_count_div_phone {
  font-size: 14px;
}

.content > .order_info_last {
  text-align: right;
}

.order_info_last > .go-btn {
  font-family: Arial, serif;
  display: inline-block;
  width: 182px;
  height: 39px;
  position: relative;
  vertical-align: middle;
  line-height: 39px;
  cursor: pointer;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
  background: #FF0036;
  color: #FFFFFF;
}

.order_info_last > .go-btn:hover {
  text-decoration: none;
}

/*修改bootstrap-select下拉框样式*/
.bootstrap-select:not([class*=col-]):not([class*=form-control]):not(.input-group-btn) {
  width: 150px;
  margin: 0 20px 0 0;
  position: relative;
  bottom: 1px;
}

.btn.dropdown-toggle.btn-default {
  font-size: 12px;
  color: #333;
  border-color: #ccc;
  border-radius: 3px;
}

.btn-group-vertical > .btn, .btn-group > .btn {
  height: 25px;
  padding: 5px 12px 5px 5px;
}

.bootstrap-select.btn-group .dropdown-toggle .filter-option {
  position: relative;
  bottom: 1px;
}

.bootstrap-select.btn-group .dropdown-toggle .caret {
  color: #cccccc;
}

.dropdown-menu > li > a {
  font-size: 14px;
}

.bs-searchbox > .form-control {
  height: 20px;
  width: 140px;
}

/*加载动画样式*/
.loader {
  display: none;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  z-index: 999;
  font-size: 5px;
  width: 1em;
  height: 1em;
  border-radius: 50%;
  text-indent: -9999em;
  animation: load-effect 1s infinite linear;
}

@keyframes load-effect {
  0% {
    box-shadow: 0 -3em 0 .2em #ff7874, /*上*/ 2em -2em 0 0 #ff7874, /*右上*/ 3em 0 0 -.5em #ff7874, /*右*/ 2em 2em 0 -.5em #ff7874, /*右下*/ 0 3em 0 -.5em #ff7874, /*下*/ -2em 2em 0 -.5em #ff7874, /*左下*/ -3em 0 0 -.5em #ff7874, /*左*/ -2em -2em 0 0 #ff7874; /*左上*/;
  }
  12.5% {
    box-shadow: 0 -3em 0 0 #ff7874,
    2em -2em 0 .2em #ff7874,
    3em 0 0 0 #ff7874,
    2em 2em 0 -.5em #ff7874,
    0 3em 0 -.5em #ff7874,
    -2em 2em 0 -.5em #ff7874,
    -3em 0 0 -.5em #ff7874,
    -2em -2em 0 -.5em #ff7874;
  }
  25% {
    box-shadow: 0 -3em 0 -.5em #ff7874,
    2em -2em 0 0 #ff7874,
    3em 0 0 .2em #ff7874,
    2em 2em 0 0 #ff7874,
    0 3em 0 -.5em #ff7874,
    -2em 2em 0 -.5em #ff7874,
    -3em 0 0 -.5em #ff7874,
    -2em -2em 0 -.5em #ff7874;
  }
  37.5% {
    box-shadow: 0 -3em 0 -.5em #ff7874,
    2em -2em 0 -.5em #ff7874,
    3em 0 0 0 #ff7874,
    2em 2em 0 .2em #ff7874,
    0 3em 0 0 #ff7874,
    -2em 2em 0 -.5em #ff7874,
    -3em 0 0 -.5em #ff7874,
    -2em -2em 0 -.5em #ff7874;
  }
  50% {
    box-shadow: 0 -3em 0 -.5em #ff7874,
    2em -2em 0 -.5em #ff7874,
    3em 0 0 -.5em #ff7874,
    2em 2em 0 0 #ff7874,
    0 3em 0 .2em #ff7874,
    -2em 2em 0 0 #ff7874,
    -3em 0 0 -.5em #ff7874,
    -2em -2em 0 -.5em #ff7874;
  }
  62.5% {
    box-shadow: 0 -3em 0 -.5em #ff7874,
    2em -2em 0 -.5em #ff7874,
    3em 0 0 -.5em #ff7874,
    2em 2em 0 -.5em #ff7874,
    0 3em 0 0 #ff7874,
    -2em 2em 0 .2em #ff7874,
    -3em 0 0 0 #ff7874,
    -2em -2em 0 -.5em #ff7874;
  }
  75% {
    box-shadow: 0 -3em 0 -.5em #ff7874,
    2em -2em 0 -.5em #ff7874,
    3em 0 0 -.5em #ff7874,
    2em 2em 0 -.5em #ff7874,
    0 3em 0 -.5em #ff7874,
    -2em 2em 0 0 #ff7874,
    -3em 0 0 .2em #ff7874,
    -2em -2em 0 0 #ff7874;
  }
  87.5% {
    box-shadow: 0 -3em 0 0 #ff7874,
    2em -2em 0 -.5em #ff7874,
    3em 0 0 -.5em #ff7874,
    2em 2em 0 -.5em #ff7874,
    0 3em 0 -.5em #ff7874,
    -2em 2em 0 0 #ff7874,
    -3em 0 0 0 #ff7874,
    -2em -2em 0 .2em #ff7874;
  }
  100% {
    box-shadow: 0 -3em 0 .2em #ff7874,
    2em -2em 0 0 #ff7874,
    3em 0 0 -.5em #ff7874,
    2em 2em 0 -.5em #ff7874,
    0 3em 0 -.5em #ff7874,
    -2em 2em 0 -.5em #ff7874,
    -3em 0 0 -.5em #ff7874,
    -2em -2em 0 0 #ff7874;
  }
}
</style>
