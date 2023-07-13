import {pageType} from "../baseType";

export interface pageReviewType extends pageType{
    productId:number;
}

// 评价商品
export interface reviewProductType{
    reviewContent: string;
    reviewOrderItemId: number;
    reviewProductId: number;
}

export interface reviewPeople{
    reviewContent: string;
    reviewCreatedate: string;
    userNickName: string;
}