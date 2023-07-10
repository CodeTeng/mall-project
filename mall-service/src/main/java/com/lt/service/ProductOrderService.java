package com.lt.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.entity.ProductOrder;
import com.lt.vo.ProductOrderVO;

/**
 * @author teng
 * @description 针对表【productOrder】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface ProductOrderService extends IService<ProductOrder> {

    /**
     * 获取我的所有订单
     *
     * @param productSearchDTO 搜索DTO
     * @param type             获取类型(-1 获取所有)
     * @return 所有订单信息
     */
    Page<ProductOrderVO> getMyAllOrder(ProductSearchDTO productSearchDTO, Integer type);
}
