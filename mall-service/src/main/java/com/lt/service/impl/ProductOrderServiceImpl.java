package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.common.ErrorCode;
import com.lt.common.PageRequest;
import com.lt.entity.ProductOrder;
import com.lt.exception.BusinessException;
import com.lt.mapper.ProductOrderMapper;
import com.lt.service.ProductOrderService;
import com.lt.utils.UserThreadLocalUtil;
import com.lt.vo.order.ProductOrderVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author teng
 * @description 针对表【productOrder】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder>
        implements ProductOrderService {
    @Resource
    private ProductOrderMapper productOrderMapper;

    @Override
    public Page<ProductOrderVO> getMyAllOrder(PageRequest PageRequest, Integer status) {
        // 获取当前用户id
        Integer userId = UserThreadLocalUtil.getUserId();
        long current = PageRequest.getCurrent();
        long pageSize = PageRequest.getPageSize();
        List<ProductOrderVO> productOrderVOList = productOrderMapper.getMyAllOrder(status, userId);
        Page<ProductOrderVO> page = new Page<>(current, pageSize, productOrderVOList.size());
        page.setRecords(productOrderVOList);
        return page;
    }

    @Override
    public void updateOrderStatus(Integer productOrderId, Integer productOrderStatus) {
        ProductOrder productOrder = productOrderMapper.selectById(productOrderId);
        if (productOrder == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该订单不存在");
        }
        productOrder.setProductOrderStatus(productOrderStatus);
        productOrderMapper.updateById(productOrder);
    }
}




