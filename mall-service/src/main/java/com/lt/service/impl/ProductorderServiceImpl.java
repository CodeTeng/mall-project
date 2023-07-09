package com.lt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.ProductOrder;
import com.lt.mapper.ProductOrderMapper;
import com.lt.service.ProductorderService;
import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【productOrder】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductorderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder>
        implements ProductorderService {

}




