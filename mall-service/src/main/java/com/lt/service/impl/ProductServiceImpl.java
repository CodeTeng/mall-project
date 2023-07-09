package com.lt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Product;
import com.lt.mapper.ProductMapper;
import com.lt.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【product】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

}




