<template>
  <div class="content">
    <!-- 分页器 -->
    <div id="pageDiv">
      <el-pagination
        background
        :page-current="orderPage.current"
        :page-size="orderPage.pageSize"
        :total="orderTotal"
        @current-change="handleCurrentChange"
        layout="prev, pager, next"
      />
    </div>
    <!--  订单列表  -->
    <table class="table_orderList">
      <!-- 表头 -->
      <thead>
        <tr>
          <th>宝贝</th>
          <th width="80px">单价</th>
          <th width="80px">数量</th>
          <th width="140px">实付款</th>
          <th width="140px">交易状态</th>
          <th width="140px">交易操作</th>
        </tr>
      </thead>
      <!-- 订单头信息 -->
      <tbody v-for="order in orderList" :key="order.productOrderCode">
        <tr class="tr_order_info">
          <td colspan="6">
            <span class="span_pay_date">{{ order.productOrderPayDate }}</span
            ><span class="span_order_code_title">订单号:</span
            ><span class="span_order_code">{{ order.productOrderCode }}</span>
          </td>
        </tr>

        <!-- 订单项信息 -->
        <tr
          class="tr_orderItem_info"
          v-for="(item, index) in order.productOrderItemList"
          :key="item.productOrderItemId"
        >
          <td>
            <img
              class="orderItem_product_image"
              :src="bindImg(item.productOrderItemProductVO.productImageSrc)"
              style="width: 80px; height: 80px"
            /><span class="orderItem_product_name"
              ><router-link
                :to="
                  '/product/Info/' + item.productOrderItemProductVO.productId
                "
                >{{ item.productOrderItemProductVO.productName }}</router-link
              ></span
            >
          </td>
          <!-- 售价 -->
          <td>
            <span class="orderItem_product_price"
              >￥{{ item.productOrderItemProductVO.productSalePrice }}</span
            >
          </td>
          <!-- 销售数量 -->
          <td>
            <span class="orderItem_product_number">{{
              item.productOrderItemNumber
            }}</span>
          </td>
          <!-- 总的价钱 -->
          <td class="td_order_content">
            <span class="orderItem_product_realPrice"
              >￥{{ item.productOrderItemPrice }}</span
            >
          </td>

          <!--交易状态 -->
          <td class="td_order_content" rowspan="0" v-show="index === 0">
            <span
              class="span_order_status"
              :title="orderStatusText[order.productOrderStatus].title"
              >{{ orderStatusText[order.productOrderStatus].title }}</span
            >
          </td>

          <!-- 立即付款 -->
          <td
            class="td_order_content"
            rowspan="0"
            v-show="order.productOrderStatus === 0 && index === 0"
          >
            <router-link
              class="order_btn pay_btn"
              :to="'/order/pay/' + order.productOrderId"
              target="_blank"
              >立即付款</router-link
            >
            <p class="order_close">
              <a
                class="order_close"
                href="javascript:void(0)"
                target="_blank"
                @click="cancelOrder(order.productOrderId)"
                >取消订单</a
              >
            </p>
          </td>

          <!-- 提醒发货 -->
          <td
            class="td_order_content"
            :rowspan="0"
            v-show="order.productOrderStatus === 1 && index === 0"
          >
            <a
              class="order_btn delivery_btn"
              @click="
                deliveryOrder(order.productOrderId, order.productOrderCode)
              "
              >提醒发货</a
            >
          </td>

          <!-- 确认收货 -->
          <td
            class="td_order_content"
            :rowspan="0"
            v-show="order.productOrderStatus === 2 && index === 0"
          >
            <router-link
              class="order_btn confirm_btn"
              :to="'/order/confirm/' + order.productOrderId"
              >确认收货</router-link
            >
          </td>

          <!-- 商品评价 -->
          <td
            class="td_order_content"
            v-show="order.productOrderStatus === 3"
          >
            <router-link
              class="order_btn confirm_btn"
              :to="
                '/order/review/' +
                item.productOrderItemId +
                '/' +
                item.productOrderItemProductVO.productId
              "
              >评价商品</router-link
            >
          </td>


          <!-- 订单已取消 -->
          <td
              class="td_order_content"
              rowspan="0"
              v-show="order.productOrderStatus === 4 && index === 0"
          >
            <p class="order_close">
              <a
                  class="order_close"
                  href="javascript:void(0)"
              >订单已取消</a
              >
            </p>
          </td>


        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
// 订单列表
import {
  OrderPageRequest,
  ProductOrderVO,
  updateOrderRequest,
} from "../../../api/order/type";
import { getMyAllOrderApi, updateOrderStatusApi } from "../../../api/order";
import { bindImg } from "../../../utils";
import { ElMessageBox, ElMessage } from "element-plus";

interface orderStatusVO {
  title: string;
  value: string;
}

// 路由到购买
const routerToBuy = (orderId:number):string=>{
  const ids:number[] = []
  for(let i=0;i<orderList.value.length;i++){
    if(orderList.value[i].productOrderId===orderId){
      orderList.value[i].productOrderItemList.forEach((value)=>{
        ids.push(value.productOrderItemId);
      })
    }
  }
  return ids.toString()
}

const orderStatusText = ref<orderStatusVO[]>([
  { title: "等待买家付款", value: "等待买家付款" },
  { title: "买家已付款，等待卖家发货", value: "等待卖家发货" },
  { title: "卖家已发货，等待买家确认", value: "等待买家确认" },
  { title: "交易成功", value: "交易成功" },
  { title: "订单已取消", value: "订单已取消" },
]);

// 订单列表
const orderList = ref<ProductOrderVO[]>([]);
const orderPage = ref<OrderPageRequest>({
  current: 1,
  pageSize: 10,
});

// 订单数量
const orderTotal = ref<number>(0);

const props = defineProps({
  status: Number,
});

// 获得订单列表
const doGetOrderList = () => {
  orderPage.value.status = props.status;
  getMyAllOrderApi(orderPage.value).then((res) => {
    if (res.code === 0) {
      orderTotal.value = res.data.total;
      orderList.value = res.data.list;
      nextTick()
    }
  });
};

// 分页请求
const handleCurrentChange = (val: number) => {
  orderPage.value.current = val;
  doGetOrderList();
};

// 取消订单
const cancelOrder = (productOrderId: number) => {
  ElMessageBox.confirm("确认取消订单！", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    setOrderStatus(productOrderId, 4, "取消订单");
  });
};

// 改变订单状态
const setOrderStatus = (
  productOrderId: number,
  status: number,
  msg: string
) => {
  const data: updateOrderRequest = {
    productOrderId: productOrderId,
    productOrderStatus: status,
  };
  updateOrderStatusApi(data).then((res) => {
    if (res.code === 0) {
      doGetOrderList();
      ElMessage.success(msg + "成功");
    } else {
      ElMessage.success(msg + "失败：" + res.message);
    }
  });
};

// 提醒商家发货
const deliveryOrder = (productOrderId: number, productOrderCode: string) => {
  ElMessage.success("提醒商家发货成功！请耐心等待");
  setTimeout(() => {
    setOrderStatus(productOrderId, 2, "订单" + productOrderCode + "发货");
  }, 3000);
};

onMounted( () => {
  // 获得数据列表
   doGetOrderList();
});
</script>

<!-- 分页器的样式 -->
<style lang="scss" scoped>
a:focus {
  text-decoration: none;
}

/*分页工具样式*/
#pageDiv {
  width: 100%;
  margin: 20px 0;
  text-align: right;
}
</style>

<!-- 列表的样式 -->
<style scoped>
.header {
  width: 1230px;
  margin: 0 auto;
  height: 96px;
}

.header > #mallLogo {
  float: left;
  padding-top: 28px;
  width: 280px;
  height: 64px;
  line-height: 64px;
  position: relative;
}

#mallLogo > a {
  position: relative;
  display: block;
  width: 190px;
  height: 30px;
  overflow: hidden;
}

#mallLogo img {
  width: 190px;
  height: 28px;
  cursor: pointer;
  vertical-align: top;
}

.header > .shopSearchHeader {
  float: right;
  overflow: hidden;
  width: 597px;
  padding-top: 25px;
}

.shopSearchHeader > form {
  border: solid #ff0036;
  border-width: 3px 0 3px 3px;
}

.shopSearchHeader > form > .shopSearchInput {
  position: relative;
  height: 30px;
}

input::-webkit-input-placeholder {
  /* WebKit browsers*/
  color: #ccc;
  font-weight: normal;
}

input:-moz-placeholder {
  /* Mozilla Firefox 4 to 18*/
  color: #ccc;
  font-weight: normal;
}

input::-moz-placeholder {
  /* Mozilla Firefox 19+*/
  color: #ccc;
  font-weight: normal;
}

input:-ms-input-placeholder {
  /* Internet Explorer 10+*/
  color: #ccc;
  font-weight: normal;
}

.shopSearchInput > .searchInput {
  font-size: 12px;
  color: #000;
  width: 496px;
  height: 20px;
  line-height: 20px;
  padding: 5px 3px 5px 5px;
  border: none;
  font-weight: 900;
  outline: none;
  float: left;
}

.shopSearchInput > .searchBtn {
  width: 90px;
  height: 30px;
  font-size: 16px;
  cursor: pointer;
  color: #ffffff;
  background-color: #ff0036;
  overflow: hidden;
  border: 0;
  font-family: Arial, serif;
  float: left;
}

.shopSearchHeader > ul {
  padding-top: 4px;
  margin-left: -10px;
  height: 16px;
  overflow: hidden;
  line-height: 16px;
  margin-bottom: 15px;
}

.shopSearchHeader li + li {
  border-left: 1px solid #cccccc;
}

.shopSearchHeader li {
  float: left;
  line-height: 1.1;
  padding: 0 12px;
}

.shopSearchHeader li > a {
  color: #999;
  font-size: 12px;
}

.content {
  width: 1230px;
  margin: auto;
  min-height: 400px;
  padding-bottom: 60px;
  color: #666;
}

.content > .tabs_ul {
  margin: 22px 0;
  font-size: 0;
  border-bottom: 2px solid #e8e8e8;
}

.tabs_ul > li {
  position: relative;
  top: 2px;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
  font-weight: 700;
  font-family: "Microsoft YaHei UI", serif;
}

.tabs_ul > li > a {
  display: inline-block;
  padding: 0 20px 12px;
  color: #333333;
}

.tabs_ul > li > a:hover {
  text-decoration: none;
  color: #ff0036;
}

.tabs_ul > li.tab_select {
  border-bottom: 2px solid #ff0036;
}

.tabs_ul > li.tab_select > a {
  color: #ff0036;
}

.content > .table_orderList {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  color: #3c3c3c;
  table-layout: fixed;
  font: 12px/1.5 "Microsoft YaHei UI", Arial, "Hiragino Sans GB";
}

.table_orderList > thead {
  background-color: #f5f5f5;
  border: 1px solid #e8e8e8;
}

.table_orderList > thead th {
  text-align: center;
  padding: 10px 0;
  font-weight: 400;
}

.table_orderList > tbody td {
  text-align: center;
}

.table_orderList > tbody tr.tr_order_info {
  background: #f1f1f1;
  border: 1px solid #ececec;
}

.table_orderList > tbody tr.tr_order_info > td {
  text-align: left;
  padding: 11px 0;
}

tr.tr_order_info > td > span.span_pay_date {
  display: inline-block;
  margin-left: 20px;
  margin-right: 10px;
  font-weight: 700;
}

.table_orderList > tbody tr.tr_orderItem_info {
  border: 1px solid #ececec;
}

.table_orderList > tbody tr.tr_orderItem_info > td {
  padding: 18px 0;
}

.table_orderList > tbody tr.tr_orderItem_info > td + td {
  border-left: 1px solid #ececec;
}

.table_orderList > tbody tr.tr_orderItem_info > td:first-child {
  text-align: left;
}

.table_orderList > tbody tr.tr_orderItem_info > td.td_order_content {
  vertical-align: top;
}

tr.tr_orderItem_info > td > img.orderItem_product_image {
  margin: 0 20px;
}

tr.tr_orderItem_info > td > span.orderItem_product_name > a {
  color: #3c3c3c;
}

tr.tr_orderItem_info > td > span.orderItem_product_name > a:hover {
  text-decoration: none;
  color: #ff0036;
}

tr.tr_orderItem_info > td > span.orderItem_product_price {
  font-family: verdana, serif;
}

tr.tr_orderItem_info > td > span.orderItem_product_number {
  font-family: verdana, serif;
}

tr.tr_orderItem_info > td.td_order_content > span.orderItem_product_realPrice {
  font-family: verdana, serif;
  font-weight: bolder;
}

tr.tr_orderItem_info > td.td_order_content > span.span_order_status {
  color: #333;
}

tr.tr_orderItem_info > td.td_order_content > a.order_btn {
  display: inline-block;
  height: 28px;
  line-height: 28px;
  padding: 0 12px;
  border-radius: 3px;
  cursor: pointer;
  background-color: #ff0036;
  color: #fff;
  border-color: #ff0036;
  font-weight: bolder;
}

tr.tr_orderItem_info > td.td_order_content > a.order_btn:hover {
  text-decoration: none;
}

tr.tr_orderItem_info > td.td_order_content > p.order_close {
  margin-top: 8px;
}

tr.tr_orderItem_info > td.td_order_content > p.order_close > a {
  color: #3c3c3c;
  cursor: pointer;
}

tr.tr_orderItem_info > td.td_order_content > p.order_close > a:hover {
  text-decoration: none;
  color: #ff0036;
}

.no_search_result {
  padding: 40px 0;
  text-align: center;
}

.no_search_result img {
  margin-right: 24px;
  vertical-align: middle;
}

.no_search_result span {
  font-family: "Microsoft YaHei UI", serif;
  font-size: 12px;
}
</style>
