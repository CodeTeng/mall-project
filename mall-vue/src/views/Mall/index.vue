<template>
  <div class="mall-main">
    <!-- 搜索头部 -->
    <div class="header">
      <img src="../../assets/images/fore/WebsiteImage/HomeLogoB.png" />
      <div class="mallSearch">
        <form action="/product" method="get">
          <div class="mallSearch-input">
            <input
              class="header_search_input"
              type="text"
              name="productName"
              placeholder="搜索 商品/品牌/店铺"
              maxlength="50"
            />
            <input class="header_search_button" type="submit" value="搜索" />
          </div>
        </form>
        <ul>
          <li
            v-for="(item, index) in categorySearchList"
            :key="item.categoryId"
          >
            <a
              :href="'/product?categoryId=' + item.categoryId"
              :style="index % 2 === 0 ? 'color: #FF0036' : ''"
              >{{ item.categoryName }}</a
            >
          </li>
        </ul>
      </div>
    </div>

    <!--  商品分类  -->
    <div class="home_nav">
      <div class="home_nav_title">
        <img src="../../assets/images/fore/WebsiteImage/header_nav_title.png" />
        <span>商品分类</span>
      </div>
      <a href="http://vip.mall.com/" target="_blank">Mall会员</a>
      <a href="https://miao.mall.com/" target="_blank">Mall生鲜</a>
      <a href="https://3c.mall.com/" target="_blank">智能家居</a>
      <a href="http://yao.mall.com/" target="_blank">医药馆</a>
      <a href="http://yao.mall.com/" target="_blank">医药馆</a>
      <a href="http://wt.mall.com/" target="_blank">营业厅</a>
      <a href="https://pin.mall.com/" target="_blank">Mall拼团</a>
      <a href="https://suning.mall.com/" target="_blank">苏宁易购</a>
      <a href="https://alitrip.mall.com/" target="_blank">Mall旅行</a>
    </div>

    <!--  轮播主体  -->
    <div @mouseleave="categoryLeave()">
      <!-- 轮播图 -->
      <div class="banner">
        <img
          src="../../assets/images/fore/WebsiteImage/banner/42.jpg"
          name="42"
          id="banner1"
          :style="bannerIndex===1?'display: block':''"
        />

        <img
          src="../../assets/images/fore/WebsiteImage/banner/43.jpg"
          name="43"
          id="banner2"
          :style="bannerIndex===2?'display: block':''"

        />

        <img
          src="../../assets/images/fore/WebsiteImage/banner/44.jpg"
          name="44"
          id="banner3"
          :style="bannerIndex===3?'display: block':''"

        />

        <img
          src="../../assets/images/fore/WebsiteImage/banner/45.jpg"
          name="45"
          id="banner4"
          :style="bannerIndex===4?'display: block':''"

        />

        <img
          src="../../assets/images/fore/WebsiteImage/banner/46.jpg"
          name="46"
          id="banner5"
          :style="bannerIndex===5?'display: block':''"

        />

        <img
          src="../../assets/images/fore/WebsiteImage/banner/47.jpg"
          name="47"
          id="banner6"
          :style="bannerIndex===6?'display: block':''"

        />
      </div>

      <!-- 侧边分类信息   -->
      <div class="banner_main">
        <ul class="banner_nav">
          <li v-for="item in categorySideList" :key="item.categoryId">
            <img :src="bindWebsite('small/' + item.categoryId + '.png')" />
            <a
              href="/product?categoryId=1"
              @mouseenter="categoryEnter(item.categoryId)"
              >{{ item.categoryName }}</a
            >
            <div class="banner_div" :name="item.categoryName">
              <span
                v-for="(proName, index) in CategoryProductNameList"
                :key="proName.productId"
              >
                <router-link
                  :to="'/product/info/' + proName.productId"
                  :style="
                    proName.productIsEnabled === 2
                      ? 'color: blue'
                      : 'color: black'
                  "
                  style="margin-left: 10px"
                >
                  {{ proName.productTitle }}
                </router-link>
                <nav
                  v-show="index % 4 === 0"
                  class="category-product-name-nav"
                ></nav>
              </span>
            </div>
          </li>
        </ul>

        <!--  轮播图下方选择条  -->
        <ul class="banner_slider">
          <li id="slider_1" :style="bannerIndex ===1?'background: rgba(255, 255, 255, 0.4)':''" @mouseover="bannerChange(1)"></li>
          <li id="slider_2" :style="bannerIndex ===2?'background: rgba(255, 255, 255, 0.4)':''" @mouseover="bannerChange(2)"></li>
          <li id="slider_3" :style="bannerIndex ===3?'background: rgba(255, 255, 255, 0.4)':''" @mouseover="bannerChange(3)"></li>
          <li id="slider_4" :style="bannerIndex ===4?'background: rgba(255, 255, 255, 0.4)':''" @mouseover="bannerChange(4)"></li>
          <li id="slider_5" :style="bannerIndex ===5?'background: rgba(255, 255, 255, 0.4)':''" @mouseover="bannerChange(5)"></li>
          <li id="slider_6" :style="bannerIndex ===6?'background: rgba(255, 255, 255, 0.4)':''" @mouseover="bannerChange(6)"></li>
        </ul>
        <a href="#"></a>
      </div>
    </div>

    <!--  分类得商品信息  -->
    <div class="banner_do">
      <div class="banner_goods">
        <!--    分类下的商品信息列表    -->
        <div
          class="banner_goods_type"
          v-for="item in categoryHomeMainList"
          :key="item.categoryId"
        >
          <div class="banner_goods_title">
            <span></span>
            <p>{{ item.categoryName }}</p>
          </div>
          <router-link :to="'/product?categoryId=' + item.categoryId"
            ><img
              class="banner_goods_show"
              :src="bindWebsite('show/' + item.categoryId + '.jpg')"
          /></router-link>

          <!-- 分类下的商品列表 -->
          <div class="banner_goods_items">
            <div
              class="banner_goods_item"
              v-for="product in item.categoryHomeProductVOList"
              :key="product.productId"
            >
              <router-link
                :to="'product/info/' + product.productId"
                class="goods_link"
              ></router-link>
              <img :src="bindImg(product.singleProductImageList[0])" />
              <router-link
                :to="'product/info/' + product.productId"
                class="goods_name"
                >{{ product.productName }}</router-link
              >
              <span class="goods_price">￥{{ product.productSalePrice }}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="endDiv"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {
  categoryHomeMainType,
  categoryProductNameType,
  categoryType,
} from "../../api/category/type";
import {
  getCategoryListApi,
  getHomeProductApi,
  getProductListByCategoryIdApi,
} from "../../api/category";
import { bindWebsite, bindImg } from "../../utils";

// 轮播图
const bannerIndex = ref<number>(1);
const bannerChange = (index:number) => {
  bannerIndex.value = index;
}
const bannerTimeoutShow = ()=>{
  bannerIndex.value = (bannerIndex.value+1)%6+1;
  setTimeout(()=>{
    bannerTimeoutShow()
  },3000)
}
bannerTimeoutShow()



const isShowBannerDiv = ref<string>("none");

const categoryHomeMainList = ref<categoryHomeMainType[]>();

// 获得主体列表
const doGetCategoryHomeMainList = () => {
  getHomeProductApi().then((res) => {
    console.log(res);
    if (res.code === 0) {
      categoryHomeMainList.value = res.data;
    } else {
      ElMessage.error("获得分类下商品列表失败" + res.message);
    }
  });
};

// 搜索头部分类列表
const categorySearchList = ref<categoryType>();

// 搜索左侧分类表
const categorySideList = ref<categoryType>();

// 分类下的商品名列表（鼠标划过侧边分类显示）
const CategoryProductNameList = ref<categoryProductNameType[]>();

// 进入分类
const categoryEnter = (id: number) => {
  isShowBannerDiv.value = "block";
  getProductListByCategoryIdApi(id).then((res) => {
    if (res.code === 0) {
      CategoryProductNameList.value = res.data;
    } else {
      console.log("获取分类下的商品信息失败");
    }
  });
  console.log(id);
};

// 离开分类
const categoryLeave = () => {
  isShowBannerDiv.value = "none";
};

// 获得分类列表
const doGetCategory = () => {
  getCategoryListApi(1).then((res) => {
    console.log(res);
    if (res.code === 0) {
      categorySearchList.value = res.data;
    } else {
      ElMessage.error(res.message);
    }
  });
  getCategoryListApi(2).then((res) => {
    if (res.code === 0) {
      categorySideList.value = res.data;
    } else {
      ElMessage.error(res.message);
    }
  });
};

onMounted(() => {
  // 获得列表
  doGetCategoryHomeMainList();
  // 获得分类
  doGetCategory();
});
</script>

<style scoped>
.category-product-name-nav {
  margin-top: 5px;
  margin-bottom: 5px;
  border-bottom-color: rgba(62, 62, 62, 0.13);
}
</style>

<!--主体样式-->
<style scoped>
nav {
  border-bottom: 2px solid #ff0036;
}

.header {
  width: 1270px;
  height: 130px;
  margin: auto;
}

.header > img {
  float: left;
  width: 240px;
  height: 130px;
}

.header > .mallSearch {
  width: 625px;
  padding-top: 38px;
  float: left;
  padding-left: 110px;
}

.mallSearch > form {
  border: 2px solid #ff0036;
  border-right: 0;
}

.mallSearch .mallSearch-input {
  height: 36px;
  clear: both;
}

.mallSearch-input > .header_search_input {
  float: left;
  height: 36px;
  width: 491px;
  font: 14px "宋体";
  box-sizing: border-box;
  outline: none;
  color: #000;
  margin: 0;
  padding: 5px 3px 5px 5px;
  vertical-align: middle;
  border: 0;
}

.mallSearch-input > .header_search_button {
  float: right;
  width: 132px;
  height: 36px;
  font-size: 18px;
  font-weight: 700;
  letter-spacing: 5px;
  background-color: #ff0036;
  cursor: pointer;
  color: #ffffff;
  border: 0;
}

.mallSearch > ul {
  height: 24px;
  font-size: 14px;
  padding: 4px 0 0;
  margin-left: -10px;
  overflow: hidden;
  width: 100%;
  list-style: none;
}

.mallSearch > ul > li {
  font-family: "宋体", sans-serif;
  font-size: 14px;
  display: inline-block;
  line-height: 1.1;
  padding: 0 8px 0 12px;
}

.mallSearch > ul > li > a {
  color: #999999;
}

.mallSearch li + li {
  border-left: 1px solid #cccccc;
}

.home_nav {
  width: 1230px;
  height: 36px;
  margin: auto;
}

.home_nav > .home_nav_title {
  background: #ff0036;
  width: 200px;
  float: left;
  height: 36px;
}

.home_nav_title > img {
  vertical-align: middle;
  margin-left: 14px;
  margin-top: -5px;
}

.home_nav_title > span {
  display: inline-block;
  line-height: 38px;
  margin-left: 8px;
  font-size: 16px;
  font-weight: 700;
  color: #ffffff;
}

.home_nav > a {
  height: 35px;
  cursor: pointer;
  float: left;
  padding: 0 8px;
  font-weight: 500;
  font-size: 16px;
  display: block;
  color: #333333;
  letter-spacing: normal;
  position: relative;
  line-height: 37px;
}

.home_nav > a:hover {
  color: #ff0036;
  text-decoration: none;
}

.home_nav > a > img {
  width: 100px;
  height: 30px;
  margin-top: 3px;
  display: block;
}

.banner {
  width: 100%;
  height: 500px;
  position: absolute;
  background-color: #0f1322;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.banner > img {
  cursor: pointer;
  position: absolute;
  margin: auto;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  display: none;
}

.banner_main {
  width: 1230px;
  margin: auto;
  position: relative;
}

.banner_main > .banner_nav {
  width: 200px;
  height: 500px;
  background-color: rgba(0, 0, 0, 0.55);
  margin: 0;
}

.banner_nav > li {
  font-family: "Microsoft YaHei UI", serif;
  display: block;
  height: 31.2px;
  line-height: 31.2px;
  font-size: 14px;
}

.banner_nav > li:hover {
  background-color: #ffffff;
}

.banner_nav > li > img {
  width: 16px;
  margin: 0 14px;
  position: relative;
  bottom: 3px;
}

.banner_nav > li > a {
  cursor: pointer;
  color: #ffffff;
}

.banner_nav > li > a:hover {
  text-decoration: none;
}

.banner_nav > li > .banner_div {
  position: absolute;
  left: 200px;
  top: 0;
  width: 650px;
  height: 470px;
  overflow: hidden;
  background-color: #ffffff;
  display: v-bind(isShowBannerDiv);
  padding: 15px 100px;
  z-index: 999;
}

.banner_div > .hot_word {
  padding: 5px;
  border-bottom: 1px dashed rgba(0, 0, 0, 0.1);
}

.hot_word > a {
  display: inline-block;
  font-size: 14px;
  margin-right: 13px;
  color: #666;
}

.hot_word > a:hover {
  color: #6347ed;
  text-decoration: none;
}

.banner_main > .banner_slider {
  margin: 470px auto 0;
  width: 210px;
  height: 10px;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  list-style: none;
}

.banner_slider > li {
  float: left;
  margin: 0 5px;
  cursor: pointer;
  background: rgba(0, 0, 0, 0.4);
  width: 25px;
  height: 5px;
}

.banner_main > a {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 200px;
  right: 0;
  display: inline-block;
  width: 1000px;
  height: 450px;
  opacity: 0;
}

.banner_do {
  width: 100%;
  padding-top: 35px;
  background-color: #f5f5f5;
  padding-bottom: 10px;
}

.banner_do > .banner_goods {
  width: 1230px;
  margin: auto;
  position: relative;
}

.banner_goods > .banner_goods_type {
  margin-top: 20px;
  width: 100%;
  overflow: hidden;
}

.banner_goods_type > .banner_goods_title {
  padding: 10px 0;
}

.banner_goods_title > span {
  display: inline-block;
  width: 5px;
  height: 18px;
  line-height: 38px;
  background-color: rgb(99, 71, 237);
}

.banner_goods_title > p {
  display: inline-block;
  position: relative;
  bottom: 2px;
  padding-left: 10px;
  font-family: "Microsoft YaHei", SimSun, "\5b8b\4f53", sans-serif;
  font-size: 18px;
  color: #000000;
  margin: 0;
}

.banner_goods_type .banner_goods_show {
  float: left;
  position: relative;
  width: 235px;
  height: 618px;
  display: block;
  cursor: pointer;
}

.banner_goods_type > .banner_goods_items {
  height: 618px;
  overflow: hidden;
}

.banner_goods_items > .banner_goods_item {
  width: 235px;
  height: 301px;
  margin-left: 13px;
  text-align: center;
  float: left;
  display: block;
  border-bottom: 15px solid #f5f5f5;
  position: relative;
  background-color: #ffffff;
  font-family: "Microsoft YaHei UI", serif;
}

.banner_goods_item > img {
  padding-top: 20px;
  width: 185px;
  height: 185px;
}

.banner_goods_item > .goods_link {
  position: absolute;
  display: block;
  width: 234px;
  height: 300px;
  border: 1px solid #ffffff;
}

.banner_goods_item > .goods_link:hover {
  border: 1px solid #ff0036;
}

.banner_goods_item > .goods_name {
  display: block;
  width: 135px;
  height: 40px;
  font-size: 14px;
  color: #333;
  line-height: 20px;
  overflow: hidden;
  margin: 8px auto;
}

.banner_goods_item > .goods_price {
  display: block;
  font-size: 18px;
  color: #ff0036;
  line-height: 18px;
  margin: 10px auto;
}

.banner_goods_item img {
  width: 185px;
  height: 185px;
}

.banner_do > .endDiv {
  background: url(../../assets/images/fore/WebsiteImage/TB1wQDAPXXXXXXgaFXXXXXXXXXX-62-35.png);
  position: relative;
  text-align: center;
  width: 80px;
  height: 45px;
  margin: 20px auto 0;
  background-size: cover;
}
</style>
