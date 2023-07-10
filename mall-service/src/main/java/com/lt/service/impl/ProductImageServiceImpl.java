package com.lt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.ProductImage;
import com.lt.mapper.ProductImageMapper;
import com.lt.service.ProductImageService;
import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【productimage】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage>
        implements ProductImageService {

}




