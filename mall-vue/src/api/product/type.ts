import { pageType } from '../baseType'

// 根据商品名模糊,商品分类id分页查询
export interface productSearchRequest extends pageType {
    productName?: string;
    categoryId?: number;
}



// 商品属性
export interface productParameterType {
    propertyName: string;
    propertyValueValue: string;
}

// 商品购物车信息
export interface ProductCartInfoVO{
    categoryId: number;
    categoryName: string
    productId: number;
    productImageSrc: string;
    productName: string;
    productOrderItemId: number;
    productOrderItemNumber: number;
    productOrderItemPrice: number;
    productSalePrice: number;
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



