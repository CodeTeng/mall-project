import {get, post} from "../../utils/axios/request";
import {pageReviewType, reviewProductType} from "./type";

enum URL {
    // 根据商品id分页获取评论数据
    getPageReviewByProductId = 'review/getPageReviewByProductId',
    // 获取商品评论总数
    getProductReviewCount = '/review/getProductReviewCount',
    // 评价商品
    reviewProduct = '/review/reviewProduct',
}

export const  getPageReviewByProductIdApi =
    async (params:pageReviewType)=>get(URL.getPageReviewByProductId,params)

export const getProductReviewCountApi =
    async (params:number) => get(URL.getProductReviewCount,{productId:params})

export const reviewProductApi = async (params:reviewProductType) => post(URL.reviewProduct,params)
