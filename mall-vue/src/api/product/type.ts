import { pageType } from '../baseType'
export interface productSearchRequest extends pageType {
    productName: string;
}

// 商品属性
export interface productParameterType {
    propertyName: string;
    propertyValueValue: string;
}

// 商品图片
export interface   ProductImage {
    productImageId: number;
    productImageProductId: number;
    productImageSrc: string;
    productImageType: number;
}

// 商品详情
export interface productDetailsType {
    // 商品不可放大图
    detailProductImageList: string[];
    productId: number;
    productName: string;
    // 商品属性列表
    productParameterList: productParameterType[];
    productPrice: number;
    // 商品价格
    productSalePrice: number;
    // 评论数量
    reviewCount: number;
    // 商品可放大图
    singleProductImageList: string[];
    // 总销量
    totalSales: number;
}



