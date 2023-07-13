//package com.lt;
//
//import com.lt.mapper.UserMapper;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//
///**
// * @description:
// * @author: ~Teng~
// * @date: 2023/7/9 15:51
// */
//@SpringBootTest
//@Slf4j
//public class UserTest {
//    @Resource
//    private UserMapper userMapper;
//    @Resource
//    private MailUtils mailUtils;
//
//    @Test
//    void testFindAll() {
//        userMapper.selectList(null).forEach(System.out::println);
//    }
//
//    @Test
//    void testSendEmail() {
//        try {
//            mailUtils.sendCode("2300858713@qq.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//}
