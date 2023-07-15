import { get } from "../../utils/axios/request";
import { productSearchRequest } from "./type";

enum URL {
    // 主页商品分页搜索
    productSearch = '/product/search',
    // 根据商品id获取详细商品信息 /product/getDetailedProduct/{productId}
    getDetailedProduct = '/product/getDetailedProduct/',
    // 根据分类id分页查询商品信息
    getProductByCategoryId = '/product/getProductByCategoryId'

}

export const productSearchApi = async (params: productSearchRequest) => get(URL.productSearch, params)

export const getDetailedProductApi = async (productId: number) => get(URL.getDetailedProduct + productId, undefined);

export const getProductByCategoryIdApi =
    async (params: productSearchRequest) => get(URL.getProductByCategoryId, params);
