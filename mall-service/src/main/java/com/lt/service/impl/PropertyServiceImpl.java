package com.lt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Property;
import com.lt.mapper.PropertyMapper;
import com.lt.service.PropertyService;

import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【property】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property>
        implements PropertyService {

}




