<template>
  <div>
    <Search title="" :isShowLi="false" :isSHowSearch="true" />

    <!-- 结果界面 -->
    <div class="context" v-show="!isProductNull">
      <div class="context_menu">
        <ul data-type="1">
          <li data-name="productSalePrice" @click="dobaseSearch">
            <span style="position: relative; left: 3px">综合</span>
          </li>
          <!-- 根据价格 -->
          <li data-name="productSalePrice" @click="doProductSalePriceSort()">
            <span style="position: relative; left: 3px">价格</span>
            <span
              class="orderByAsc"
              v-if="productSalePriceOrderBy === 'desc'"
            ></span>
            <span
              class="orderByDesc"
              v-if="productSalePriceOrderBy === 'asc'"
            ></span>
          </li>
        </ul>
      </div>

      <!-- 商品列表 -->
      <div class="context_main">
        <div
          class="context_productStyle"
          v-for="item in productList"
          :key="item.productId"
        >
          <div class="context_product">
            <router-link :to="'/product/info/' + item.productId"
              ><img
                class="context_product_imgMain"
                :src="bindImg(item.productImageSrcList[0])"
            /></router-link>
            <ul class="context_product_imgList">
              <li v-for="imgurl in item.productImageSrcList" :key="imgurl">
                <img :src="bindImg(imgurl)" />
              </li>
            </ul>
            <p class="context_product_price">
              <span>¥</span>{{ item.productSalePrice }}
            </p>
            <p class="context_product_name">
              <router-link :to="'/mall/product/' + item.productId">{{
                item.productName
              }}</router-link>
            </p>
            <p class="context_product_shop">
              <span>{{ item.categoryName }}旗舰店</span>
            </p>
            <p class="context_product_status">
              <span class="status_left"
                >总成交<em>{{ item.totalTransactionCount }}笔</em></span
              >
              <span class="status_middle"
                >评价<em>{{ item.reviewCount }}</em></span
              >
              <span class="status_right"
                ><img
                  src="../../assets/images/fore/WebsiteImage/T11lggFoXcXXc1v.nr-93-93.png"
              /></span>
            </p>
          </div>
        </div>
      </div>
      <el-pagination
        background
        v-model:current-page="searchQuery.current"
        :page-size="15"
        layout="prev, pager, next"
        v-model:total="pageTotal"
        @current-change="currentChange"
        class="el-page"
      />
    </div>

    <!-- 未查询到的结果界面 -->
    <div class="context" v-show="isProductNull">
      <div class="error">
        <h2>
          喵~没找到与“{{ productName }}”相关的 商品
          哦，要不您换个关键词我帮您再找找看
        </h2>
        <h3>建议您：</h3>
        <ol>
          <li>看看输入的文字是否有误</li>
          <li>调整关键词，如“全铜花洒套件”改成“花洒”或“花洒 套件”</li>
          <li>
            <form action="/product" method="get">
              <input
                title="查询产品"
                type="text"
                class="errorInput"
                name="productName"
                v-model="productName"
              />
              <input type="submit" value="去Mall搜索" class="errorBtn" />
            </form>
          </li>
        </ol>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from "vue-router";
import { getProductByCategoryIdApi, productSearchApi } from "../../api/product";
import { bindImg } from "../../utils";
import { productSearchRequest } from "../../api/product/type";
const route = useRoute();
const isProductNull = ref<boolean>(true);

// 获得查询参数
const productName = ref<any>();
const categoryId = ref<any>();
categoryId.value = route.query?.categoryId;
productName.value = route.query?.productName;

const searchQuery = ref<productSearchRequest>({
  current: 1,
  pageSize: 15,
  sortField: undefined,
  sortOrder: undefined,
});

const pageTotal = ref<number>(0);

// 分页响应
const currentChange = () => {
  doSearch();
};

interface productInfo {
  productId: number;
  productImageSrcList: string[];
  productName: string;
  productPlanSrc: string;
  productSalePrice: number;
  reviewCount: number;
  totalTransactionCount: number;
  categoryName: string;
}

// 价格排序
const productSalePriceOrderBy = ref<string>("desc");

// 价格排序
const doProductSalePriceSort = () => {
  if (productSalePriceOrderBy.value === "desc") {
    productSalePriceOrderBy.value = "asc";
  } else {
    productSalePriceOrderBy.value = "desc";
  }

  searchQuery.value.sortField = "ProductSalePrice";
  searchQuery.value.sortOrder = productSalePriceOrderBy.value;
  doSearch();
};

// 点击综合查询
const dobaseSearch = () => {
  searchQuery.value.sortField = undefined;
  searchQuery.value.sortOrder = undefined;
  doSearch();
};

const productList = ref<productInfo[]>();

// 进行搜索
const doSearch = () => {
  if (!(productName.value == undefined)) {
    doSearchByProductName();
  } else if (!(categoryId.value == undefined)) {
    doSearchByCategoryId();
  }
};

// 通过商品名进行搜索
const doSearchByProductName = () => {
  searchQuery.value.productName = productName.value;
  productSearchApi(searchQuery.value).then((res) => {
    console.log(res);
    if (res.code === 0) {
      console.log("请求成功");
      isProductNull.value = false;
      productList.value = res.data.records;
      pageTotal.value = res.data.total;
      if (productList.value?.length === 0) {
        isProductNull.value = true;
      }
    } else {
      isProductNull.value = true;
      ElMessage.error("获取商品数据失败");
    }
  });
};

// 通过分类进行搜索进行搜索
const doSearchByCategoryId = () => {
  searchQuery.value.categoryId = categoryId.value;
  searchQuery.value.productName = undefined;
  getProductByCategoryIdApi(searchQuery.value).then((res) => {
    console.log(res);
    if (res.code === 0) {
      console.log("请求成功");
      isProductNull.value = false;
      productList.value = res.data.list;
      pageTotal.value = res.data.total;
      if (productList.value?.length === 0) {
        isProductNull.value = true;
      }
    } else {
      isProductNull.value = true;
      ElMessage.error("获取商品数据失败");
    }
  });
};

// 挂载初始化
onMounted(() => {
  doSearch();
});
</script>

<style scoped>
.el-page {
  margin-left: 150px;
  margin-bottom: 30px;
}
</style>

<!-- 列表样式 -->
<style lang="scss" scoped>
.context {
  width: 100%;
}

.context > .context_menu {
  width: 1230px;
  margin: 0 auto;
  background-color: #faf9f9;
  padding: 5px;
  height: 23px;
}

.context_menu > ul {
  display: inline-block;
  border: 1px solid #cccccc;
  border-right: none;
  margin-bottom: 0;
  margin-right: 5px;
}

.context_menu > ul > li {
  float: left;
  border-right: 1px solid #cccccc;
}

.context_menu > ul > li {
  color: #806f66;
  text-align: center;
  display: block;
  width: 50px;
  padding: 2px;
  font-size: 12px;
  cursor: pointer;
}

.context_menu li:hover {
  text-decoration: none;
  color: #ff0036;
  background-color: #f1edec;
}

.context_menu li.orderBySelect {
  background: #f1edec;
}

.context_menu li.orderBySelect > span {
  color: #ff0036;
}

.context_menu li.orderBySelect > span.orderByDesc.orderBySelect {
  border-bottom-color: #ff0036;
}

.context_menu li.orderBySelect > span.orderByAsc.orderBySelect {
  border-top-color: #ff0036;
}

.context_menu li > span.orderByDesc {
  display: inline-block;
  position: relative;
  bottom: 6px;
  width: 0;
  height: 0;
  border: 4px solid transparent;
  border-bottom-color: #806f66;
}

.context_menu li > span.orderByAsc {
  display: inline-block;
  position: relative;
  width: 0;
  height: 0;
  top: 2px;
  border: 4px solid transparent;
  border-top-color: #806f66;
}

.context_menu_liHover {
  color: #ff0036;
  background-color: #f1edec;
}

.context > .context_main {
  width: 1230px;
  margin: 0 auto;
  padding: 10px 5px;
}

.context_main > .context_productStyle {
  width: 220px;
  height: 372px;
  position: relative;
  display: inline-block;
  margin-right: 20px;
  margin-bottom: 25px;
}

.context_productStyle > .context_product {
  width: 210px;
  background-color: #ffffff;
  padding: 4px 4px 0;
  border: 1px solid #f5f5f5;
  border-radius: 3px;
}

.context_product:hover {
  height: auto;
  margin: -3px;
  border: 4px solid #ff0036;
  border-radius: 0;
  -webkit-transition: border-color 0.2s ease-in;
  -moz-transition: border-color 0.2s ease-in;
  -o-transition: border-color 0.2s ease-in;
  transition: border-color 0.2s ease-in;
}

.context_product .context_product_imgMain {
  width: 210px;
  height: 210px;
  margin-bottom: 5px;
}

.context_product > .context_product_imgList {
  text-align: center;
  margin-bottom: 5px;
  padding-top: 5px;
  width: 100%;
  min-height: 37px;
}

.context_product_imgList > li {
  cursor: pointer;
  display: inline-block;
  border: 1px solid #cccccc;
  padding: 1px;
}

.context_product_imgList > .context_product_Li_click {
  border: 2px solid #cd0000;
  padding: 0;
}

.context_product_imgList img {
  width: 30px;
  height: 30px;
}

.context_product > .context_product_price {
  font-family: arial, serif;
  color: #ff0036;
  font-size: 20px;
  height: 30px;
  display: block;
  line-height: 30px;
  margin: 0 0 5px;
  overflow: hidden;
  font-weight: 400;
}

.context_product_price > span {
  font-family: arial, serif;
  margin-right: 2px;
  font-weight: 700;
  font-size: 14px;
}

.context_product > .context_product_name {
  display: block;
  height: 16px;
  margin: 0 0 3px;
  word-break: break-all;
  position: relative;
  overflow: hidden;
}

.context_product_name > a {
  font-family: \5FAE\8F6F\96C5\9ED1, serif;
  color: #333333;
  font-size: 12px;
}

.context_product_name > a:hover {
  color: #ff0036;
  text-decoration: underline;
}

.context_product > .context_product_shop {
  position: relative;
  height: 22px;
  line-height: 20px;
  margin: 0 0 5px;
  white-space: nowrap;
  overflow: hidden;
}

.context_product_shop > span {
  color: #999;
  text-decoration: underline;
  text-overflow: ellipsis;
  font-size: 12px;
  white-space: nowrap;
}

.context_product > .context_product_status {
  position: relative;
  height: 32px;
  border: none;
  border-top: 1px solid #eee;
  margin: 0;
  color: #999;
}

.context_product_status > .status_left {
  float: left;
  border-right: 1px solid #eeeeee;
  width: 39%;
  padding: 10px 1px;
  margin-right: 6px;
  line-height: 12px;
  text-align: left;
  font-size: 12px;
  color: #999;
}

.status_left > em {
  position: absolute;
  color: #b57c5b;
  padding-left: 2px;
  font-family: Arial, serif;
  font-size: 12px;
  font-weight: 700;
  font-style: normal;
}

.context_product_status > .status_middle {
  float: left;
  border-right: 1px solid #eeeeee;
  width: 39%;
  padding: 10px 1px;
  margin-right: 6px;
  line-height: 12px;
  text-align: left;
  font-size: 12px;
  color: #999;
}

.status_middle > em {
  color: #38b;
  padding-left: 2px;
  font-family: Arial, serif;
  font-size: 12px;
  font-weight: 700;
  font-style: normal;
}

.context_product_status > .status_right {
  float: left;
  width: 9%;
  margin-right: 6px;
  padding: 6px 1px;
}

.status_right > img {
  position: relative;
  bottom: 2px;
  width: 16px;
  height: 16px;
  cursor: pointer;
  margin: 0;
  padding: 0;
}

.error {
  width: 1095px;
  padding: 8px 20px 20px 115px;
  background: url(../../assets/images/fore/WebsiteImage/TB1QJqdPFXXXXccXFXXXXXXXXXX-65-48.png)
    23px center no-repeat #fff8f6;
  border: 1px solid #f7eae7;
  color: #595959;
  margin: 0 auto 10px;
}

.error > h2 {
  font-size: 14px;
  font-weight: 700;
  color: #333;
  line-height: 38px;
}

.error > h3 {
  font-size: 12px;
  line-height: 36px;
  font-weight: bold;
}

.error > ol {
  list-style: none;
}

.error > ol > li {
  font-size: 12px;
  margin-bottom: 5px;
  list-style: decimal inside;
}

.error form {
  display: inline-block;
}

.error .errorInput {
  width: 180px;
  padding: 4px 5px;
  height: 15px;
  line-height: 15px;
  margin: 2px 10px 0 0;
  border: 1px solid #e5e5e5;
  display: inline-block;
}

.error .errorBtn {
  background-color: #ff0036;
  color: #fff;
  height: 25px;
  line-height: 25px;
  font-size: 12px;
  font-weight: 700;
  padding: 0 10px;
  display: inline-block;
  border: 0;
  border-left: 1px solid #ff0036;
  border-right: 1px solid #ff0036;
  border-radius: 2px;
}
</style>
