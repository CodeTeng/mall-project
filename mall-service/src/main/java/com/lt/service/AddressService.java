package com.lt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.entity.Address;
import com.lt.vo.AddressVO;

import java.util.List;

/**
 * @author teng
 * @description 针对表【address】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface AddressService extends IService<Address> {

    /**
     * 获取对应地址名称
     *
     * @param addressAreaId 地址id
     * @return 对应下级地址名称
     */
    List<AddressVO> getAddressRegion(String addressAreaId);
}
