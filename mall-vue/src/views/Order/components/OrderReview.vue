<template>
  <div>
    <Search title="" :isShowLi="false" :isSHowSearch="true" />
    <div class="content">
      <div class="details_box">
        <div class="db-showpanel">
          <a href="/mall/product/47" target="_blank"
            ><img :src="bindImg(productDetails?.singleProductImageList[0])"
          /></a>
        </div>
        <div class="db-icbu">
          <ol class="ui-form-bd">
            <li class="ui-form-row">
              <h3>{{ productDetails?.productName }}</h3>
            </li>
            <li class="ui-form-row superstar-price">
              <label class="ui-form-label">价格</label>
              <div class="ui-form_right">
                <em>{{ productDetails?.productSalePrice }}</em>
                <span>元</span>
              </div>
            </li>

            <!-- <li class="ui-from-row">
              <label class="ui-form-label">配送</label>
              <div class="ui-form_right">
                <span class="ul_right_special">快递：0.00</span>
              </div>
            </li> -->
          </ol>
        </div>
        <div class="banner-totalevolute">
          <div class="tv-leftbox">
            <div class="tv-lb-head"></div>
            <div class="tv-lb-content">
              <span>累计评价</span>
              <em class="superstar-ratetotal">{{
                  productDetails?.reviewCount
              }}</em>
            </div>
            <div class="tv-lb-bottom"></div>
          </div>
          <div class="tv-rightbox">
            <div class="tv-rb-cover"></div>
            <div class="tv-rb-bottom"></div>
          </div>
        </div>
        <div class="rate-compose">
          <div id="review_form">
            <div class="compose-main">
              <div class="compose-header">
                <span>其他买家，需要你的建议哦！</span>
              </div>
              <div class="compose-order">
                <div class="J_rateInputArea">评价商品</div>
                <div class="text-input-box">
                  <textarea
                    id="text-review"
                    name="reviewContent"
                    v-model="reviewValue.reviewContent"
                  ></textarea>
                </div>
              </div>
            </div>
            <div class="compose-submit">
              <input type="submit" value="提交评价" @click="submitReview" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { getDetailedProductApi } from "../../../api/product";
import { productDetailsType } from "../../../api/product/type";
import { reviewProductApi } from "../../../api/review";
import { reviewProductType } from "../../../api/review/type";
import { bindImg } from "../../../utils";
const route = useRoute();
const router = useRouter();
// 评价内容

const reviewValue = ref<reviewProductType>({
  reviewContent: "",
  reviewOrderItemId: 0,
  reviewProductId: 0,
});
const productId = ref<any>(route.params?.productId);
const orderItemId = ref<any>(route.params?.orderItemId);

const productDetails = ref<productDetailsType>();
// 提交评论
const submitReview = () => {
  reviewValue.value.reviewOrderItemId = orderItemId.value
  reviewValue.value.reviewProductId = productId.value

  reviewProductApi(reviewValue.value).then((res) => {
    if (res.code === 0) {
      ElMessage.success("评论成功");
      router.go(-1);
    } else {
      ElMessage.error("评论失败");
    }
  });
};



onMounted(() => {
  console.log(orderItemId)
  console.log(productId)

  getDetailedProductApi(productId.value).then((res) => {
    if (res.code === 0) {
      productDetails.value = res.data;
    } else {
      ElMessage.error("加载商品数据失败");
    }
  });
});
</script>

<style lang="scss" scoped>
.content {
  width: 1230px;
  margin: auto;
  min-height: 800px;
  padding-bottom: 60px;
}

.content > .details_box {
  position: relative;
  width: 990px;
  margin: 20px auto;
  min-height: 300px;
}

.details_box > .db-showpanel {
  width: 462px;
  height: 462px;
  float: left;
  border: 1px solid #e7e7e7;
}

.db-showpanel > a {
  display: table-cell;
  vertical-align: middle;
  width: 462px;
  height: 462px;
  text-align: center;
}

.db-showpanel > a > img {
  max-height: 460px;
  max-width: 460px;
  margin: 1px;
  border: none;
}

.details_box > .db-icbu {
  overflow: hidden;
  width: 526px;
  height: 462px;
  margin-left: 463px;
  border-top: 1px solid #e7e7e7;
  margin-bottom: 17px;
}

.db-icbu > .ui-form-bd {
  display: block;
  padding: 19px;
  min-height: 160px;
  margin-top: 20px;
}

.ui-form-bd .ui-form-row {
  margin-bottom: 15px;
  overflow: hidden;
}

.ui-form-row > h3 {
  padding: 3px 0;
  color: #000;
  vertical-align: top;
  font: 16px/1.5 tahoma, arial, "\5b8b\4f53";
  font-weight: bold;
}

.ui-form-row .superstar-price {
  height: 27px;
  line-height: 27px;
}

.ui-form-label {
  display: inline-block;
  float: left;
  color: #999;
  font: 12px/1.5 tahoma, arial, "\5b8b\4f53";
  font-weight: normal;
  line-height: 27px;
}

.ui-form_right {
  margin-left: 70px;
  color: #666;
  height: 27px;
  line-height: 27px;
}

.superstar-price > .ui-form_right > em {
  font-style: normal;
  font-weight: bolder;
  color: #c00;
  vertical-align: top;
  font-size: 27px;
  line-height: 27px;
}

.superstar-price > .ui-form_right > span {
  line-height: 27px;
}

.ui-form_right > .ul_right_special {
  font-size: 12px;
}

.banner-totalevolute {
  position: absolute;
  left: 0;
  height: 38px;
  margin-bottom: 5px;
  background: #f6f3f1;
}

.banner-totalevolute > .tv-leftbox {
  display: inline-block;
  overflow: hidden;
  border-bottom: 1px solid #d5d4d4;
  height: 38px;
  width: 181px;
  float: left;
}

.tv-leftbox > .tv-lb-head {
  height: 5px;
  background: #b41a1a;
}

.tv-leftbox > .tv-lb-content {
  height: 28px;
  font-size: 15px;
  line-height: 28px;
  text-align: center;
  font-weight: 700;
  background: #f6f5f1;
  border-left: 1px solid #d5d4d4;
  border-right: 1px solid #d5d4d4;
  font-family: Arial, serif;
}

.tv-lb-content > span {
  color: #363535;
  font-size: 15px;
  line-height: 28px;
  text-align: center;
  font-weight: 700;
}

.tv-lb-content > .superstar-ratetotal {
  color: #284ca5;
  font-weight: 700;
  font-style: normal;
  font-size: 15px;
  line-height: 28px;
  text-align: center;
}

.banner-totalevolute > .tv-rightbox {
  display: inline-block;
  overflow: hidden;
  border-bottom: 1px solid #d5d4d4;
  height: 38px;
  width: 809px;
  float: right;
}

.tv-rightbox > .tv-rb-cover {
  height: 32px;
  background: #fff;
}

.tv-lb-bottom {
  height: 5px;
  background: #f6f5f1;
  border-left: 1px solid #d5d4d4;
}

.rate-compose {
  position: relative;
  top: 50px;
  border: 2px solid #f0eceb;
  margin: 20px 0;
}

.rate-compose > form {
  border: 1px solid #d1ccc8;
}

.rate-compose .compose-main {
  background: #f6f6f6;
  padding-bottom: 50px;
}

.compose-main > .compose-header {
  position: relative;
  padding: 25px 0 30px 5%;
}

.compose-header > span {
  font-size: 16px;
  color: #333333;
  font-weight: bold;
}

.compose-order {
  height: 119px;
}

.J_rateInputArea {
  font-family: Arial, serif;
  font-size: 12px;
  width: 60px;
  height: 118px;
  line-height: 118px;
  text-align: center;
  vertical-align: top;
  color: #666;
  background-color: #efefef;
  border: 1px solid #e7e7e7;
  border-top: 0;
  float: left;
}

.text-input-box {
  height: 117px;
  border: 1px solid #e7e7e7;
  border-left: 0;
  width: 600px;
  position: relative;
  float: left;
}

.text-input-box > textarea {
  width: 100%;
  height: 100%;
  border: 0;
  resize: none;
}

.compose-submit {
  position: relative;
  padding: 10px 0;
  height: 27px;
  text-align: center;
}

.compose-submit input {
  background-color: #c40000;
  border: 0;
  display: inline-block;
  padding: 0 10px;
  line-height: 25px;
  font-weight: 700;
  color: #ffffff;
  cursor: pointer;
  border-radius: 2px;
}
</style>
