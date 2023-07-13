package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.ProductOrderItem;
import com.lt.vo.cart.CartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author teng
 * @description 针对表【productOrderItem】的数据库操作Mapper
 * @createDate 2023-07-09 11:29:57
 * @Entity generator.domain.ProductOrderItem
 */
@Repository
public interface ProductOrderItemMapper extends BaseMapper<ProductOrderItem> {
    /**
     * 根据用户id获取购物车信息
     *
     * @param userId 用户id
     * @return 购物车信息
     */
    List<CartVO> getMyCart(@Param("userId") Integer userId);
}




