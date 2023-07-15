import { get, post } from "../../utils/axios/request";
import {OrderAddRequest, OrderPageRequest, updateOrderRequest} from "./type";
enum URL {
    // 获得用户所有订单
    getMyAllOrder = "/order/getMyAllOrder",

    // 修改订单状态
    updateOrderStatus = "/order/updateOrderStatus",

    // 添加订单
    addOrder = "/order/addOrder",

    // 获得订单时间
    getOrderTime = '/order/getOrderTime'

}
export const getMyAllOrderApi = async (params: OrderPageRequest) => get(URL.getMyAllOrder, params);

export const updateOrderStatusApi = async (params: updateOrderRequest) => post(URL.updateOrderStatus, params)

export const addOrderApi = async (params:OrderAddRequest)=>post(URL.addOrder,params)

export const getOrderTimeApi = async (productOrderId:number)=>get(URL.getOrderTime,{productOrderId:productOrderId})

