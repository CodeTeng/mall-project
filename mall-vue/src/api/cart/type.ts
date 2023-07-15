export interface CartItemVO{
    productId: number;
    productOrderItemNumber: number;
    productSalePrice: number;
}

// 确认收货视图
export interface OrderConfirmVO {
    categoryName: string;
    productId: number;
    productImageSrc: string;
    productName: string;
    productOrderCode: string;
    productOrderItemId: number;
    productOrderItemNumber: number;
    productOrderItemPrice: number;
    productOrderPayDate: string;
    productSalePrice: number
}