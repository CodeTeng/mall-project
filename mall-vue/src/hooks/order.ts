import { updateOrderStatusApi } from "../api/order";
import { updateOrderRequest } from "../api/order/type";

// 设置订单状态
export const setOrderStatus = (productOrderId: number, status: number) => {
    const data: updateOrderRequest = {
        productOrderId: productOrderId,
        productOrderStatus: status
    }
    const res = updateOrderStatusApi(data).then(res => {
        if (res.code === 0) {
            return true;
        } else {
            return false;
        }
    }).catch(e => {
        return false;
    }).finally(() => {
        return false;
    })

}