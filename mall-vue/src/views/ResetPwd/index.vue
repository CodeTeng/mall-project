<template>
  <div class="login-mian">
    <!-- logo标签 -->
    <nav id="baseNavigator">
      <a href="/mall" target="_self">
        <img src="../../assets/images/fore/WebsiteImage/tmallLogoD.png" />
      </a>
    </nav>

    <!-- 登录主体 -->
    <div class="content">
      <div class="contentMain"></div>
      <div class="loginDiv">
        <!--   密码登录     -->
        <div class="pwdLogin">
          <span class="loginTitle">找回密码</span>
          <el-form class="loginForm">
            <el-form-item class="loginInputDiv">
              <el-input
                  :prefix-icon="User"
                  type="text"
                  v-model="resetForm.userName"
                  placeholder="用户名"
              />
            </el-form-item>
            <el-form-item class="loginInputDiv">
              <el-input
                  :prefix-icon="PhoneFilled"
                  type="text"
                  placeholder="手机号"
                  v-model="resetForm.mobile"
              />
            </el-form-item>
          </el-form>
          <!-- 登录按钮 -->
          <button class="loginButton" @click="OnSubmit()">开始找回</button>
          <div class="loginLinks">
            <router-link to="/login">返回登录</router-link>
            <router-link to="/register">免费注册</router-link>
          </div>
          <div class="error_message">
            <p id="error_message_p"></p>
          </div>
        </div>

      </div>
    </div>

    <!-- 登录footer -->
    <div class="footer-copyright">
      <div class="footer">
        <p>
          <a
              href="http://pages.mall.com/wow/seller/act/zhaoshangproduce"
              target="_blank"
          >关于我们</a
          >
          <a
              href="http://service.mall.com/support/mall/tmallHelp.htm"
              target="_blank"
          >帮助中心</a
          >
          <a href="http://open.taobao.com" target="_blank">开放平台</a>
          <a href="http://job.alibaba.com/zhaopin/index.htm" target="_blank"
          >诚聘英才</a
          >
          <a href="http://consumerservice.taobao.com/contact-us" target="_blank"
          >联系我们</a
          >
          <a href="http://xtao.mall.com?tracelog=tmallfoot" target="_blank"
          >网站合作</a
          >
          <a
              href="http://terms.alicdn.com/legal-agreement/terms/suit_bu1_taobao/suit_bu1_taobao201703241622_61002.html"
              target="_blank"
          >法律声明及隐私权政策</a
          >
          <a href="http://ipp.alibabagroup.com/" target="_blank">知识产权</a>
          <a
              href="http://jubao.alibaba.com/index.html?site=mall"
              target="_blank"
          >廉正举报</a
          >
          <a
              href="http://rule.mall.com/trulecycle.htm"
              target="_blank"
              class="noneStyle"
          >规则意见征集</a
          >
        </p>
        <p class="footertwo">
          <a href="http://www.alibabagroup.com/cn/global/home" target="_blank"
          >阿里巴巴集团</a
          >
          <a href="http://www.taobao.com" target="_blank">Mall商城</a>
          <a href="http://ju.taobao.com/" target="_blank">聚划算</a>
          <a href="http://www.aliexpress.com/" target="_blank">全球速卖通</a>
          <a href="http://www.alibaba.com/" target="_blank"
          >阿里巴巴国际交易市场</a
          >
          <a href="http://www.1688.com/" target="_blank">1688</a>
          <a href="http://www.alimama.com/" target="_blank">阿里妈妈</a>
          <a href="http://www.fliggy.com/" target="_blank">飞猪</a>
          <a href="http://www.aliyun.com/" target="_blank">阿里云计算</a>
          <a href="http://www.yunos.com/" target="_blank">YunOS</a>
          <a href="http://mall.cn/" target="_blank">Mall通信</a>
          <a href="http://www.net.cn/" target="_blank">万网</a>
          <a href="http://www.autonavi.com/" target="_blank">高德</a>
          <a href="http://www.uc.cn/" target="_blank">UC</a>
          <a href="http://www.umeng.com/" target="_blank">友盟</a>
          <a href="http://www.xiami.com/" target="_blank">虾米</a>
          <a href="http://www.mallnet.com" target="_blank">Mall星球</a>
          <a href="http://www.laiwang.com/" target="_blank">来往</a>
          <a
              href="http://www.dingtalk.com/?lwfrom=20150205115110773"
              target="_blank"
          >钉钉</a
          >
          <a href="https://www.alipay.com" target="_blank">支付宝</a>
        </p>
        <div class="copyright">
          增值电信业务经营许可证：
          <a
              href="http://www.miibeian.gov.cn/state/outPortal/loginPortal.action"
              target="_blank"
          >浙B2-20110446</a
          >
          网络文化经营许可证：
          <a
              href="http://sq.ccm.gov.cn/ccnt/sczr/service/business/emark/toDetail/CB964ABC7B904B7BA3472DDF700A2D6D"
              target="_blank"
          >浙网文[2015]0295-065号</a
          >
          <a href="http://jb.ccm.gov.cn/" target="_blank">12318举报</a>
          <br />
          互联网药品信息服务资质证书编号：
          <a
              href="http://img.alicdn.com/tps/TB1vi6vPVXXXXbVXVXXXXXXXXXX-927-653.png"
              target="_blank"
          >浙-（经营性）-2017-0005</a
          >
          <a
              href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=33010002000120"
              target="_blank"
          >
            <img
                src="../../assets/images/fore/WebsiteImage/TB1yEqRPXXXXXXPXpXXXXXXXXXX-20-20.png"
            />
            浙公网安备 33010002000120号
          </a>
          <br />
          <b>© 2018 贤趣开发小组 版权所有</b>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PhoneFilled, User } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import {resetFormVO} from "../../api/user/types";
import {findPassword} from "../../api/user";
import {ElMessageBox} from "element-plus";
const router = useRouter();



const resetForm = ref<resetFormVO>({
  mobile: "",
  userName: ""
});



// 提交
const OnSubmit = () => {
  findPassword(resetForm.value).then(res=>{
    if(res.code===0){
      ElMessageBox({
        title: '信息提示',
        message: h('p', null, [
          h('span', null, '信息:'),
          h('i', { style: 'color: teal' }, res.data)
        ]),
      })
    }else{
      ElMessage.error(res.message)
    }
  })
};
</script>

<!--整体样式-->
<style lang="scss" scoped>
.login-mian {
  width: 100%;
}

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
</style>

<!--登录尾部样式-->
<style lang="scss" scoped>
/*尾部样式*/
.footer-copyright {
  border-top: 2px solid #fff;
  background: #fff;
  text-align: left;
  min-height: 177px;
}

.footer-copyright > .footer {
  overflow: hidden;
  width: 1190px;
  margin: 0 auto;
  font: 12px/1.5 "Microsoft YaHei UI", arial, "\5b8b\4f53";
}

.footer > p {
  color: #000;
  padding: 5px 0;
  line-height: 25px;
  margin: 0;
}

.footer a {
  color: #000;
  margin-right: 3px;
  text-decoration: none;
}

.footer a:hover {
  text-decoration: underline;
}

.noneStyle {
  display: none;
}

.copyright {
  width: 990px;
  color: #a4a4a4;
  line-height: 25px;
}

.copyright > a {
  color: #999;
  margin-right: 65px;
}

.copyright img {
  position: relative;
  bottom: 2px;
  vertical-align: middle;
  border: 0;
}

.copyright > b {
  font-weight: 400;
  color: #686868;
}

.footertwo > a {
  margin-right: 10px;
}
</style>

<!--登录主体样式-->
<style lang="scss" scoped>
.content {
  position: relative;
  width: 100%;
  min-width: 1190px;
  margin-bottom: 40px;
  background-color: rgb(241, 220, 165);
  font-family: "Microsoft YaHei UI", serif;
}

.content > .contentMain {
  position: relative;
  width: 1190px;
  height: 600px;
  margin: auto;
  background-image: url(../../assets/images/fore/WebsiteImage/loginbz3.jpg);
}

.loginDiv {
  width: 350px;
  height: 400px;
  position: absolute;
  top: 91px;
  right: 425px;
  background: #ffffff;
  color: #404040;
}

.loginSwitch {
  position: absolute;
  width: 52px;
  height: 52px;
  right: 5px;
  top: 12px;
  background-image: url(../../assets/images/fore/WebsiteImage/scan_icon.png);
  cursor: pointer;
  -webkit-background-size: cover;
  background-size: cover;
}

.loginSwitch_two {
  position: absolute;
  width: 52px;
  height: 52px;
  right: 5px;
  top: 12px;
  background-image: url(../../assets/images/fore/WebsiteImage/pc_icon.png);
  cursor: pointer;
  -webkit-background-size: cover;
  background-size: cover;
}

.loginDiv > .loginMessage {
  position: absolute;
  top: 10px;
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
  border-width: 6px 0 6px 6px;
  border-style: solid;
  border-color: rgba(255, 255, 255, 0);
  border-left-color: #f3d995;
}

.poptip-arrow > span {
  border-width: 6px 0 6px 6px;
  position: absolute;
  width: 0;
  height: 0;
  top: 0;
  left: 0;
  border-style: solid;
  border-color: rgba(255, 255, 255, 0);
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
  // cursor: pointer;
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
</style>
