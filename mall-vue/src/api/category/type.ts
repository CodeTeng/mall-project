
// 分类
export interface categoryType {
    categoryId: number;
    categoryName?: string;
}


export interface categoryHomeProductVO {
    productId: number;
    productName: string;
    productSalePrice: number;
    singleProductImageList: string[];
}

export interface categoryHomeMainType {
    categoryHomeProductVOList: categoryHomeProductVO[],
    categoryId: number,
    categoryImageSrc: string,
    categoryName: string
}

// 商品热属性
export interface categoryProductNameType {
    productId: number;
    productIsEnabled: number;
    productTitle: string
}
