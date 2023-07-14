package com.lt.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.common.ErrorCode;
import com.lt.common.PageRequest;
import com.lt.dto.order.AddOrderDTO;
import com.lt.dto.order.AddOrderItemDTO;
import com.lt.entity.ProductOrder;
import com.lt.exception.BusinessException;
import com.lt.mapper.ProductOrderItemMapper;
import com.lt.mapper.ProductOrderMapper;
import com.lt.service.ProductOrderService;
import com.lt.utils.UserThreadLocalUtil;
import com.lt.vo.order.ProductOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private ProductOrderItemMapper productOrderItemMapper;

    @Override
    public List<ProductOrderVO> getMyAllOrder(PageRequest PageRequest, Integer status) {
        // 获取当前用户id
        Integer userId = UserThreadLocalUtil.getUserId();
        return productOrderMapper.getMyAllOrder(status, userId);
    }

    @Override
    public void updateOrderStatus(Integer productOrderId, Integer productOrderStatus) {
        ProductOrder productOrder = productOrderMapper.selectById(productOrderId);
        if (productOrder == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该订单不存在");
        }
        productOrder.setProductOrderStatus(productOrderStatus);
        if (productOrderStatus == 1) {
            // 投递时间
            productOrder.setProductOrderDeliveryDate(new Date());
        } else if (productOrderStatus == 2) {
            // 确认时间
            productOrder.setProductOrderConfirmDate(new Date());
        }
        productOrderMapper.updateById(productOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addOrder(AddOrderDTO addOrderDTO) {
        Integer userId = UserThreadLocalUtil.getUserId();
        ProductOrder productOrder = new ProductOrder();
        BeanUtils.copyProperties(addOrderDTO, productOrder);
        productOrder.setProductOrderStatus(0);
        productOrder.setProductOrderUserId(userId);
        String productOrderCode = DateUtil.format(new Date(), "yyyyMMddHHmmssSS");
        // 模拟订单Code和支付时间 后序支付时修改
        productOrder.setProductOrderCode(productOrderCode);
        productOrder.setProductOrderPayDate(new Date());
        // 添加订单
        productOrderMapper.insert(productOrder);
        Integer productOrderId = productOrder.getProductOrderId();
        // 添加订单项留言
        List<AddOrderItemDTO> addOrderItemDTOList = addOrderDTO.getAddOrderItemDTOList();
        for (AddOrderItemDTO addOrderItemDTO : addOrderItemDTOList) {
            Integer productOrderItemId = addOrderItemDTO.getProductOrderItemId();
            String productOrderItemUserMessage = addOrderItemDTO.getProductOrderItemUserMessage();
            if (StringUtils.isBlank(productOrderItemUserMessage)) {
                // 没有留言
                continue;
            }
            // 更新订单项
            productOrderItemMapper.updateOrderItem(productOrderItemId, productOrderId, productOrderItemUserMessage);
        }
        return productOrderId;
    }
}




