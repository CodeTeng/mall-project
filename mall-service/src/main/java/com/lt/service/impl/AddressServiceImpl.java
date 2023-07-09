package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Address;
import com.lt.mapper.AddressMapper;
import com.lt.service.AddressService;
import com.lt.vo.AddressVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author teng
 * @description 针对表【address】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {
    @Resource
    private AddressMapper addressMapper;

    @Override
    public List<AddressVO> getAddressRegion(String addressAreaId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("addressRegionId", addressAreaId);
        queryWrapper.ne("addressAreaId", addressAreaId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(addressList)) {
            return new ArrayList<>();
        }
        return addressList.stream().map(address -> {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(address, addressVO);
            return addressVO;
        }).collect(Collectors.toList());
    }
}




