package com.lt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.ProductOrderItem;
import com.lt.mapper.ProductOrderItemMapper;
import com.lt.service.ProductOrderItemService;
import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【productorderitem】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductOrderItemServiceImpl extends ServiceImpl<ProductOrderItemMapper, ProductOrderItem>
        implements ProductOrderItemService {

}




