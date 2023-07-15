import { get,post ,DELETE,postByParams} from "../../utils/axios/request";
import {CartItemVO} from "./type";

enum URL {
    // 添加商品到购物车中
    addCartItem = '/cart/addCartItem',
    // 根据订单id删除购物车订单信息
    deleteCartItem = '/cart/deleteCartItem',
    // 获取我的购物车信息
    getMyCart = '/cart/getMyCart',
    // 更新购物车中的订单信息
    updateCartItem = '/cart/updateCartItem/',
    // 根据订单项id集合获取订单信息
    getCartListByItemId = '/cart/getCartListByItemId',
    // 根据订单id获取订单信息
    getCartListByOrderId = '/cart/getCartListByOrderId'

}
export const addCartItemApi = async (params?:CartItemVO)=>post(URL.addCartItem,params)

export const deleteCartItemApi =
    async (productOrderItemIdList:string)=>DELETE(URL.deleteCartItem,{productOrderItemIdList})

export const getMyCartApi = async ()=>get(URL.getMyCart,undefined);

export const updateCartItemApi =
    async (orderItemId:number,productOrderItemNumber:number)=>
        postByParams(URL.updateCartItem+orderItemId,{productOrderItemNumber:productOrderItemNumber})

export const getCartListByItemIdApi =
    async (orderItemIdList:string)=>get(URL.getCartListByItemId,{orderItemIdList:orderItemIdList})

export const  getCartListByOrderIdApi = async (productOrderId:number)=>get(URL.getCartListByOrderId,{productOrderId:productOrderId})