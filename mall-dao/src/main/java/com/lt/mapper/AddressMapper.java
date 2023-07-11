package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.Address;
import com.lt.vo.AddressVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author teng
 * @description 针对表【address】的数据库操作Mapper
 * @createDate 2023-07-09 11:29:57
 * @Entity generator.domain.Address
 */
@Repository
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 获取省份或直辖市
     *
     * @return 对应省份或直辖市
     */
    List<AddressVO> getProvince();
}




