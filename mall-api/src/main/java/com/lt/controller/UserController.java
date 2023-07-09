package com.lt.controller;

import com.lt.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 15:46
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


}
