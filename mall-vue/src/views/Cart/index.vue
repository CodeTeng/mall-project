<template>
  <div>
    <!-- 购物车搜索栏 -->
    <Search title="购物车" :isShowLi="false" :isShowSearch="true"/>

    <div class="content">

      <div id="J_FilterBar">
        <ul id="J_CartSwitch">
          <li>
            <router-link to="/cart" class="J_MakePoint">
              <em>全部商品</em>
              <span class="number">{{cartProductInfoList.length}}</span>
            </router-link>
          </li>
        </ul>
        <div class="cart-sum">
          <span class="pay-text">已选商品（不含运费）</span>
          <strong class="price"><em id="J_SmallTotal"><span
              class="total-symbol">&nbsp;</span>{{ (cartTotalMoney).toFixed(2) }}</em></strong>
          <a id="J_SmallSubmit" :class="['submit-btn',cartTotalMoney===0?'submit-btn-disabled':'']">结&nbsp;算</a>
        </div>
        <div class="wrap-line">
          <div class="floater"></div>
        </div>
      </div>
      <table id="J_CartMain">
        <thead>
        <tr>
          <th class="selectAll_th">
            <el-checkbox v-model="isAllSelect" :label="isAllSelect?'取消':'全选'"
                         @click="isAllSelect?doSelect(-1):doSelect(1)"/>
          </th>
          <th width="474px" class="productInfo_th"><span>商品信息</span></th>
          <th width="120px"><span>单价</span></th>
          <th width="120px"><span>数量</span></th>
          <th width="120px"><span>金额</span></th>
          <th width="84px"><span>操作</span></th>
          <th hidden>ID</th>
        </tr>
        </thead>
        <tbody>
        <div class="orderItem-div" v-for="(cart,index) in cartProductInfoList" :key="cart.productId">
          <tr class="orderItem_category">
            <td colspan="6"><span></span><span
                class="category_shop">店铺：贤趣{{ cart.categoryName }}旗舰店</span>
            </td>
          </tr>
          <tr class="orderItem_info">
            <td class="tbody_checkbox">
              <el-checkbox v-model="cartSelectList[index]" @change="doSelect(0)"/>
            </td>

            <td><img class="orderItem_product_image"
                     :src="bindImg(cart.productImageSrc)"
                     style="width: 80px;height: 80px;"/><span class="orderItem_product_name"><router-link
                :to="'/product/info/'+cart.productId">{{ cart.productName }}</router-link></span>
            </td>
            <td><span
                class="orderItem_product_price">￥{{ cart.productSalePrice}}</span>
            </td>
            <td>
              <div class="item_amount">
                <a href="javascript:void(0)" @click="down(index)"
                   class="J_Minus">-</a>
                <input type="text" v-model="cart.productOrderItemNumber"/>
                <a href="javascript:void(0)" @click="up(index)" class="J_Plus">+</a>
              </div>
            </td>
            <td>
              <span
                  class="orderItem_product_realPrice">￥{{ (cart.productOrderItemPrice).toFixed(2) }}</span>
            </td>
            <td><a href="javascript:void(0)" @click="doDeleteCartItem(cart.productOrderItemId)"
                   class="remove_order">删除</a></td>
          </tr>
        </div>


        </tbody>

      </table>
      <div id="J_FloatBar">
        <div class="operations">
          <a href="javascript:void(0)" @click="doDeleteCartItem(undefined)">删除已选商品</a>
        </div>
        <div class="float-bar-right">
          <div id="J_ShowSelectedItems">
            <span class="txt">已选商品</span>
            <em id="J_SelectedItemsCount">{{ selectNumber }}</em>
            <span class="txt">件</span>
          </div>
          <div class="price_sum">
            <span class="txt">合计（不含运费）:</span>
            <strong class="price">
              <em id="J_Total">
                <span class="total_symbol">{{ (cartTotalMoney).toFixed(2) }}￥</span>
                <span class="total_value"> </span>
              </em>
            </strong>
          </div>
          <div class="btn_area">
            <a href="javascript:void(0)" id="J_Go" :class="cartTotalMoney===0?'':'selected'" @click="doBuyProduct()">
              <span>结&nbsp;算</span>
            </a>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import {deleteCartItemApi, getMyCartApi, updateCartItemApi} from "../../api/cart";
import {ProductCartInfoVO} from "../../api/product/type";
import {bindImg} from "../../utils";
import {ElMessageBox} from "element-plus";
import { useRouter } from 'vue-router'
const router = useRouter()

// 购物车信息列表
const cartProductInfoList = ref<ProductCartInfoVO[]>([])



// 结算购物车
const doBuyProduct = ()=>{
  if(cartTotalMoney.value<=0) return;
  const productOrderItemIdList:number[] = [];

  for (let i = 0; i < cartSelectList.value.length; i++) {
    if (cartSelectList.value[i] === true) {
      productOrderItemIdList.push(cartProductInfoList.value[i].productOrderItemId);
    }
  }
  router.push({
    path: '/order/buy',
    query: {list:productOrderItemIdList.toString()}
  })


}


// 获得购物车
const doGetCartItemList = () => {
  getMyCartApi().then(res => {
    if (res.code === 0) {
      cartProductInfoList.value = res.data;
      cartSelectList.value = []
      for (let i = 0; i < cartProductInfoList.value.length; i++) {
        cartSelectList.value.push(false);
      }
      console.log(cartProductInfoList.value)
    }
  })
}

// 购物车选择列表
const cartSelectList = ref<boolean[]>([])
const isAllSelect = ref<boolean>(false)
const selectNumber = ref<number>(0)

// 选择购物车商品的总钱数
const cartTotalMoney = ref<number>(0)

// 选择 type=> 1-all全选  -1-取消全选  0-正常的选取
const doSelect = (type: number) => {
  if (type === 0) {

  } else if (type === 1) {
    for (let i = 0; i < cartSelectList.value.length; i++) {
      cartSelectList.value[i] = true;
    }
  } else if (type === -1) {
    for (let i = 0; i < cartSelectList.value.length; i++) {
      cartSelectList.value[i] = false;
    }
  }
  refreshData()

}

// 刷新选择的数量和总金额
const refreshData = ()=>{
  let money = 0;
  let number = 0;
  for (let i = 0; i < cartSelectList.value.length; i++) {
    if (cartSelectList.value[i] === true) {
      number++;
      money = money + cartProductInfoList.value[i].productOrderItemPrice
    }
  }
  selectNumber.value = number;
  cartTotalMoney.value = money;
}

// 删除购物车
const doDeleteCartItem = (productOrderItemId: number) => {
  ElMessageBox.confirm(
      '确定要执行删除操作吗?',
      '删除提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        const productOrderItemIdList: number[] = [];
        if (productOrderItemId != undefined) {
          productOrderItemIdList.push(productOrderItemId)
        } else {
          for (let i = 0; i < cartSelectList.value.length; i++) {
            if (cartSelectList.value[i] === true) {
              productOrderItemIdList.push(cartProductInfoList.value[i].productOrderItemId)
            }
          }
        }
        console.log(productOrderItemIdList.toString())
        deleteCartItemApi(productOrderItemIdList.toString()).then(res => {
          if (res.code === 0) {
            ElMessage.success("删除成功")
            doGetCartItemList()
            refreshData()
          } else {
            ElMessage.error("删除失败")
          }
        })
      })

}

// 更新购物车
const doUpdateCart = async (orderItemId: number, productOrderItemNumber: number) => {
  await updateCartItemApi(orderItemId, productOrderItemNumber).then(res => {
    if (res.code !== 0) {
      ElMessage.error("更新数据失败")
    }
  })
}

// 添加商品数量
const up = (index: number) => {
  if (cartProductInfoList.value && index < cartProductInfoList.value.length) {
    cartProductInfoList.value[index].productOrderItemNumber++;
    doUpdateCart(cartProductInfoList.value[index].productOrderItemId, cartProductInfoList.value[index].productOrderItemNumber);
  }
  refreshData()
}
// 减少商品数量
const down = (index: number) => {
  if (cartProductInfoList.value
      && index < cartProductInfoList.value.length
      && cartProductInfoList.value[index].productOrderItemNumber > 1) {
    cartProductInfoList.value[index].productOrderItemNumber--;
    doUpdateCart(cartProductInfoList.value[index].productOrderItemId, cartProductInfoList.value[index].productOrderItemNumber);
  }
  refreshData()
}


// 页面加载初始化
onMounted(() => {
  doGetCartItemList()
})

</script>

<style scoped>
.orderItem-div {
  display: contents;
}
</style>
<!--主体样式-->
<style lang="scss" scoped>
.content {
  width: 1230px;
  margin: auto;
  min-height: 400px;
  padding-bottom: 60px;
  color: #666;
}

.content > #crumbs {
  overflow: hidden;
  color: #000;
  margin: 20px 0 10px 0;
  height: 18px;
}

.content > #crumbs > .cart-tip {
  font-size: 12px;
  float: right;
  color: gray;
}

.content > #crumbs > .cart-tip > a {
  color: #36c;
}

.content > #empty {
  padding: 88px 0 100px 156px;
  background: url(../../assets/images/fore/WebsiteImage/T1TvXUXnNjXXXXXXXX-100-100.png) no-repeat 40px 86px;
  position: relative;
}

.content > #empty > h2 {
  font: 700 14px/20px Arial;
  color: #000000;
}

.content > #empty li {
  font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
  margin-top: 12px;
  color: #000;
}

.content > #empty a {
  font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
  color: #36c;
  margin-left: 3px;
}

.content > #J_FilterBar {
  overflow: hidden;
  font-size: 12px;
  position: relative;
}

.content > #J_FilterBar > #J_CartSwitch {
  overflow: hidden;
  height: 33px;
}

.content > #J_FilterBar > #J_CartSwitch > li {
  float: left;
  font-weight: 700;
  font-size: 16px;
  height: 16px;
  line-height: 1.1;
  font-family: 'Hiragino Sans GB', 'Lantinghei SC', 'Microsoft Yahei', SimSun, serif;
  text-align: center;
  padding: 0 0 15px 0;
  cursor: pointer;
  margin-left: -1px;
}

.content #J_CartSwitch > li > a {
  padding-left: 15px;
  color: #3c3c3c;
}

.content #J_CartSwitch > li > a:hover {
  text-decoration: none;
}

.content #J_CartSwitch > li > a > em {
  color: #FF0036;
  font-style: normal;
}

.content #J_CartSwitch > li > a > span {
  color: #FF0036;
  font-size: 14px;
  margin: 0 30px 0 0;
  font-weight: 400;
  font-family: Verdana, Tahoma, arial, serif;
}

.content > #J_FilterBar > .cart-sum {
  position: absolute;
  right: 0;
  top: 0;
  height: 25px;
  line-height: 25px;
  font-size: 12px;
}

.content .cart-sum > .pay-text {
  line-height: 25px;
}

.content .cart-sum > .price {
  font-family: Arial, Verdana, serif;
  font-weight: 700;
  margin-right: 5px;
  color: #FF0036;
}

.content .cart-sum > .price > #J_SmallTotal {
  font-family: Verdana, Arial, serif;
  padding-left: 2px;
  font-weight: 700;
  font-style: normal;
}

.total-symbol {
  font-size: 12px;
  font-weight: 400;
}

.cart-sum .submit-btn {
  display: inline-block;
  width: 55px;
  color: #FFFFFF;
  background: #F40;
  border-radius: 2px;
  text-align: center;
  cursor: pointer;
  font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
  line-height: 25px;
}

.cart-sum .submit-btn.submit-btn-disabled {
  background: #aaa;
  color: #FFFFFF;
  cursor: not-allowed;
}

.cart-sum .submit-btn:hover {
  text-decoration: none;
}

.content > #J_FilterBar > .wrap-line {
  background: #e6e6e6;
  height: 2px;
  position: relative;
}

.content > #J_FilterBar > .wrap-line > .floater {
  width: 123px;
  left: -1px;
  background: #FF0036;
  height: 2px;
  position: absolute;
  top: 0;
  overflow: hidden;
}

.content > #J_CartMain {
  width: 100%;
  min-height: 210px;
}

#J_CartMain > thead {
  height: 50px;
  line-height: 50px;
  color: #3c3c3c;
}

#J_CartMain > thead .selectAll_th {
  text-align: left;
  position: relative;
  height: 50px;
  width: 45px;
}

.selectAll_th label {
  font-weight: normal;
}

#J_CartMain > thead .productInfo_th {
  padding-left: 91px;
}

#J_CartMain > thead th {
  font: 12px 'Microsoft YaHei UI';
}

#J_CartMain > tbody tr.orderItem_category {
  position: relative;
  height: 38px;
  background: #FFFFFF;
  overflow: hidden;
}

tr.orderItem_category span.shop_logo {
  width: 16px;
  height: 16px;
  display: inline-block;
  vertical-align: middle;
  margin: -3px 6px 0 20px;
  background: url(../../assets/images/fore/WebsiteImage/TB1boCXfmCWBuNjy0FhXXb6EVXa-800-600.png) no-repeat -20px -105px;
}

tr.orderItem_category span.category_shop {
  font-size: 12px;
  color: #3c3c3c;
}

#J_CartMain > tbody tr.orderItem_info {
  border: 1px solid #cccccc;
  background: #fcfcfc;
  min-height: 119px;
}

#J_CartMain > tbody tr.orderItem_info.orderItem_selected {
  background: #fff8e1;
}

tr.orderItem_info > td {
  padding: 20px 0;
}

tr.orderItem_info > .tbody_checkbox {
  text-align: center;
}

tr.orderItem_info .orderItem_product_image {
}

tr.orderItem_info .orderItem_product_name {
  vertical-align: middle;
  display: inline-block;
  margin-left: 10px;
  font-size: 12px;
  max-width: 400px;
  max-height: 36px;
  overflow: hidden;
  text-overflow: ellipsis;
}

tr.orderItem_info .orderItem_product_name > a {
  color: #3c3c3c;
}

tr.orderItem_info .orderItem_product_name > a:hover {
  color: #ff0036;
}

tr.orderItem_info .orderItem_product_price {
  color: #3c3c3c;
  font-weight: 700;
  font-size: 12px;
  font-family: Verdana, Tahoma, Arial, serif;
}

tr.orderItem_info .item_amount {
  width: 77px;
  height: 25px;
  overflow: hidden;
  position: relative;
}

tr.orderItem_info .item_amount > a {
  display: block;
  width: 17px;
  height: 23px;
  border: 1px solid #e5e5e5;
  background: #F0F0F0;
  text-align: center;
  line-height: 23px;
  color: #444;
}

tr.orderItem_info .item_amount > a:hover {
  text-decoration: none;
}

tr.orderItem_info .item_amount > a.J_Minus {
  float: left;
}

tr.orderItem_info .item_amount > a.J_Plus {
  float: right;
}

tr.orderItem_info .item_amount > a.no_minus {
  color: #e5e5e5 !important;
}

tr.orderItem_info .item_amount > input[type=text] {
  width: 39px;
  height: 15px;
  border: 1px solid #aaaaaa;
  color: #343434;
  text-align: center;
  padding: 4px 0;
  background-color: #FFFFFF;
  position: absolute;
  left: 18px;
  top: 0;
  font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
  line-height: 15px;
}

tr.orderItem_info .orderItem_product_realPrice {
  font-family: Verdana, Tahoma, Arial, serif;
  font-style: normal;
  color: #FF0036;
  font-weight: 700;
  font-size: 12px;
}

tr.orderItem_info .remove_order {
  display: block;
  color: #3c3c3c;
  font-size: 12px;
  height: 15px;

}

tr.orderItem_info .remove_order:hover {
  color: #FF0036;
}

.content > #J_FloatBar {
  margin-top: 15px;
  height: 50px;
  overflow: hidden;
  background: #e5e5e5;
}

#J_FloatBar > #J_SelectAll2 {
  float: left;
  width: 50px;
  height: 50px;
  line-height: 50px;
  padding-left: 5px;
  font: 12px tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
}

#J_SelectAll2 > .cart_checkbox {
  float: left;
  vertical-align: middle;
  position: relative;
  overflow: hidden;
  line-height: 50px;
}

.cart_checkbox > input[type="checkbox"] + label:before {
  margin-right: .2em;
}

#J_SelectAll2 > span {
  float: left;
  line-height: 50px;
  color: #000;
}

#J_FloatBar > .operations {
  float: left;
  line-height: 50px;
  height: 50px;
}

.operations > a {
  margin-left: 25px;
  float: left;
  color: #3c3c3c;
  font: 12px/50px tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif
}

.operations > a:hover {
  color: #FF0036;
}

#J_FloatBar > .float-bar-right {
  float: right;
  padding-left: 20px;
  background: #e5e5e5;
  height: 100%;
}

.float-bar-right > #J_ShowSelectedItems {
  cursor: pointer;
  height: 48px;
  color: #3c3c3c;
  float: left;
  font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
}

#J_ShowSelectedItems > .txt {
  float: left;
  display: inline-block;
  line-height: 48px;
}

#J_ShowSelectedItems > #J_SelectedItemsCount {
  float: left;
  display: inline-block;
  line-height: 50px;
  padding: 0 5px;
  color: #FF0036;
  font-weight: 700;
  font-size: 18px;
  font-style: normal;
  font-family: tohoma, Arial, serif;
}

.float-bar-right > .price_sum {
  padding-left: 40px;
  height: 48px;
  color: #3c3c3c;
  float: left;
}

.price_sum > .txt {
  float: left;
  color: #3c3c3c;
  font: 12px/48px tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif
}

.price_sum > .price {
  color: #FF0036;
  font-weight: 400;
  font-size: 18px;
  line-height: 48px;
  font-family: Arial, serif;
  vertical-align: middle;
  float: left;
  display: inline-block;
}

.price_sum > .price > #J_Total {
  font-weight: 700;
  font-size: 22px;
  padding: 0 3px;
  font-family: tohoma, Arial, serif;
  font-style: normal;
}

#J_Total > .total_symbol {
  font-size: 14px;
  font-family: Verdana, serif;
  font-weight: 400;
  color: #FF0036;
}

.float-bar-right > .btn_area {
  float: left;
}

.btn_area > a {
  display: block;
  background: #B0B0B0;
  color: #FFF;
  border-left: 1px solid #e7e7e7;
  width: 119px;
  cursor: not-allowed;
  height: 50px;
  line-height: 50px;
  text-align: center;
  font-family: "Lantinghei SC", "Microsoft YaHei UI", serif;
  font-size: 20px;
  border-radius: 2px;
}

.btn_area > a:hover {
  text-decoration: none;
}

.btn_area > a.selected {
  background: #FF0036;
  cursor: pointer;
}

.btn_area > a.selected:hover {
  background: #FF0026;
}

input[type="checkbox"] + label::before {
  content: "\a0";
  position: relative;
  bottom: 1px;
  display: inline-block;
  vertical-align: middle;
  font-size: 18px;
  width: 13px;
  height: 13px;
  margin-right: .4em;
  border: 1px solid #cccccc;
  text-indent: .15em;
  line-height: 1;
  cursor: pointer;
}

input[type="checkbox"]:checked + label::before {
  background-color: #ff7874;
  background-clip: content-box;
  padding: 2px;
}

input[type="checkbox"] {
  position: absolute;
  clip: rect(0, 0, 0, 0);
}
</style>
