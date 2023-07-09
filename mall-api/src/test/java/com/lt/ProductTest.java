package com.lt;

import com.lt.entity.Product;
import com.lt.mapper.ProductMapper;
import com.lt.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 商品测试
 * @author: ~Teng~
 * @date: 2023/7/9 15:15
 */
@SpringBootTest
@Slf4j
public class ProductTest {
    @Resource
    private ProductMapper productMapper;

    @Test
    void testFindAll() {
        List<Product> productList = productMapper.selectList(null);
        productList.forEach(System.out::println);
    }
}
