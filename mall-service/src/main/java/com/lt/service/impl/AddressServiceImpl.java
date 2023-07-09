package com.lt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Address;
import com.lt.mapper.AddressMapper;
import com.lt.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【address】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {
}




