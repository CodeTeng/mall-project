import { get } from "../../utils/axios/request";
import {productSearchRequest} from "./type";

enum URL {
    // 主页商品分页搜索
    productSearch = 'product/search',
    // 根据商品id获取详细商品信息 /product/getDetailedProduct/{productId}
    getDetailedProduct = '/product/getDetailedProduct/',
}

export const productSearchApi = async (params: productSearchRequest) => get(URL.productSearch, params)

export const getDetailedProductApi = async (productId:number)  => get(URL.getDetailedProduct+productId,undefined);
