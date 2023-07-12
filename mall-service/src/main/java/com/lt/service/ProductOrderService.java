package com.lt.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.common.PageRequest;
import com.lt.entity.ProductOrder;
import com.lt.vo.order.ProductOrderVO;

/**
 * @author teng
 * @description 针对表【productOrder】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface ProductOrderService extends IService<ProductOrder> {

    /**
     * 获取我的所有订单
     *
     * @param pageRequest 分页参数
     * @param status      获取类型
     * @return 所有订单信息
     */
    Page<ProductOrderVO> getMyAllOrder(PageRequest pageRequest, Integer status);

    /**
     * 修改订单状态
     *
     * @param productOrderId     订单id
     * @param productOrderStatus 订单状态
     */
    void updateOrderStatus(Integer productOrderId, Integer productOrderStatus);
}
