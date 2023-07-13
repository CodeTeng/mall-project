import { get, post } from "../../utils/axios/request";

enum URL {
    // 获取主页分类下的所有商品
    getHomeProduct = '/category/getHomeProduct',
    // 获取商品的分类信息 type:1 查询上方分类 type:2查询侧边分类
    getCategoryList = '/category/list',
    // 根据分类id获取商品
    getProductListByCategoryId = '/category/productListById',
}

export const getHomeProductApi = async ()=>get(URL.getHomeProduct,undefined)
export const getCategoryListApi = async (params:number)=>get(URL.getCategoryList,{type:params})
export const getProductListByCategoryIdApi = async (params:number) =>
    get(URL.getProductListByCategoryId,{categoryId:params})
