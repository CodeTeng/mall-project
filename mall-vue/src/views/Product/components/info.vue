<template>
  <div class="product-info-main">
    <div class="header">
      <router-link to="/mall"
        ><img src="../../../assets/images/fore/WebsiteImage/tmallLogoD.png"
      /></router-link>
      <span class="shopNameHeader">贤趣女装 /大衣官方旗舰店</span>
      <input id="tid" type="hidden" value="1" />
      <img
        src="../../../assets/images/fore/WebsiteImage/detailsHeaderA.png"
        class="shopAssessHeader"
      />
      <div class="shopSearchHeader">
        <form action="/product" method="get">
          <div class="shopSearchInput">
            <input
              type="text"
              class="searchInput"
              name="productName"
              placeholder="搜索 商品/品牌/店铺"
              maxlength="50"
            />
            <input type="submit" value="搜Mall" class="searchTmall" />
          </div>
          <input type="submit" value="搜本店" class="searchShop" />
        </form>
        <ul>
          <li>
            <router-link to="/product?categoryId=1">女装 /大衣</router-link>
          </li>

          <li>
            <router-link to="/product?categoryId=2">男装 /运动户外</router-link>
          </li>

          <li>
            <router-link to="/product?categoryId=3"
              >女鞋 /男鞋 /箱包
            </router-link>
          </li>
        </ul>
      </div>
    </div>

    <nav></nav>

    <!-- 商品图片信息 -->
    <div class="context">
      <div class="context_left">
        <!-- 放大主图 -->
        <ImgToBig :img="bindImg(showProductImage)" />

        <!-- 可放大图列表-->
        <ul class="context_img_ul">
          <li
            :class="[
              'context_img_li',
              showProductImage === simg ? 'context_img_li_hover' : '',
            ]"
            v-for="simg in productDetails?.singleProductImageList"
            :key="simg"
            @mouseover="changeShowProductImage(simg)"
          >
            <img :src="bindImg(simg)" alt="" />
          </li>
        </ul>
      </div>

      <!-- 商品信息 -->
      <div class="context_info">
        <div class="context_info_name_div">
          <p class="context_info_name">
            {{ productDetails?.productName }}
          </p>
          <span class="context_info_title">春装新款百搭~~</span>
        </div>
        <div class="context_info_main">
          <div class="context_info_main_ad">
            <img
              src="../../../assets/images/fore/WebsiteImage/context_ad.png"
            />
            <span>全Mall商城实物商品通用</span>
            <a href="#"
              >去刮券<img
                src="../../../assets/images/fore/WebsiteImage/tmallItemContentB.png"
            /></a>
          </div>
          <dl class="context_price_panel">
            <dt>价格</dt>
            <dd>
              <em>¥</em><span>{{ productDetails?.productPrice }}</span>
            </dd>
          </dl>
          <dl class="context_promotePrice_panel">
            <dt>促销价</dt>
            <dd>
              <em>¥</em><span>{{ productDetails?.productSalePrice }}</span>
            </dd>
          </dl>
        </div>
        <ul class="context_other_panel">
          <li>
            总销量<span>{{ productDetails?.totalSales }}</span>
          </li>
          <li>
            累计评价<span>{{ productDetails?.reviewCount }}</span>
          </li>
          <li class="tmall_points">送Mall积分<span>8</span></li>
        </ul>
        <dl class="context_info_member">
          <dt>数量</dt>
          <dd>
            <input
              type="text"
              :value="productNumber"
              maxlength="8"
              title="请输入购买量"
              class="context_buymember"
            />
            <input type="hidden" id="stock" value="1000" />
            <span class="amount-btn">
              <img
                src="../../../assets/images/fore/WebsiteImage/up.png"
                @click="addProductNumber"
              />
              <img
                src="../../../assets/images/fore/WebsiteImage/down.png"
                width="18"
                @click="reduceProductNumber"
              />
            </span>

            <span class="amount_unit">件</span>
            <em>库存{{ 5000-productDetails?.totalSales  }}件</em>
          </dd>
        </dl>
        <div class="context_buy">
          <div method="get" class="context_buy_form">
            <input
              class="context_buyNow"
              type="submit"
              value="立即购买"
              @click="OnOrderBuy()"
            />
            <input
              class="context_addBuyCar"
              type="submit"
              value="加入购物车"
              @click="OnAddToCart()"
            />
          </div>
        </div>
        <div class="context_clear">
          <span>服务承诺</span>
          <a href="#">正品保证</a>
          <a href="#">极速退款</a>
          <a href="#">七天无理由退换</a>
        </div>
      </div>
    </div>

    <div class="mainwrap">
      <div class="J_TabBarBox">
        <ul>
          <li
            :class="['J_GoodsDetails', isShowJ_Details ? 'tab-selected' : '']"
            @click="isShowJ_Details = true"
          >
            <a href="javascript:void(0)" class="detailsClick">商品详情</a>
          </li>

          <li
            :class="[
              'J_GoodsReviews',
              isShowJ_Details === false ? 'tab-selected' : '',
            ]"
            @click="isShowJ_Details = false"
          >
            <a href="javascript:void(0)"
              >累计评价<span>{{ productDetails?.reviewCount }}</span></a
            >
          </li>
        </ul>
      </div>

      <div class="J_choose">
        <!--   商品数量   -->
        <div class="J_details" v-show="isShowJ_Details">
          <div class="J_details_list">
            <p class="J_details_list_header">
              产品名称：<span>{{ productDetails?.productName }}</span>
            </p>

            <!-- 产品属性参数 -->
            <p class="J_details_list_title">产品参数：</p>
            <ul class="J_details_list_body">
              <li
                :title="productParameter.propertyValueValue"
                v-for="productParameter in productDetails?.productParameterList"
                :key="productParameter.propertyName"
              >
                {{
                  productParameter.propertyName +
                  "：" +
                  productParameter.propertyValueValue
                }}
              </li>
            </ul>
          </div>
        </div>
        <!--   商品评论   -->
        <div class="J_reviews" v-show="!isShowJ_Details">
          <div class="J_reviews_main">
            <!-- 评论信息单项列表 -->
            <div
              class="reviews_info"
              v-for="rp in reviewPeopleList"
              :key="rp.reviewCreatedate"
            >
              <div class="reviews_main">
                <div class="reviews_content">
                  <p>
                    {{ rp.reviewContent }}
                  </p>
                </div>
                <div class="reviews_date">
                  <span>{{ rp.reviewCreatedate }}</span>
                </div>
              </div>
              <div class="reviews_author">{{ rp.userNickName }}</div>
            </div>

            <el-pagination
              :hide-on-single-page="reviewCount <= reviewPeoplePage.pageSize"
              :total="reviewCount"
              v-model:current-page="reviewPeoplePage.current"
              layout="prev, pager, next"
              v-model:page-size="reviewPeoplePage.pageSize"
              @current-change="doGetReviewPeopleList()"
            />
          </div>
        </div>
      </div>

      <!-- 商品大图列表 -->
      <div class="J_img">
        <img
          v-for="dimg in productDetails?.detailProductImageList"
          :key="dimg"
          :src="bindImg(dimg)"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">

import { useRoute, useRouter } from "vue-router";
import { bindImg } from "../../../utils";
import { ref } from "vue";
import { getDetailedProductApi } from "../../../api/product";
import { productDetailsType } from "../../../api/product/type";
// @ts-ignore
import ImgToBig from "./img-part-to-big.vue";
import { pageReviewType, reviewPeople } from "../../../api/review/type";
import { getPageReviewByProductIdApi } from "../../../api/review";
import {addCartItemApi} from "../../../api/cart";
import {CartItemVO} from "../../../api/cart/type";
const route = useRoute();
const router = useRouter();

const productId = ref();
// 商品ID
productId.value = route.params?.id;

// 商品数量
const productNumber = ref<number>(1);
// 增加
const addProductNumber = () => {
  productNumber.value = productNumber.value + 1;
};
// 减少
const reduceProductNumber = () => {
  if (productNumber.value > 1) {
    productNumber.value = productNumber.value - 1;
  }
};

// 商品详细信息
const productDetails = ref<productDetailsType>({
  detailProductImageList: [],
  productId: 0,
  productName: "",
  productParameterList: [],
  productPrice: 0,
  productSalePrice: 0,
  reviewCount: 0,
  singleProductImageList: [],
  totalSales: 0
});

// 获得商品详细信息
const doGetDetailedProduct = () => {
  getDetailedProductApi(productId.value).then((res) => {
    if (res.code === 0) {
      productDetails.value = res.data;
      showProductImage.value = productDetails.value?.singleProductImageList[0];
    } else {
      ElMessage.error("获取商品信息失败");
    }
  });
};

// 商品展示的图片
const showProductImage = ref<string>();
const changeShowProductImage = (imgUrl: string) => {
  showProductImage.value = imgUrl;
};

// 图片放大处理
const contextImgKsDisplay = ref("block");
const contextImgWinSelectorDisplay = ref("none");

// 是否展示J_Details
const isShowJ_Details = ref<boolean>(true);

// 获得商品评论
const reviewPeopleList = ref<reviewPeople[]>();

const reviewPeoplePage = ref<pageReviewType>({
  current: 1,
  pageSize: 15,
  productId: 0,
});

const reviewCount = ref<number>(0);

const pageChange = ()=>{

}
const doGetReviewPeopleList = () => {
  const data = ref<any>();
  reviewPeoplePage.value.productId = productId.value;
  getPageReviewByProductIdApi(reviewPeoplePage.value).then((res) => {
    if (res.code === 0) {
      data.value = res;
      reviewPeopleList.value = data.value.data.records;
      reviewCount.value = data.value.data.total;
    } else {
      console.log("获取评论失败");
    }
  });
};

// 购买商品
const OnOrderBuy = () => {
  const data:CartItemVO = {
    productId: 0,
    productOrderItemNumber: 0,
    productSalePrice: 0
  }

  data.productSalePrice = productDetails.value?.productSalePrice;
  data.productId = productId.value;
  data.productOrderItemNumber = productNumber.value;

  addCartItemApi(data).then(res=>{
    if(res.code===0){
      router.push({
        path: "/order/buy",
        query:{list:res.data,type:2},
      });
    }else{
      ElMessage.error("立即购买失败")
    }
  })

};

// 购物车的信息
const CartItem = ref<CartItemVO>({
  productId: 0,
  productOrderItemNumber: 0,
  productSalePrice: 0,
})

// 商品加入购物车
const OnAddToCart = () => {
  // 商品数量
  if(productNumber.value&&productDetails.value?.productId&&productDetails.value?.productSalePrice){
    CartItem.value.productOrderItemNumber = productNumber.value;
    CartItem.value.productId = productDetails.value.productId;
    CartItem.value.productSalePrice = productDetails.value.productSalePrice;
  }
  addCartItemApi(CartItem.value).then(res=>{
    if(res.code===0){
      ElMessage.success("添加购物车成功")
    }else{
      ElMessage.success("添加购物车失败")
    }
  })
};

// 初始化加载
onMounted(() => {
  // 加载数据
  doGetDetailedProduct();

  // 加载评论数据
  doGetReviewPeopleList();
});
</script>
<!--头部样式-->
<style lang="scss" scoped>
/*头部样式*/
#baseNavigator {
  padding: 22px 0;
  width: 1190px;
  height: 44px;
  margin: auto;
}

#baseNavigator img {
  width: 190px;
  margin-top: 8px;
}

#nav {
  width: auto;
  height: 32px;
  font-family: "Microsoft YaHei UI", Tahoma, serif;
  font-size: 12px;
  position: relative !important;
  background: #f2f2f2;
  z-index: 999;
  border-bottom: 1px solid #e5e5e5;
}

.nav_main {
  width: 1230px;
  margin: auto;
  height: 100%;
  position: relative;
}

.nav_main a {
  color: #999;
  padding-left: 20px;
}

.nav_main .userName {
  padding: 0;
}

.nav_main a:hover {
  color: #ff335e;
  text-decoration: none;
}

.nav_main > #container_login {
  position: absolute;
  top: 0;
  left: 0;
  line-height: 32px;
  color: #999;
  margin: 0;
}

#container_login > em {
  font-style: normal;
}

.nav_main .quick_li {
  position: absolute;
  right: 0;
  top: 0;
}

.quick_li > li {
  float: left;
  position: relative;
}

.quick_li > li > a {
  line-height: 32px;
  padding: 0 10px;
}

.quick_li > li > img {
  position: relative;
  left: 10px;
  bottom: 2px;
}

.quick_li > li > div > a {
  line-height: 32px;
  padding: 0 14px 0 10px;
}

.quick_li > li > .sn_menu {
  position: relative;
}

.sn_menu > a {
  display: block;
  position: relative;
  cursor: pointer;
  border: solid #f2f2f2;
  border-width: 0 1px;
}

.sn_menu_hover {
  background: #ffffff;
  border-right: 1px solid #eeeeee;
  border-left: 1px solid #eeeeee;
  border-bottom: 1px solid #ffffff;
}

.sn_menu > a > b {
  position: absolute;
  margin-left: 2px;
  top: 15px;
  width: 0;
  height: 0;
  border: 3px dashed transparent;
  border-top: solid #bbb;
  font-size: 0;
  line-height: 0;
}

.quick_menu {
  width: 74px;
  line-height: 1.4;
  padding: 8px 10px;
  display: none;
  position: absolute;
  top: 33px;
  left: 0;
  background: #ffffff;
  border: solid #eeeeee;
  border-width: 0 1px 1px;
}

.quick_menu > a {
  line-height: 20px;
  color: #666;
  padding: 0;
}

.quick_menu > a:hover {
  text-decoration: underline;
}

.quick_qrcode {
  padding: 12px 15px;
  position: absolute;
  background: #ffffff;
  left: -48px;
  top: 26px;
  display: none;
  box-shadow: 0 0 5px #aaa;
}

.quick_qrcode > b,
.quick_sitemap > b {
  width: 0;
  height: 0;
  line-height: 0;
  font-size: 0;
  position: absolute;
  left: 50%;
  top: -10px;
  margin-left: -2px;
  border-width: 5px;
  border-style: dashed dashed solid;
  border-color: transparent transparent #ffffff;
}

.quick_sitemap > .sn_menu > a:hover {
  color: #999999;
  text-decoration: none;
}

.quick_DirectPromoDiv {
  width: 122px;
  right: 0;
  left: auto;
  display: none;
  line-height: 1.4;
  padding: 8px 10px;
  top: 33px;
  position: absolute;
  background: #ffffff;
  border: solid #eeeeee;
  border-width: 0 1px 1px;
}

.quick_DirectPromoDiv > ul {
  margin-top: -5px;
  overflow: hidden;
  width: 122px;
}

.quick_DirectPromoDiv li {
  float: none;
  padding: 5px 0;
  height: auto;
  display: block;
  line-height: 1.4;
  border-bottom: 1px dotted #827777;
  margin-right: -20px;
  position: relative;
}

.quick_DirectPromoDiv li > h3 {
  color: #666;
  font-weight: 700;
  font-size: 12px;
  margin: 0;
}

.quick_DirectPromoDiv li > a {
  color: #666666;
  display: inline-block;
  width: 48px;
  margin-right: 20px;
  line-height: 22px;
  padding: 0;
}

.quick_sitmap_div {
  width: 1198px;
  right: 0;
  display: none;
  left: auto;
  padding: 25px 0;
  line-height: 1.4;
  top: 33px;
  position: absolute;
  border: 1px solid #eeeeee;
  border-top: 0;
  border-bottom: 0;
  background: #ffffff;
}

.quick_sitmap_div a {
  padding: 0;
}

.quick_sitmap_div > .site-hot {
  width: 275px;
  padding-left: 30px;
  overflow: hidden;
  height: 200px;
  float: left;
}

.site-hot > h2 {
  color: #f56a00;
  font-size: 16px;
  padding-bottom: 8px;
  font-family: "\5FAE\8F6F\96C5\9ED1", arial, "\5b8b\4f53", serif;
  font-weight: 100;
}

.site-hot > ul {
  margin-right: -20px;
  overflow: hidden;
}

.site-hot li {
  width: 95px;
  float: left;
  padding: 6px 0;
  overflow: hidden;
  position: relative;
}

.quick_sitmap_div > .site-market {
  width: 375px;
  border-left: 1px solid #f5f5f5;
  border-right: 1px solid #f5f5f5;
  padding-left: 30px;
  overflow: hidden;
  height: 200px;
  float: left;
}

.site-market > h2 {
  color: #2263d4;
  font-size: 16px;
  padding-bottom: 8px;
  font-family: "\5FAE\8F6F\96C5\9ED1", arial, "\5b8b\4f53", serif;
  font-weight: 100;
}

.site-market > ul {
  margin-right: -20px;
  overflow: hidden;
}

.site-market li {
  width: 95px;
  float: left;
  padding: 6px 0;
  overflow: hidden;
  position: relative;
}

.quick_sitmap_div > .site-brand {
  width: 270px;
  padding-left: 30px;
  overflow: hidden;
  height: 200px;
  float: left;
  border-right: 1px solid #f5f5f5;
}

.site-brand > h2 {
  color: #000;
  font-size: 16px;
  padding-bottom: 8px;
  font-family: "\5FAE\8F6F\96C5\9ED1", arial, "\5b8b\4f53", serif;
  font-weight: 100;
}

.site-brand > ul {
  margin-right: -20px;
  overflow: hidden;
}

.site-brand li {
  width: 95px;
  float: left;
  padding: 6px 0;
  overflow: hidden;
  position: relative;
}

.quick_sitmap_div > .site-help {
  padding-left: 30px;
  overflow: hidden;
  height: 200px;
}

.site-help > h2 {
  color: #666;
  font-size: 16px;
  padding-bottom: 8px;
  font-family: "\5FAE\8F6F\96C5\9ED1", arial, "\5b8b\4f53", serif;
  font-weight: 100;
}

.site-help > ul {
  margin-right: -20px;
  overflow: hidden;
}

.site-help li {
  width: 95px;
  float: left;
  padding: 6px 0;
  overflow: hidden;
  position: relative;
}

.mc_count {
  color: #666;
  padding: 0 2px;
  font-weight: 700;
  font-family: Arial, serif;
}

.quick_li_separator {
  display: inline-block;
  width: 0;
  height: 14px;
  line-height: 14px;
  vertical-align: top;
  position: relative;
  left: 0;
  font-size: 0;
  border-left: 1px solid #cccccc;
  margin: 0 2px 0 5px;
  top: 9px;
}

.quick_li_cart > a:hover {
  color: #ff0036;
  font-weight: 700;
}
</style>
<!--main主体样式-->
<style scoped>
nav {
  width: 100%;
  border-bottom: 1px solid #c8baaa;
}

.loginModel {
  display: none;
  width: 100%;
  height: 100%;
  position: fixed;
  z-index: 9999;
  left: 0;
  top: 0;
  user-select: none;
  background-color: #000;
  opacity: 0.5;
  filter: alpha(opacity=50);
}

.loginDiv {
  display: none;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  width: 350px;
  height: 436px;
  z-index: 99999;
  margin: auto;
  background-color: #ffffff;
  border: 10px solid rgba(0, 0, 0, 0.4);
}

.loginDiv > .loginDivHeader {
  background: #ff0036
    url(../../../assets/images/fore/WebsiteImage/TB1VLoORXXXXXc4XXXXXXXXXXXX-132-41.png)
    20px 10px no-repeat;
  height: 35px;
}

.loginDivHeader > .closeLoginDiv {
  position: absolute;
  z-index: 3;
  right: 10px;
  top: 10px;
  height: 16px;
  width: 16px;
  background: #ff0036
    url(../../../assets/images/fore/WebsiteImage/TB1VLoORXXXXXc4XXXXXXXXXXXX-132-41.png)
    0 -25px no-repeat;
}

.loginSwitch {
  position: absolute;
  width: 52px;
  height: 52px;
  right: 5px;
  top: 45px;
  background-image: url(../../../assets/images/fore/WebsiteImage/scan_icon.png);
  cursor: pointer;
  -webkit-background-size: cover;
  background-size: cover;
}

.loginSwitch_two {
  position: absolute;
  width: 52px;
  height: 52px;
  right: 5px;
  top: 45px;
  background-image: url(../../../assets/images/fore/WebsiteImage/pc_icon.png);
  cursor: pointer;
  -webkit-background-size: cover;
  background-size: cover;
}

.loginDiv > .loginMessage {
  position: absolute;
  top: 45px;
  right: 58px;
}

.loginMessage > .loginMessageMain {
  border: 1px solid #f3d995;
  height: 16px;
  line-height: 16px;
  background: #fefcee;
  color: #df9c1f;
  font-size: 12px;
  font-weight: 400;
  padding: 5px 20px 5px 15px;
  position: relative;
}

.loginMessageMain > img {
  margin-right: 8px;
  position: relative;
  bottom: 2px;
}

.loginMessageMain > .poptip-arrow {
  position: absolute;
  top: 8px;
  right: 0;
}

.poptip-arrow > em {
  position: absolute;
  width: 0;
  height: 0;
  top: 0;
  left: 1px;
  border: 6px solid rgba(255, 255, 255, 0);
  border-right-width: 0;
  border-left-color: #f3d995;
}

.poptip-arrow > span {
  position: absolute;
  width: 0;
  height: 0;
  top: 0;
  left: 0;
  border: 6px solid rgba(255, 255, 255, 0);
  border-right-width: 0;
  border-left-color: #fefcee;
}

.loginDiv > .pwdLogin {
  padding: 25px 26px 20px;
}

.pwdLogin .loginInputDiv {
  position: relative;
  margin-top: 25px;
}

.loginInputDiv > .loginLabel {
  display: block;
  width: 38px;
  height: 38px;
  line-height: 38px;
  background: #ddd;
  text-align: center;
  position: absolute;
  top: 1px;
  left: 1px;
  font-size: 18px;
}

.loginLabel > i {
  color: #606060;
}

.loginInputDiv > .loginInput {
  color: #9b9b9b;
  width: 240px;
  font-size: 14px;
  line-height: 18px;
  height: 18px;
  padding: 11px 8px 11px 50px;
  border: 1px solid #cbcbcb;
}

.pwdLogin .loginButton {
  width: 300px;
  height: 42px;
  line-height: 42px;
  background-color: #ff0036;
  font-weight: 700;
  font-size: 16px;
  color: #ffffff;
  cursor: pointer;
  border-radius: 3px;
  border: 0;
  margin-top: 20px;
}

.loginButton:hover {
  background-color: #ff335e;
}

.pwdLogin .loginLinks {
  margin-top: 25px;
  text-align: right;
  font-size: 12px;
}

.loginLinks > a {
  margin-right: 10px;
  color: #6c6c6c;
  text-decoration: none;
}

.loginLinks > a:hover {
  color: #ff0036;
}

.pwdLogin .error_message {
  margin-top: 30px;
  text-align: center;
  font-size: 14px;
}

.error_message > p {
  position: relative;
  color: #c33;
  left: 20px;
  opacity: 0;
  margin: 0;
}

.loginTitle {
  display: block;
  margin-top: 15px;
  height: 18px;
  line-height: 18px;
  font-size: 16px;
  color: #3c3c3c;
  font-weight: 700;
}

.loginDiv > .qrcodeLogin {
  display: none;
  padding: 25px 26px 20px;
}

.qrcodeLogin > .qrcodeMain {
  position: relative;
  margin-top: 24px;
  height: 140px;
}

.qrcodeMain > #qrcodeA {
  position: absolute;
  left: 80px;
}

.qrcodeMain > #qrcodeB {
  position: absolute;
  opacity: 0;
  right: 12px;
  top: -15px;
}

.qrcodeLogin > .qrcodeFooter {
  width: 188px;
  margin: 24px auto 0;
  overflow: hidden;
}

.qrcodeFooter > img {
  float: left;
  margin-right: 10px;
  padding-top: 5px;
}

.qrcodeFooter > p {
  float: left;
  width: 144px;
  line-height: 18px;
  color: #6c6c6c;
  font-size: 12px;
}

.qrcodeFooter > p > a {
  color: #ff0036;
}

.qrcodeFooter > p > a:hover {
  text-decoration: none;
}

.qrcodeLogin > .loginLinks {
  margin-top: 15px;
  overflow: hidden;
  text-align: right;
  font-size: 12px;
}

.header {
  margin-bottom: 20px;
  width: 1230px;
  margin: 0 auto;
  padding-top: 15px;
  height: 70px;
}

.header > .shopNameHeader {
  display: inline-block;
  height: 38px;
  line-height: 38px;
  font-size: 12px;
  font-weight: bold;
  font-family: "宋体", serif;
  border-left: 1px solid #f0f0f0;
  padding: 0 10px;
}

.header > .shopAssessHeader {
  border-left: 1px dashed #f0f0f0;
  border-right: 1px solid #f0f0f0;
}

.header > .shopSearchHeader {
  float: right;
  font-family: "宋体", serif;
  position: relative;
}

.shopSearchHeader .shopSearchInput {
  width: 455px;
  border: solid #ff0036;
  border-width: 3px 0 3px 3px;
  height: 30px;
  margin-right: 82px;
  position: relative;
}

.shopSearchInput > .searchInput {
  width: 367px;
  padding: 5px 3px 5px 5px;
  color: #000;
  margin: 0;
  height: 20px;
  line-height: 20px;
  outline: 0;
  border: none;
  font-size: 12px;
}

.shopSearchInput > .searchTmall {
  position: absolute;
  top: 0;
  right: 0;
  width: 80px;
  height: 30px;
  background-color: #ff0036;
  border: 0;
  color: #ffffff;
  font-size: 16px;
  font-family: "Microsoft YaHei UI", serif;
}

.shopSearchHeader .searchShop {
  position: absolute;
  top: 0;
  right: 0;
  background-color: #333;
  color: #ffffff;
  font-size: 16px;
  font-family: "Microsoft YaHei UI", serif;
  width: 80px;
  height: 36px;
  border: 0;
}

.shopSearchHeader > ul {
  padding: 6px 0 0;
  height: 16px;
  margin-left: -13px;
  font-size: 13px;
  font-family: "宋体", serif;
  overflow: hidden;
}

.shopSearchHeader li {
  float: left;
  display: inline-block;
  padding: 0 12px;
  line-height: 1.1;
}

.shopSearchHeader li + li {
  border-left: 1px solid #cccccc;
}

.shopSearchHeader li > a {
  color: #999;
  text-decoration: none;
}

.shopSearchHeader li > a:hover {
  text-decoration: underline;
}

.shopImg {
  width: 1190px;
  margin: 0 auto;
}

.context {
  width: 1190px;
  height: 600px;
  margin: 20px auto;
}

.context > .context_left {
  float: left;
  height: 100%;
  margin-left: 140px;
  position: relative;
}

.context_left > .context_img_ks {
  display: v-bind(contextImgKsDisplay);
  position: absolute;
  width: 418px;
  height: 418px;
  left: 460px;
  top: 20px;
  border: 1px solid #cccccc;
  overflow: hidden;
  text-align: center;
  z-index: 999;
  background-color: #ffffff;
}

.context_img_ks > img {
  position: absolute;
}

.context_left > .context_img {
  position: relative;
  overflow: hidden;
  margin: 20px;
}

.context_img > .context_img_main {
  width: 418px;
  height: 418px;
  border: 1px solid rgba(0, 0, 0, 0.05);
}

.context_img > .context_img_winSelector {
  position: absolute;
  cursor: move;
  top: 0;
  left: 0;
  width: 218px;
  height: 218px;
  display: v-bind(contextImgWinSelectorDisplay);
  background: url(../../../assets/images/fore/WebsiteImage/T12pdtXaldXXXXXXXX-2-2.png);
}

.context_left > .context_img_ul {
  margin: 0;
  text-align: center;
}

.context_img_ul > .context_img_li {
  margin: 0 6px;
  display: inline-block;
  width: 60px;
  height: 60px;
  cursor: pointer;
}

.context_img_li_hover {
  border: 2px solid #000000;
}

.context_img_li > img {
  width: 60px;
  height: 60px;
}

.context > .context_info {
  float: left;
  height: 100%;
}

.context_info > .context_info_name_div {
  padding: 20px 10px 12px;
  color: #000000;
}

.context_info_name_div > .context_info_name {
  width: 510px;
  padding-bottom: 0.3em;
  line-height: 1;
  font-size: 16px;
  font-weight: 700;
  font-family: "Microsoft YaHei UI", serif;
  margin: 0;
}

.context_info_name_div > .context_info_title {
  display: block;
  width: 510px;
  font-size: 14px;
  color: #ff0036;
  font-family: "Microsoft YaHei UI", serif;
}

.context_info > .context_info_main {
  background: #e9e9e9
    url(../../../assets/images/fore/WebsiteImage/tmallItemContent.png) no-repeat;
  position: relative;
  padding-bottom: 5px;
  padding-top: 5px;
  margin-right: 20px;
}

.context_info_main > .context_info_main_ad {
  position: relative;
  height: 35px;
  margin: 0 10px 0 8px;
}

.context_info_main_ad > img {
  float: left;
  display: inline-block;
  margin: 10px 5px 0 0;
}

.context_info_main_ad > span {
  font: 400 12px/35px "Microsoft YaHei UI", tahoma, arial;
  color: #666;
}

.context_info_main_ad > a {
  float: right;
  font: normal 12px/35px "Microsoft YaHei UI", tahoma, arial;
  display: inline-block;
  color: #ff0036;
  cursor: pointer;
}

.context_info_main_ad > a > img {
  position: relative;
  bottom: 1px;
  padding-left: 5px;
}

.context_price_panel {
  margin: 0;
}

.context_price_panel > dt {
  font-family: "Microsoft YaHei UI", serif;
  font-weight: normal;
  color: #999;
  font-size: 12px;
  text-align: left;
  float: left;
  width: 69px;
  height: 20px;
  line-height: 20px;
  margin-left: 8px;
}

.context_price_panel > dd {
  margin-left: 70px;
  height: 20px;
  line-height: 20px;
}

.context_price_panel > dd > em {
  font-family: Arial, serif;
  font-style: normal;
}

.context_price_panel > dd > span {
  text-decoration: line-through;
  font-size: 14px;
  color: #333333;
  font-family: Arial, serif;
}

.context_promotePrice_panel {
  margin: 0;
}

.context_promotePrice_panel > dt {
  font-family: "Microsoft YaHei UI", serif;
  font-weight: normal;
  color: #999;
  font-size: 12px;
  text-align: left;
  float: left;
  width: 69px;
  margin-left: 8px;
  height: 42px;
  line-height: 42px;
}

.context_promotePrice_panel > dd {
  margin-left: 70px;
  height: 42px;
  line-height: 42px;
}

.context_promotePrice_panel > dd > em {
  font-style: normal;
  color: #ff0036;
  font-size: 18px;
  font-family: Arial, serif;
}

.context_promotePrice_panel > dd > span {
  font-style: normal;
  font-size: 30px;
  color: #ff0036;
  font-weight: bolder;
  font-family: Arial, serif;
}

.context_other_panel {
  border: dotted #c9c9c9;
  border-width: 1px 0;
  margin-top: 10px;
  margin-bottom: 0;
  margin-right: 20px;
  padding: 10px 0;
  position: relative;
}

.context_other_panel > li {
  font-family: "Microsoft YaHei UI", serif;
  color: #999;
  font-size: 12px;
  display: inline-block;
  width: 32%;
  text-align: center;
  line-height: 16px;
}

.context_other_panel > li + li {
  border-left: 1px solid #e5dfda;
}

.context_other_panel > li > span {
  font-family: tohoma, Arial, serif;
  display: inline-block;
  color: #ff0036;
  font-weight: 700;
  margin-left: 3px;
}

.context_other_panel > .tmall_points > span {
  color: #280;
}

.context_info_member {
  margin: 0;
  margin-top: 20px;
}

.context_info_member > dt {
  font-family: "Microsoft YaHei UI", serif;
  font-weight: normal;
  color: #999;
  font-size: 12px;
  text-align: left;
  float: left;
  width: 69px;
  margin-left: 8px;
  height: 31px;
  line-height: 31px;
}

.context_info_member > dd {
  height: 31px;
  line-height: 31px;
}

.context_info_member > dd > input {
  padding: 3px 2px 0 2px;
  font-size: 12px;
  font-family: Arial, serif;
  border: 1px solid #a7a6ac;
  width: 36px;
  height: 26px;
  color: #666;
  display: inline-block;
  float: left;
  margin-right: 5px;
  outline: none;
}

.amount-btn {
  float: left;
  display: inline-block;
  width: 18px;
}

.amount-btn > img {
  cursor: pointer;
  float: left;
}

.amount-btn > img + img {
  margin-top: 3px;
}

.amount_unit {
  font-family: "Microsoft YaHei UI", serif;
  font-weight: normal;
  color: #999;
  font-size: 12px;
  text-align: left;
  float: left;
  margin-left: 10px;
}

.context_info_member em {
  font-style: normal;
  font-family: "Microsoft YaHei UI", serif;
  margin-left: 20px;
  color: #999;
  font-size: 12px;
  text-align: left;
  float: left;
}

.context_buy {
  text-align: center;
  margin-top: 25px;
}

.context_buy input {
  display: inline-block;
  margin: 0 2px;
}

.context_buyNow {
  width: 178px;
  background-color: #ffeded;
  border: 1px solid #ff0036;
  color: #ff0036;
  font-family: "Microsoft YaHei UI", serif;
  height: 38px;
  line-height: 38px;
  font-size: 16px;
}

.context_addBuyCar {
  width: 178px;
  background-color: #ff0036;
  border: 1px solid #ff0036;
  color: #ffffff;
  font-family: "Microsoft YaHei UI", serif;
  height: 38px;
  line-height: 38px;
  font-size: 16px;
}

.context_notBuy {
  background-color: #f9f9f9;
  color: #818181;
  border: 1px solid #818181;
}

.context_notCar {
  background-color: #818181;
  color: #ffffff;
  border: 1px solid #818181;
}

.context_clear {
  width: 490px;
  margin: 0 20px;
  margin-top: 50px;
}

.context_clear > span {
  display: inline-block;
  font-family: "Microsoft YaHei UI", serif;
  color: #999;
  font-size: 12px;
  text-align: left;
  padding-right: 10px;
}

.context_clear > a {
  display: inline-block;
  font-family: "Microsoft YaHei UI", serif;
  color: #666;
  font-size: 12px;
  text-align: left;
  padding-right: 30px;
  padding-bottom: 3px;
}

.context_clear > a:hover {
  text-decoration: none;
}

.context_ul {
  width: 199px;
  float: right;
  border-left: 1px solid #f2f2f2;
  height: 100%;
  overflow: hidden;
}

.context_ul > .context_ul_head {
  position: relative;
  height: 15px;
  margin-top: 5px;
}

.context_ul_head > s {
  position: absolute;
  top: 10px;
  left: 30px;
  width: 140px;
  height: 0;
  border-top: 1px #c9c9c9 dotted;
  text-decoration: none;
}

.context_ul_head > span {
  position: absolute;
  color: #999999;
  text-align: center;
  width: 6em;
  top: 0;
  left: 50%;
  margin-left: -3em;
  background-color: #ffffff;
  font-family: "Microsoft YaHei UI", serif;
  font-size: 10px;
}

.context_ul > .context_ul_goodsList {
  height: 480px;
  overflow: hidden;
  position: relative;
}

.context_ul_goodsList > ul {
  padding: 10px 12px 2px 13px;
  position: relative;
}

.context_ul_goodsList > ul > .context_ul_main {
  height: 160px;
}

.context_ul_main > .context_ul_img {
  width: 140px;
  height: 140px;
  margin: 0 auto;
  position: relative;
  background-color: #f8f8f8;
  line-height: 120px;
  overflow: hidden;
}

.context_ul_img > a {
  display: block;
  text-align: center;
  color: #62574e;
  cursor: pointer;
  zoom: 1;
}

.context_ul_img > a > img {
  max-width: 140px;
  max-height: 140px;
}

.context_ul_img > p {
  width: 100%;
  left: 0;
  position: absolute;
  font-family: Arial, serif;
  line-height: 20px;
  background-color: rgba(255, 255, 255, 0.8);
  bottom: 0;
  text-align: center;
  margin: 0;
  font-size: 10px;
}

.context_ul > .context_ul_trigger {
  padding: 0 55px;
  height: 27px;
  margin: 0;
}

.context_ul_trigger > .ul_trigger_up {
  float: left;
  cursor: pointer;
  width: 27px;
  height: 27px;
  background: transparent
    url(../../../assets/images/fore/WebsiteImage/T1Z0VbFEdcXXbcIIcR-80-112.png)
    0 0;
}

.context_ul_trigger > .ul_trigger_down {
  float: right;
  cursor: pointer;
  width: 27px;
  height: 27px;
  background: transparent
    url(../../../assets/images/fore/WebsiteImage/T1Z0VbFEdcXXbcIIcR-80-112.png)
    0 -30px;
}

.ul_trigger_up > a,
.ul_trigger_down > a {
  display: block;
  width: 100%;
  height: 100%;
}

.mainwrap {
  width: 790px;
  margin: 0 auto;
  overflow: hidden;
}

.mainwrap > .J_TabBarBox {
  width: 788px;
  border: 1px solid #dfdfdf;
  z-index: 9999;
}

.J_TabBarBox > ul {
  width: 100%;
  height: 48px;
  position: relative;
  background: #ffffff;
  margin: 0;
}

.J_TabBarBox li {
  float: left;
  line-height: 48px;
  display: block;
  cursor: pointer;
  margin-left: 1px;
  height: 48px;
}

.J_TabBarBox a {
  display: block;
  height: 100%;
  font-size: 10px;
  font-family: "Microsoft YaHei UI", serif;
  padding: 0 20px;
  color: #333333;
  border-right: 1px dotted #d2d2d2;
}

.J_TabBarBox a:hover {
  text-decoration: none;
}

.J_TabBarBox .tab-selected {
  position: relative;
  border-top: 2px solid #ff0036;
  height: 47px;
  margin: -1px 0 0 -1px;
  z-index: 1;
  padding: 0;
}

.J_TabBarBox .tab-selected:after {
  content: " ";
  display: block;
  border: 5px solid transparent;
  border-top-color: #ff0036;
  width: 0;
  height: 0;
  font-family: Arial, serif;
  position: absolute;
  top: -1px;
  left: 50%;
  margin-left: -5px;
}

.J_TabBarBox .tab-selected > a {
  font-weight: 700;
  line-height: 46px;
  color: #ff0036;
  border-left: 1px solid #cfbfb1;
  border-right: 1px solid #cfbfb1;
}

.J_TabBarBox .J_GoodsReviews span {
  font-family: "Microsoft YaHei UI", serif;
  display: inline;
  margin: 0 5px;
  color: #38b;
}

.J_choose {
  width: 100%;
}

.J_img {
  width: 790px;
  position: relative;
  overflow: hidden;
  margin-bottom: 100px;
}

.msg {
  opacity: 0;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  width: 230px;
  height: 70px;
  line-height: 70px;
  color: white;
  border-radius: 5px;
  text-align: center;
  background-color: rgba(0, 0, 0, 0.75);
  font-size: 16px;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
</style>
<!--J_reviews-->
<style scoped>
.J_reviews {
  width: 100%;
  display: block;
  margin-bottom: 10px;
}

.reviews_info {
  width: 100%;
  padding: 16px 7px;
  border-bottom: 1px solid #e3e3e3;
  font-family: tahoma, arial, \5FAE\8F6F\96C5\9ED1, sans-serif;
  font-size: 12px;
  min-height: 68px;
  box-sizing: border-box;
}

.reviews_main {
  display: inline-block;
}

.reviews_content {
  width: 494px;
  padding-right: 30px;
  line-height: 19px;
  overflow: hidden;
  word-wrap: break-word;
  word-break: break-all;
  color: #333333;
}

.reviews_date {
  clear: both;
  color: #cccccc;
}

.reviews_author {
  position: relative;
  right: 30px;
  float: right;
  display: inline-block;
  height: 36px;
  line-height: 36px;
  min-width: 100px;
}
</style>
<!--J_details-->
<style>
.J_details {
  width: 100%;
  display: block;
}

.J_details > .J_details_list {
  margin-bottom: 10px;
  border: 1px solid #e6e6e6;
  border-top: none;
}

.J_details_list > .J_details_list_header {
  display: block;
  padding: 8px 20px 10px;
  border-top: 1px solid #e6e6e6;
  height: 40px;
  line-height: 40px;
  color: #666;
  margin: 0;
  font-family: "Microsoft YaHei UI", serif;
  font-size: 10px;
}

.J_details_list_header > span {
  color: #333333;
  font-family: "Microsoft YaHei UI", serif;
}

.J_details_list > .J_details_list_title {
  margin: 0;
  padding: 5px 20px;
  line-height: 22px;
  color: #999;
  font-weight: 700;
  font-family: "Microsoft YaHei UI", serif;
  font-size: 10px;
}

.J_details_list > .J_details_list_body {
  padding: 0 20px 18px;
  border-top: 1px solid #ffffff;
  margin: 0;
  zoom: 1;
}

.J_details_list_body > li {
  display: inline;
  float: left;
  width: 220px;
  height: 18px;
  overflow: hidden;
  margin: 10px 15px 0 0;
  line-height: 18px;
  vertical-align: top;
  white-space: nowrap;
  text-overflow: ellipsis;
  color: #666;
  font-family: "Microsoft YaHei UI", serif;
  font-size: 10px;
}

.J_details_list_body:after {
  display: block;
  content: "\0020";
  clear: both;
  visibility: hidden;
}
</style>
