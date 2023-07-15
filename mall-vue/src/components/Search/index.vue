<template>
  <div>
    <div class="header">
      <div id="mallLogo">
        <a href="/mall"
          ><img src="../../assets/images/fore/WebsiteImage/tmallLogoD.png" />
          <span class="span_tmallBuyCar">{{ props.title }}</span></a
        >
      </div>
      <div class="shopSearchHeader" v-show="props.isSHowSearch">
        <form action="/product" method="get">
          <div class="shopSearchInput">
            <input
              type="text"
              class="searchInput"
              name="productName"
              placeholder="搜索 商品/品牌/店铺"
              value=""
              maxlength="50"
            />
            <input type="submit" value="搜 索" class="searchBtn" />
          </div>
        </form>
        <ul v-show="props.isShowLi">
          <li><a href="/mall/product?categoryId=1">女装</a></li>

          <li>
            <a href="/mall/product?categoryId=2" style="color: #ff0036">男装</a>
          </li>

          <li><a href="/mall/product?categoryId=3">女鞋</a></li>

          <li>
            <a href="/mall/product?categoryId=4" style="color: #ff0036">美妆</a>
          </li>

          <li><a href="/mall/product?categoryId=5">腕表</a></li>

          <li>
            <a href="/mall/product?categoryId=6" style="color: #ff0036">手机</a>
          </li>

          <li><a href="/mall/product?categoryId=7">母婴玩具</a></li>

          <li>
            <a href="/mall/product?categoryId=8" style="color: #ff0036">零食</a>
          </li>

          <li><a href="/mall/product?categoryId=9">生鲜水果</a></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const props = defineProps<{
  title: string;
  isShowLi: boolean;
  isSHowSearch: boolean;
}>();
</script>

<style lang="scss" scoped>
nav {
  width: 100%;
}

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
  height: 30px;
  overflow: hidden;
}

#mallLogo img {
  display: inline-block;
  width: 190px;
  height: 28px;
  cursor: pointer;
  vertical-align: top;
}

#mallLogo .span_tmallBuyCar {
  font-family: "Microsoft YaHei UI Light", serif;
  display: inline-block;
  height: 28px;
  line-height: 28px;
  font-size: 20px;
  color: #333;
  font-weight: bold;
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
  font-family: Arial, serif;
  position: relative;
  height: 30px;
}

input::-webkit-input-placeholder {
  /* WebKit browsers*/
  color: #666;
  font-weight: normal;
}

input:-moz-placeholder {
  /* Mozilla Firefox 4 to 18*/
  color: #666;
  font-weight: normal;
}

input::-moz-placeholder {
  /* Mozilla Firefox 19+*/
  color: #666;
  font-weight: normal;
}

input:-ms-input-placeholder {
  /* Internet Explorer 10+*/
  color: #666;
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
  font-family: "Microsoft YaHei UI", serif;
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
</style>
