<template>
  <div class="content">
    <!-- 分页器 -->
    <div id="pageDiv">
      <ul>
        <li data-name="firstPage" class="disabled">
          <a href="javascript:void(0)" onclick="getPage(0)" aria-label="首页"
            ><span aria-hidden="true">&laquo;</span></a
          >
        </li>
        <li data-name="prevPage" class="disabled">
          <a href="javascript:void(0)" onclick="getPage(-1)" aria-label="上一页"
            ><span aria-hidden="true">&lsaquo;</span></a
          >
        </li>

        <li class="pageThis">
          <a href="javascript:void(0)" onclick="getPage(0)">1</a>
        </li>

        <li data-name="nextPage" class="disabled">
          <a href="javascript:void(0)" onclick="getPage(1)" aria-label="下一页"
            ><span aria-hidden="true">&rsaquo;</span></a
          >
        </li>
        <li data-name="lastPage" class="disabled">
          <a href="javascript:void(0)" onclick="getPage(0)" aria-label="尾页"
            >&raquo;</a
          >
        </li>
      </ul>
    </div>

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
      <tbody v-for="order in orderList" :key="order.orderCode">
        <tr class="tr_order_info">
          <td colspan="6">
            <span class="span_pay_date">{{ order.payDate }}</span
            ><span class="span_order_code_title">订单号:</span
            ><span class="span_order_code">{{ order.orderCode }}</span>
          </td>
        </tr>

        <!-- 订单项信息 -->
        <tr
          class="tr_orderItem_info"
          v-for="item in order.orderItemList"
          :key="item.productId"
        >
          <td>
            <img
              class="orderItem_product_image"
              :src="getImageUrl(item.productImageSrc)"
              style="width: 80px; height: 80px"
            /><span class="orderItem_product_name"
              ><a href="/mall/productInfo/">{{ item.productName }}</a></span
            >
          </td>
          <!-- 售价 -->
          <td>
            <span class="orderItem_product_price"
              >￥{{ item.productSalePrice }}</span
            >
          </td>
          <!-- 销售数量 -->
          <td>
            <span class="orderItem_product_number">{{
              item.productNumber
            }}</span>
          </td>
          <!-- 总的价钱 -->
          <td class="td_order_content">
            <span class="orderItem_product_realPrice"
              >￥{{ item.productSalePrice * item.productNumber }}</span
            >
          </td>
          <!--交易状态 -->
          <td class="td_order_content" rowspan="0">
            <span class="span_order_status" title="卖家已发货，等待买家确认"
              >等待买家确认</span
            >
          </td>
          <!-- 确认收货 -->
          <td class="td_order_content" rowspan="0">
            <a
              class="order_btn confirm_btn"
              href="/mall/order/confirm/2018052615500401"
              >确认收货</a
            >
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
const imageBeseUrl = ref<string>("../../../assets/img/");
const getImageUrl = (img: string) => {
  return "../../../assets/img/" + img;
};
// 订单项
interface orderItemInfoType {
  productImageSrc: string;
  productId: number;
  productName: string;
  productSalePrice: number;
  productNumber: number;
  productRealPrice: number;
}

// 订单
interface orderInfoType {
  orderCode: string;
  payDate: string;
  orderItemList: orderItemInfoType[];
}
// 订单列表
const orderList = ref<orderInfoType[]>([
  {
    orderCode: "2018052615500401",
    payDate: "Wed Jun 06 15:50:10 CST 2018",
    orderItemList: [
      {
        productImageSrc: "70fc3539-4b80-49b0-a4d9-85835ad4c044.jpg",
        productId: 0,
        productName:
          "白色T恤女短袖蕾丝上衣棉立方2018夏新款女装修身韩版圆领雪纺衫",
        productSalePrice: 59.0,
        productNumber: 4,
        productRealPrice: 0,
      },
    ],
  },
]);
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

#pageDiv > ul {
  margin-left: -20px;
  display: inline-block;
  height: 32px;
  margin-bottom: 0;
  padding: 0;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

#pageDiv li {
  width: 35px;
  height: 35px;
  float: left;
  list-style: none;
  border: 1px solid #ddd;
  margin: 0 1px;
}

#pageDiv li:hover {
  border: 1px solid #ff0036;
  text-decoration: none;
}

#pageDiv li > a {
  text-align: center;
  display: inline-block;
  text-decoration: none;
  width: 100%;
  height: 100%;
  line-height: 35px;
  color: #666;
  cursor: pointer;
}

#pageDiv li > a:hover {
  color: #ff0036;
}

#pageDiv li.disabled > a {
  color: #dddddd;
  cursor: default;
}

#pageDiv li.pageThis {
  border: 1px solid #ff0036;
  background-color: #ff0036;
}

#pageDiv .pageThis > a {
  color: #fff;
}

#pageDiv .pageThis > a:hover {
  color: #fff;
}
</style>

<!-- 列表的样式 -->
<style scoped>
.content {
  width: 1230px;
  margin: auto;
  min-height: 400px;
  padding-bottom: 60px;
  color: #666;
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
