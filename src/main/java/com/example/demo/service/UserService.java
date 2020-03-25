package com.example.demo.service;

import com.example.demo.model.User;

/**
 * 用户业务接口类
 */
public interface UserService {
    User saveWeChatUser(String code);
}
