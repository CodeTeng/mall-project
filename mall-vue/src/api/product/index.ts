import { get } from "../../utils/axios/request";

enum URL {
    // 主页商品分页搜索
    productSearch = 'product/search',
    // 获取主页分类下的商品
    getHomeProduct = 'product/getHomeProduct',
    // 根据商品id获取商品信息
    getProductById = 'product/getProductById',
    // 根据商品id获取商品参数
    getProductParameter = 'product/getProductParameter'

}

export const productSearchApi = async (params: productSearchRequest) => get(URL.productSearch, params)