<template>
  <div class="header-main">
    <div id="nav">
      <div class="nav_main">
        <p class="container_login" v-show="!isOnLogin">
          <em>嗨！欢迎来到Mall商城</em>
          <router-link to="/login">登录</router-link>
          <router-link to="/register">注册</router-link>
        </p>

        <p class="container_login" v-show="isOnLogin">
          <em>Hi，</em>
          <router-link to="/userInfo" class="userName" target="_blank">{{
            getUserName()
          }}</router-link>
          <a @click="userStore.logout()">退出</a>
        </p>

        <!-- 右侧主体 -->
        <ul class="quick_li">
          <li class="quick_li_MyTaobao">
            <div class="sn_menu">
              <router-link to="/userInfo">我的Mall</router-link>
              <!-- <div class="quick_menu">
                <a href="/mall/order/0/10">已买到的宝贝</a>
                <a href="#">已卖出的宝贝</a>
              </div> -->
            </div>
          </li>
          <li class="quick_li_MyTaobao">
            <div class="sn_menu">
              <router-link to="/order">我的订单</router-link>
            </div>
          </li>
          <li class="quick_li_cart">
            <img src="../../../assets/images/fore/WebsiteImage/buyCar.png" />
            <router-link to="/cart">购物车</router-link>
          </li>
          <!-- <li class="quick_li_menuItem">
            <div class="sn_menu">
              <a href="#">收藏夹<b></b></a>
              <div class="quick_menu">
                <a href="#">收藏的宝贝</a>
                <a href="#">收藏的店铺</a>
              </div>
            </div>
          </li> -->
          <li class="quick_li_separator"></li>
          <li class="quick_li_mobile">
            <img src="../../../assets/images/fore/WebsiteImage/mobile.png" />
            <router-link to="#" title="Mall无线">手机版</router-link>
            <div class="quick_qrcode">
              <img src="../../../assets/images/fore/WebsiteImage/qrcode.png" />
              <b></b>
            </div>
          </li>
          <li class="quick_home"><router-link to="/mall">Mall商城</router-link></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { isLogin } from "../../../utils/auth";
import { useUserStore } from "../../../store/modules/user/index";
import { getUserName } from "../../../utils/auth";

const userStore = useUserStore();

const isOnLogin = isLogin();
</script>

<style lang="scss" scoped>
.header-main {
  width: 100%;
  height: 60px;
}

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

.nav_main > .container_login {
  position: absolute;
  top: 0;
  left: 0;
  line-height: 32px;
  color: #999;
  margin: 0;
}

.container_login > em {
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
