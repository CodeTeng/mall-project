package com.lt;

import com.lt.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 15:51
 */
@SpringBootTest
public class UserTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void testFindAll() {
        userMapper.selectList(null).forEach(System.out::println);
    }
}
