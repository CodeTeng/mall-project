// 订单状态枚举
import {pageType} from "../baseType";
import {LocationQueryValueRaw} from 'vue-router'
// 订单分页请求
export interface OrderPageRequest extends pageType{
    status?: number;
}

// 订单状态修改请求
export interface updateOrderRequest{
    productOrderId:number;
    productOrderStatus:number;
}

// 订单item商品信息
export interface   OrderItemProductVO {
    productId: number;
    productImageSrc: string;
    productName: string;
    productSalePrice: number;
}

// 订单item视图
export interface OrderItemVO{
    productOrderItemId: number;
    productOrderItemNumber: number;
    productOrderItemPrice: string;
    productOrderItemProductVO: OrderItemProductVO;
}

// 订单列表视图
export interface ProductOrderVO {
    // 订单订单编号
    productOrderCode: string;
    // 订单项列表
    productOrderItemList: OrderItemVO[],
    // 订单ID
    productOrderId: number;
    // 付款时间
    productOrderPayDate: string;
    // 订单状态
    productOrderStatus: number;
}

// 添加订单的商品item
export interface addOrderItemVO{
    productOrderItemId:number;
    productOrderItemUserMessage:string;
}

// 添加订单请求
export interface OrderAddRequest{
    addOrderItemDTOList: addOrderItemVO[];
    productOrderAddress: string;
    productOrderDetailAddress: string;
    productOrderMobile: string;
    productOrderPost: string;
    productOrderReceiver: string;
}