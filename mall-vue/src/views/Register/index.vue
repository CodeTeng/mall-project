<template>
  <div class="register-main">
    <!-- logo标签 -->
    <nav id="baseNavigator">
      <a href="/mall" target="_self">
        <img src="../../assets/images/fore/WebsiteImage/tmallLogoD.png" />
      </a>
    </nav>

    <!-- 注册主体 -->
    <div class="content">
      <div class="steps">
        <div class="steps_main">
          <span class="steps_tsl">填写账号信息</span>
        </div>
      </div>

      <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules">
        <div class="form-list">
          <div class="form-item">
            <label class="form-label form-label-b tsls">设置会员名</label>
          </div>
          <!-- 设置用户名 -->
          <el-form-item class="form-item" label="用户名:" prop="userName">
            <el-input
              name="userName"
              v-model="ruleForm.userName"
              placeholder="请输入用户名"
              class="el-input-item"
            />
          </el-form-item>

          <div class="form-item">
            <label class="form-label form-label-b tsls">设置登录密码</label>
            <label class="form-label tsl">登录时验证，保护账号信息</label>
          </div>

          <!-- 设置密码 -->
          <el-form-item class="form-item" label="输入密码:" prop="password">
            <el-input
              name="password"
              type="password"
              v-model="ruleForm.userPassword"
              placeholder="请设置登录密码"
              show-password
              class="el-input-item"
            />
          </el-form-item>

          <!-- 确认密码 -->
          <el-form-item class="form-item" label="确认密码:" prop="conPassword">
            <el-input
              name="conPassword"
              type="password"
              v-model="ruleForm.confirmPassword"
              placeholder="请再次输入你的密码"
              show-password
              maxlength="20"
              class="el-input-item"
            />
          </el-form-item>

          <div class="form-item">
            <label class="form-label form-label-b tsls">填写基本信息</label>
          </div>

          <!-- 昵称 -->
          <el-form-item class="form-item" label="用户昵称:" prop="nickName">
            <el-input
              name="nickName"
              v-model="ruleForm.userNickName"
              placeholder="请输入昵称"
              maxlength="20"
              type="text"
              class="el-input-item"
            />
          </el-form-item>

          <!-- 性别选取 -->
          <el-form-item class="form-item" label="用户性别：" prop="gender">
            <el-select v-model="ruleForm.userGender" clearable filterable>
              <el-option label="男" :value="0"> </el-option>
              <el-option label="女" :value="1"> </el-option>
            </el-select>
          </el-form-item>

          <!-- 出生日期 -->
          <el-form-item class="form-item" label="出生日期：" prop="birthday">
            <el-date-picker
              v-model="ruleForm.userBirthday"
              type="date"
              placeholder="请选择时间"
              value-format="YYYY-MM-DD"
            />
          </el-form-item>

          <!-- 地址选取 -->
          <el-form-item class="form-item" label="居住地址:" prop="address">
            <el-select
              v-model="proAddress"
              filterable
              placeholder="省"
              class="el-select-item"
              @change="proChange()"
            >
              <el-option
                v-for="item in provinceAddressOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
            <!-- 市 -->
            <el-select
              v-model="ruleForm.userAddress"
              filterable
              placeholder="市"
              class="el-select-item"
              @change="cityChange()"
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
              v-model="ruleForm.userHomePlace"
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

          <!-- 点击注册 -->
          <div class="form-item">
            <input
              type="button"
              id="register_sub"
              class="btns btn-large tsl"
              value="注 册"
              @click="submitForm(ruleFormRef)"
            />
          </div>
        </div>
      </el-form>
    </div>

    <!-- 注册尾部 -->
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
              src="static/images/fore/WebsiteImage/TB1yEqRPXXXXXXPXpXXXXXXXXXX-20-20.png"
            />
            浙公网安备 33010002000120号
          </a>
          <br />
          <b>© 2018 贤趣开发小组 版权所有</b>
        </div>
      </div>
    </div>

    <!-- todo：登陆成功后弹窗 -->
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { register } from "../../api/user";
import { useRouter } from "vue-router";
import { getAddressApi } from "../../api/address";
const router = useRouter();

interface AddressType {
  value: number;
  label: string;
}
interface AddressDTO {
  addressAreaId: number;
  addressName: string;
}

// 省
const provinceAddressOptions = ref<AddressType[]>([
  { value: 110000, label: "北京" },
  { value: 120000, label: "天津" },
  { value: 130000, label: "河北省" },
  { value: 140000, label: "山西省" },
  { value: 150000, label: "内蒙古自治区" },
  { value: 210000, label: "辽宁省" },
  { value: 220000, label: "吉林省" },
  { value: 230000, label: "黑龙江省" },
  { value: 310000, label: "上海市" },
  { value: 320000, label: "江苏省" },
  { value: 330000, label: "浙江省" },
  { value: 340000, label: "安徽省" },
  { value: 350000, label: "福建省" },
  { value: 360000, label: "江西省" },
  { value: 370000, label: "山东省" },
  { value: 410000, label: "河南省" },
  { value: 420000, label: "湖北省" },
  { value: 430000, label: "湖南省" },
  { value: 440000, label: "广东省" },
  { value: 450000, label: "广西壮族自治区" },
  { value: 460000, label: "海南省" },
  { value: 500000, label: "重庆" },
  { value: 510000, label: "四川省" },
  { value: 520000, label: "贵州省" },
  { value: 530000, label: "云南省" },
  { value: 540000, label: "西藏自治区" },
  { value: 610000, label: "陕西省" },
  { value: 620000, label: "甘肃省" },
  { value: 630000, label: "青海省" },
  { value: 640000, label: "宁夏回族自治区" },
  { value: 650000, label: "新疆维吾尔自治区" },
  { value: 710000, label: "台湾省" },
  { value: 810000, label: "香港特别行政区" },
  { value: 820000, label: "澳门特别行政区" },
]);
// 获得市一级列表
const proChange = () => {
  getAddressApi(proAddress.value).then((res) => {
    console.log(res);
    if (res.code === 0) {
      cityAddressOptions.value = res.data;
    } else {
      ElMessage.error("获取市区失败");
    }
  });
};

// 市
const cityAddressOptions = ref<AddressDTO[]>([]);
// 获得区一级列表
const cityChange = () => {
  getAddressApi(ruleForm.value.userAddress).then((res) => {
    console.log(res);
    if (res.code === 0) {
      regionAddressOptions.value = res.data;
    } else {
      ElMessage.error("获取地区失败");
    }
  });
};

// 区
const regionAddressOptions = ref<AddressDTO[]>([]);

// 选择的省
const proAddress = ref<number>();

const ruleFormRef = ref();

const rules = reactive<any>({
  userName: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userPassword: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  confirmPassword: [
    { required: true, message: "输入不能为空", trigger: "blur" },
  ],
  userNickName: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userGender: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userBirthday: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userAddress: [{ required: true, message: "输入不能为空", trigger: "blur" }],
  userHomePlace: [{ required: true, message: "输入不能为空", trigger: "blur" }],
});

const ruleForm = ref<any>({
  userName: "",
  userPassword: "",
  confirmPassword: "",
  userNickName: "",
  userGender: 0,
  userBirthday: "",
  userAddress: undefined,
  userHomePlace: undefined,
});

// 提交表单
const submitForm = async (formEl: any) => {
  if (!formEl) return;
  await formEl.validate((valid: any, fields: any) => {
    if (valid) {
      register(ruleForm.value)
        .then((res) => {
          if (res.code === 0) {
            ElMessage.success("注册成功");
            router.replace("/login");
          } else {
            ElMessage.error(res.message);
          }
        })
        .catch((e) => {
          ElMessage.error(e.message);
        });
    } else {
      console.log("提交出错", fields);
    }
  });
};
</script>

<!-- logo样式 -->
<style scoped>
.el-select-item {
  width: 95px;
  margin-right: 5px;
}
::v-deep .el-form-item__label {
  margin-left: 37px;
}
.el-input-item {
  width: 230px;
  margin-left: 10px;
}
.register-main {
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

<!-- 尾部信息 -->
<style scoped>
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

<!-- 表单主体样式 -->
<style scoped>
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

#mallLogo .span_tmallRegister {
  font-family: "Microsoft YaHei UI Light", serif;
  display: inline-block;
  height: 28px;
  line-height: 28px;
  font-size: 20px;
  color: #333;
  font-weight: bold;
  vertical-align: top;
}

.content {
  color: #000;
  position: relative;
  width: 100%;
  min-width: 1190px;
  font-family: Arial, serif;
  margin: auto auto 40px;
}
.form-list {
  width: 720px;
  margin: 0 auto;
  font-size: 15px;
  padding: 20px 0;
}
.form-item {
  padding: 10px 0 10px 100px;
  line-height: 10px;
  zoom: 1;
  clear: both;
}
.form-label {
  min-width: 100px;
  line-height: 30px;
  text-align: right;
  font-weight: normal;
  margin-left: 10px;
}

.steps {
  border-bottom: 2px solid #e6e6e6;
  position: relative;
}

.steps_main {
  width: 1190px;
  margin: auto auto -1px;
}
.tsls {
  font-weight: bolder;
}

.steps_tsl {
  color: #3e3e3e;
  font-size: 16px;
  font-weight: 700;
  display: block;
  border-bottom: 3px solid #ff0036;
  line-height: 50px;
  height: 45px;
  width: 200px;
  text-align: center;
}
.err-input {
  border-color: #cccccc;
}
.form-text {
  border: 1px solid #ccc;
  width: 202px;
  height: 17px;
  line-height: 17px;
  padding: 9px;
  font-size: 14px;
  _margin-left: -3px;
}

.form-text:focus {
  transition: border-color 0.5s;
}
.form_span {
  display: none;
  padding-left: 15px;
  font-size: 12px;
}
#form_radion {
  line-height: 10px;
  margin: 12px 0 0 10px;
}
#form_radions {
  line-height: 10px;
  margin: 12px 0 0 10px;
}
/*修改bootstrap-select下拉框样式*/
.bootstrap-select:not([class*="col-"]):not([class*="form-control"]):not(
    .input-group-btn
  ) {
  width: 120px;
  margin: 0 20px 0 0;
  position: relative;
  bottom: 1px;
}

.btn.dropdown-toggle.btn-default {
  font-size: 12px;
  color: #333;
  border-color: #ccc;
  padding: 9px;
  box-sizing: content-box;
  border-radius: 0;
}

.btn-group-vertical > .btn,
.btn-group > .btn {
  height: 17px;
}

.bootstrap-select.btn-group .dropdown-toggle .filter-option {
  line-height: 22px;
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
.btn-large {
  min-width: 140px;
}
.btns {
  height: 36px;
  padding: 0 20px;
  color: #fff;
  font-weight: 700;
  font-size: 16px;
  text-align: center;
  background: #ff0036;
  border: 0;
  border-radius: 3px;
  margin-left: 110px;
  margin-top: 20px;
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
