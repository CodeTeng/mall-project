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
    <div class="home_nav">
      <div class="home_nav_title">
        <img src="static/images/fore/WebsiteImage/header_nav_title.png" />
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

    <!--  -->
    <div class="banner">
      <img
        src="static/images/fore/WebsiteImage/banner/42.jpg"
        name="42"
        id="banner1"
        style="display: block"
      />

      <img
        src="static/images/fore/WebsiteImage/banner/43.jpg"
        name="43"
        id="banner2"
      />

      <img
        src="static/images/fore/WebsiteImage/banner/44.jpg"
        name="44"
        id="banner3"
      />

      <img
        src="static/images/fore/WebsiteImage/banner/45.jpg"
        name="45"
        id="banner4"
      />

      <img
        src="static/images/fore/WebsiteImage/banner/46.jpg"
        name="46"
        id="banner5"
      />

      <img
        src="static/images/fore/WebsiteImage/banner/47.jpg"
        name="47"
        id="banner6"
      />
    </div>

    <div class="banner_main">
      <ul class="banner_nav">
        <li data-toggle="1" data-status="">
          <img src="static/images/fore/WebsiteImage/small/1.png" />
          <a href="/mall/product?categoryId=1">女装 /大衣</a>
          <div class="banner_div" name="女装 /大衣"></div>
        </li>

        <li data-toggle="2" data-status="">
          <img src="static/images/fore/WebsiteImage/small/2.png" />
          <a href="/mall/product?categoryId=2">男装 /运动户外</a>
          <div class="banner_div" name="男装 /运动户外"></div>
        </li>

        <li data-toggle="3" data-status="">
          <img src="static/images/fore/WebsiteImage/small/3.png" />
          <a href="/mall/product?categoryId=3">女鞋 /男鞋 /箱包</a>
          <div class="banner_div" name="女鞋 /男鞋 /箱包"></div>
        </li>

        <li data-toggle="4" data-status="">
          <img src="static/images/fore/WebsiteImage/small/4.png" />
          <a href="/mall/product?categoryId=4">美妆 /个人护理</a>
          <div class="banner_div" name="美妆 /个人护理"></div>
        </li>

        <li data-toggle="5" data-status="">
          <img src="static/images/fore/WebsiteImage/small/5.png" />
          <a href="/mall/product?categoryId=5">腕表 /眼镜 /珠宝饰品</a>
          <div class="banner_div" name="腕表 /眼镜 /珠宝饰品"></div>
        </li>

        <li data-toggle="6" data-status="">
          <img src="static/images/fore/WebsiteImage/small/6.png" />
          <a href="/mall/product?categoryId=6">手机 /数码 /电脑办公</a>
          <div class="banner_div" name="手机 /数码 /电脑办公"></div>
        </li>

        <li data-toggle="7" data-status="">
          <img src="static/images/fore/WebsiteImage/small/7.png" />
          <a href="/mall/product?categoryId=7">母婴玩具</a>
          <div class="banner_div" name="母婴玩具"></div>
        </li>

        <li data-toggle="8" data-status="">
          <img src="static/images/fore/WebsiteImage/small/8.png" />
          <a href="/mall/product?categoryId=8">零食 /茶酒 /进口食品</a>
          <div class="banner_div" name="零食 /茶酒 /进口食品"></div>
        </li>

        <li data-toggle="9" data-status="">
          <img src="static/images/fore/WebsiteImage/small/9.png" />
          <a href="/mall/product?categoryId=9">生鲜水果</a>
          <div class="banner_div" name="生鲜水果"></div>
        </li>

        <li data-toggle="10" data-status="">
          <img src="static/images/fore/WebsiteImage/small/10.png" />
          <a href="/mall/product?categoryId=10">大家电 /生活电器</a>
          <div class="banner_div" name="大家电 /生活电器"></div>
        </li>

        <li data-toggle="11" data-status="">
          <img src="static/images/fore/WebsiteImage/small/11.png" />
          <a href="/mall/product?categoryId=11">家居建材</a>
          <div class="banner_div" name="家居建材"></div>
        </li>

        <li data-toggle="12" data-status="">
          <img src="static/images/fore/WebsiteImage/small/12.png" />
          <a href="/mall/product?categoryId=12">汽车 /配件 /用品</a>
          <div class="banner_div" name="汽车 /配件 /用品"></div>
        </li>

        <li data-toggle="13" data-status="">
          <img src="static/images/fore/WebsiteImage/small/13.png" />
          <a href="/mall/product?categoryId=13">家纺 /家饰 /鲜花</a>
          <div class="banner_div" name="家纺 /家饰 /鲜花"></div>
        </li>

        <li data-toggle="14" data-status="">
          <img src="static/images/fore/WebsiteImage/small/14.png" />
          <a href="/mall/product?categoryId=14">医药保健</a>
          <div class="banner_div" name="医药保健"></div>
        </li>

        <li data-toggle="15" data-status="">
          <img src="static/images/fore/WebsiteImage/small/15.png" />
          <a href="/mall/product?categoryId=15">厨具 /收纳 /宠物</a>
          <div class="banner_div" name="厨具 /收纳 /宠物"></div>
        </li>

        <li data-toggle="16" data-status="">
          <img src="static/images/fore/WebsiteImage/small/16.png" />
          <a href="/mall/product?categoryId=16">图书音像</a>
          <div class="banner_div" name="图书音像"></div>
        </li>
      </ul>
      <ul class="banner_slider">
        <li id="slider_1" style="background: rgba(255, 255, 255, 0.4)"></li>
        <li id="slider_2"></li>
        <li id="slider_3"></li>
        <li id="slider_4"></li>
        <li id="slider_5"></li>
        <li id="slider_6"></li>
      </ul>
      <a href="#"></a>
    </div>

    <div class="banner_do">
      <div class="banner_goods">
        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>女装 /大衣</p>
          </div>
          <a href="/mall/product?categoryId=1"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/1.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/41" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/ff709d64-4548-4d53-8f68-ad3a376f43c9.jpg"
              />
              <a href="product/41" class="goods_name"
                >小猪佩奇社会人佩琪短袖t恤成人衣服恶搞联名抖音潮牌tee男女半袖</a
              >
              <span class="goods_price">￥49.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/40" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/a377f184-7c02-42a7-8e2f-a8350362aa7c.jpg"
              />
              <a href="product/40" class="goods_name"
                >宽松短袖t恤女简约夏季印花ulzzang百搭2018新款黄色学生大码上衣</a
              >
              <span class="goods_price">￥69.9</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/38" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/da1f9f23-346b-4c21-917c-1746932574be.jpg"
              />
              <a href="product/38" class="goods_name"
                >短袖女2018新款夏装纯棉T恤女白色韩范宽松学生ulzzang百搭半袖女</a
              >
              <span class="goods_price">￥35.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/36" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/46d2ce8a-b4ab-4f70-bc95-3f1e9e0c7623.jpg"
              />
              <a href="product/36" class="goods_name"
                >润乙一夏装2018新款女雪纺衫上衣超仙甜美碎花V领荷叶chic一字肩</a
              >
              <span class="goods_price">￥139.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/34" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/be1ac709-2b2f-445b-9271-a0ef5c011fb9.jpg"
              />
              <a href="product/34" class="goods_name"
                >白色T恤女短袖蕾丝上衣棉立方2018夏新款女装修身韩版圆领雪纺衫</a
              >
              <span class="goods_price">￥59.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/31" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/6938846f-ae3c-4265-9d8c-f2da4738dc43.jpg"
              />
              <a href="product/31" class="goods_name"
                >针织衫女开衫2018春装新款宽松韩版中长款2017秋装学生毛衣外套潮</a
              >
              <span class="goods_price">￥118.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/29" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/df384d2b-664c-47a4-b298-8bedcb61a5d6.jpg"
              />
              <a href="product/29" class="goods_name"
                >茵曼旗舰店纯棉长袖衬衫曼茵2018春装新款印花衬衣女上衣女装寸衫</a
              >
              <span class="goods_price">￥169.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/25" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/e696e047-701f-40c9-a7f7-d03229779fe6.jpg"
              />
              <a href="product/25" class="goods_name"
                >菲西娅阔腿裤套装女2018春装新款女装针织衫时尚休闲两件套潮夏季</a
              >
              <span class="goods_price">￥528.0</span>
            </div>
          </div>
        </div>

        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>男装 /运动户外</p>
          </div>
          <a href="/mall/product?categoryId=2"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/2.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/39" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/83fd4cef-eefe-40cd-b4d8-8551a1e5b29b.jpg"
              />
              <a href="product/39" class="goods_name"
                >GXG男装 2018夏季新品时尚胶印黑色休闲短裤男五分裤#182822204</a
              >
              <span class="goods_price">￥259.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/37" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/10768830-b441-4b99-895c-c168e35acc39.jpg"
              />
              <a href="product/37" class="goods_name"
                >衣品天成2018夏季新款男士短袖T恤潮韩版假两件体恤早春打底小衫</a
              >
              <span class="goods_price">￥79.9</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/35" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/38d6be09-2b59-4329-8527-2c6630d23289.jpg"
              />
              <a href="product/35" class="goods_name"
                >衣品天成2018新款男士T恤潮流夏季印花半截袖青年韩版短袖体恤</a
              >
              <span class="goods_price">￥79.9</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/33" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/e0832011-052a-422b-a044-01812ae8bc83.jpg"
              />
              <a href="product/33" class="goods_name"
                >衣品天成2018夏季新款t恤男士短袖圆领纯色纯棉潮流韩版半袖体恤</a
              >
              <span class="goods_price">￥78.9</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/32" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/8de0c092-d398-45bf-b931-cf99819d703e.jpg"
              />
              <a href="product/32" class="goods_name"
                >衣品天成男士短袖T恤夏季男装半袖衣服潮流韩版修身纯棉圆领体恤</a
              >
              <span class="goods_price">￥78.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/28" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/84550614-7786-4ce0-bbce-c424fde34e63.jpg"
              />
              <a href="product/28" class="goods_name"
                >SELECTED思莱德纯棉简约立领男士长款防风风衣外套BL|417121501</a
              >
              <span class="goods_price">￥399.6</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/26" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/44ae6bb9-9cfe-4522-859f-ae666414888b.jpg"
              />
              <a href="product/26" class="goods_name"
                >男士白衬衫短袖修身韩版潮流帅气2018新款夏季休闲百搭印花衬衣男</a
              >
              <span class="goods_price">￥89.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/20" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/d4e0cc31-c6bc-4aaf-b40a-ce306b444c86.jpg"
              />
              <a href="product/20" class="goods_name"
                >七匹狼短袖衬衫 2018夏季新款男士中青年纯色商务休闲白衬衣男装</a
              >
              <span class="goods_price">￥199.0</span>
            </div>
          </div>
        </div>

        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>女鞋 /男鞋 /箱包</p>
          </div>
          <a href="/mall/product?categoryId=3"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/3.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/30" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/e43936ed-5149-4451-a7ca-1053d12d821e.jpg"
              />
              <a href="product/30" class="goods_name"
                >Nike 耐克官方 NIKE FLEX ESSENTIAL TR女子训练鞋 924344</a
              >
              <span class="goods_price">￥469.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/27" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/138e4099-9b34-4e4e-aeb4-cf32f2086b08.jpg"
              />
              <a href="product/27" class="goods_name"
                >Nike 耐克官方NIKE FREE METCON 男子训练鞋AH8141</a
              >
              <span class="goods_price">￥899.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/24" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/8a3db48c-3eac-408e-89a0-ec2902af0d0c.jpg"
              />
              <a href="product/24" class="goods_name"
                >CHARLES＆KEITH平底单鞋女CK1-70900076芭蕾舞绑带穆勒尖头小白鞋</a
              >
              <span class="goods_price">￥329.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/22" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/2c26824a-4510-42fa-b26b-85ce1ae08011.jpg"
              />
              <a href="product/22" class="goods_name"
                >CHARLES＆KEITH春夏单鞋女CK1-60900081通勤尖头浅口猫跟小白鞋</a
              >
              <span class="goods_price">￥329.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/19" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/bd8f5ad0-0aba-466a-935c-cce36c307a6c.jpg"
              />
              <a href="product/19" class="goods_name"
                >热风2017春新款小白鞋女低帮鞋系带时尚女休闲鞋H14W7110</a
              >
              <span class="goods_price">￥99.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/17" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/9434fb97-93e1-420a-b26a-c3810879db8e.jpg"
              />
              <a href="product/17" class="goods_name"
                >春季韩版原宿学生帆布鞋女港风板鞋平底运动鞋情侣休闲鞋软妹鞋潮</a
              >
              <span class="goods_price">￥68.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/14" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/a2be53ea-31a0-4535-b09b-76e22645f928.jpg"
              />
              <a href="product/14" class="goods_name"
                >YOVCA手绘情侣鞋平底大码板鞋高帮帆布鞋女鞋男鞋休闲鞋动漫周边</a
              >
              <span class="goods_price">￥59.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/13" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/8941cca2-d043-4df8-903c-4268f806e935.jpg"
              />
              <a href="product/13" class="goods_name"
                >韩版潮帆布鞋女 平跟学生板鞋 女鞋 高帮运动鞋牛仔休闲秋鞋子</a
              >
              <span class="goods_price">￥59.0</span>
            </div>
          </div>
        </div>

        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>美妆 /个人护理</p>
          </div>
          <a href="/mall/product?categoryId=4"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/4.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/67" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/85393363-e3a5-4b11-8164-947a62448e38.jpg"
              />
              <a href="product/67" class="goods_name"
                >清扬洗发水露男士活力运动薄荷控油去屑滋养官方正品套装500g*3</a
              >
              <span class="goods_price">￥109.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/66" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/a9d2f298-c7b9-46e7-ba4d-499c058b1ddc.jpg"
              />
              <a href="product/66" class="goods_name"
                >乔治卡罗尔男士洗发水沐浴露套装控油去屑持久留香味洗头膏露洗浴</a
              >
              <span class="goods_price">￥59.9</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/65" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/56f85a22-c86c-4d33-8139-b3d691b72a49.jpg"
              />
              <a href="product/65" class="goods_name"
                >睿嫣润膏舒盈洗发水250ml女士coco香水洗护合一持久留香韩国进口</a
              >
              <span class="goods_price">￥59.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/64" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/355630e0-e686-4b68-9fc1-e503bcf8dfd1.jpg"
              />
              <a href="product/64" class="goods_name"
                >海飞丝鹿晗洗发水丝质柔滑去屑止痒洗头露膏套装男女士500ml*3</a
              >
              <span class="goods_price">￥109.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/63" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/c905e661-6f50-485d-9dfd-99001e821008.jpg"
              />
              <a href="product/63" class="goods_name"
                >男士洗发水沐浴露套装古龙香味持久留香去屑止痒洗头膏液洗浴套装</a
              >
              <span class="goods_price">￥44.9</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/62" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/7888fd12-fa9e-4f11-8e66-3121877ef766.jpg"
              />
              <a href="product/62" class="goods_name"
                >雅诗兰黛红石榴眼霜 鲜活亮采眼部凝露 保湿提亮 淡化熊猫眼</a
              >
              <span class="goods_price">￥350.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/61" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/50fde06e-f38d-47e0-8dbb-e2b7c822b842.jpg"
              />
              <a href="product/61" class="goods_name"
                >雅诗兰黛面膜 小棕瓶密集修护肌透面膜贴4/8片装套装 补水保湿</a
              >
              <span class="goods_price">￥680.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/60" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/d792426f-b6b9-42c9-9999-1972fb4bfb20.jpg"
              />
              <a href="product/60" class="goods_name"
                >【母亲节】雅诗兰黛眼霜 小棕瓶密集修护眼精华 紧致修护淡化细纹</a
              >
              <span class="goods_price">￥590.0</span>
            </div>
          </div>
        </div>

        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>腕表 /眼镜 /珠宝饰品</p>
          </div>
          <a href="/mall/product?categoryId=5"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/5.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/84" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/efa5e8b7-6c99-4271-a20f-2437f91bc25a.jpg"
              />
              <a href="product/84" class="goods_name"
                >BOLON暴龙2018新款复古蛤蟆镜男金属框太阳镜革新墨镜眼镜BL7021</a
              >
              <span class="goods_price">￥668.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/83" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/0bf4a4d3-18e6-45d7-a0bb-c88452cfdcae.jpg"
              />
              <a href="product/83" class="goods_name"
                >施华洛世奇DUO恶魔之眼项链时尚气质女短款锁骨链首饰</a
              >
              <span class="goods_price">￥990.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/82" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/dce287fb-6b9c-4abf-8001-d07e4bb41311.jpg"
              />
              <a href="product/82" class="goods_name"
                >PANDORA潘多拉 华丽蝴蝶结手链925银手链597242CZ气质时尚简约女</a
              >
              <span class="goods_price">￥598.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/81" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/863598e8-0ab3-465e-815f-92066dfe6ec6.jpg"
              />
              <a href="product/81" class="goods_name"
                >军拓铁腕5心率监测GPS户外功能运动导航智能手表铁人三项马拉松表</a
              >
              <span class="goods_price">￥2999.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/80" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/27da2600-879e-4d7a-b3ad-aa51de455332.jpg"
              />
              <a href="product/80" class="goods_name"
                >SUUNTO颂拓松拓斯巴达极速光电心率运动骑行跑步智能腕表户外手表</a
              >
              <span class="goods_price">￥4089.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/79" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/5c85034f-ec06-4357-b0bd-c11a41b91255.jpg"
              />
              <a href="product/79" class="goods_name"
                >CASIO卡西欧G-SHOCK旗舰店官网DW-5035运动男士手表男表Gshock</a
              >
              <span class="goods_price">￥1190.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/78" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/cc9e4efb-4fc6-40f5-9fa1-5a9258ff7163.jpg"
              />
              <a href="product/78" class="goods_name"
                >卡西欧CASIO手表男士正品防水精钢带MTP-1374D-7A三眼休闲石英表</a
              >
              <span class="goods_price">￥298.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/77" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/ffcde472-33a5-4368-a5eb-5eb6a5640019.jpg"
              />
              <a href="product/77" class="goods_name"
                >casio卡西欧G-SHOCK旗舰店官网GA-700运动男士电子手表男表gshock</a
              >
              <span class="goods_price">￥990.0</span>
            </div>
          </div>
        </div>

        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>手机 /数码 /电脑办公</p>
          </div>
          <a href="/mall/product?categoryId=6"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/6.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/47" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/e960c83a-9922-460c-9c2b-4e4dadfed8e0.jpg"
              />
              <a href="product/47" class="goods_name"
                >【领券低至919】Meizu/魅族 魅蓝 Note6 疾速双摄 快充大电池</a
              >
              <span class="goods_price">￥999.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/45" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/10d6cfe2-23de-4428-9366-68dc0f5c461a.jpg"
              />
              <a href="product/45" class="goods_name"
                >【渐变樱粉金热卖中】Huawei/华为 P20 全面屏徕卡双摄正品4G手机</a
              >
              <span class="goods_price">￥4088.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/44" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/ef12249e-9698-4b75-9b75-2b6cadeda14c.jpg"
              />
              <a href="product/44" class="goods_name"
                >Xiaomi/小米 红米5A新品老人机拍照官网4a升级款小巧简约智能手机</a
              >
              <span class="goods_price">￥569.0</span>
            </div>

            <div class="banner_goods_item">
              <a href="product/42" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/e20ce6b1-826b-4d70-baee-60fd21e1a79b.jpg"
              />
              <a href="product/42" class="goods_name"
                >【2018年生肖纪念版】OPPO R11S 全面屏 拍照4G手机 oppor11s</a
              >
              <span class="goods_price">￥2599.0</span>
            </div>
          </div>
        </div>

        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>零食 /茶酒 /进口食品</p>
          </div>
          <a href="/mall/product?categoryId=8"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/8.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/43" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/63186053-ff66-4ec2-842c-a527ec525a38.jpg"
              />
              <a href="product/43" class="goods_name"
                >赵薇酒庄梦陇法国红酒原装进口干红葡萄酒2支装波尔多绝代双骄III</a
              >
              <span class="goods_price">￥288.0</span>
            </div>
          </div>
        </div>

        <div class="banner_goods_type">
          <div class="banner_goods_title">
            <span></span>
            <p>大家电 /生活电器</p>
          </div>
          <a href="/mall/product?categoryId=10"
            ><img
              class="banner_goods_show"
              src="res/images/fore/WebsiteImage/show/10.jpg"
          /></a>
          <div class="banner_goods_items">
            <div class="banner_goods_item">
              <a href="product/46" class="goods_link"></a>
              <img
                src="static/images/item/productSinglePicture/832fe6a6-84e5-4d6e-95fd-f91c578a20f3.jpg"
              />
              <a href="product/46" class="goods_name"
                >360扫地机器人家用全自动吸尘器智能超薄静音拖地机擦地一体机</a
              >
              <span class="goods_price">￥1599.0</span>
            </div>
          </div>
        </div>
      </div>
      <div class="endDiv"></div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

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
  display: none;
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
