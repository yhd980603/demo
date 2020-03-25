package com.example.demo.service.impl;

import com.example.demo.config.WeChatConfig;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveWeChatUser(String code) {
        String accessTokenUrl=String.format(WeChatConfig.getOpenAccessTokenUrl(),weChatConfig.getOpenAppid(),weChatConfig.getOpenAppsecret(),code);
        //获取access_token
        Map<String,Object>baseMap =HttpUtils.doGet(accessTokenUrl);
        if (baseMap==null||baseMap.isEmpty())return null;
        String accessToken=(String)baseMap.get("access_token");
        String openId=(String)baseMap.get("openid");
        User dbUser=userMapper.findByOpenid(openId);
        if (dbUser!=null){
            //更新用户，直接返回
            return dbUser;
        }
        //获取用户基本信息
        String userInfoUrl=String.format(WeChatConfig.getOpenUserInfoUrl(),accessToken,openId);
        //获取用户信息
        Map<String,Object>baseUserMap =HttpUtils.doGet(userInfoUrl);
        if (baseUserMap==null||baseUserMap.isEmpty())return null;
        String nickname=(String)baseMap.get("nickname");
        Double sexTemp=(Double) baseMap.get("sex");
        int sex=sexTemp.intValue();
        String province=(String)baseMap.get("province");
        String city=(String)baseMap.get("city");
        String country=(String)baseMap.get("country");
        String headimgurl=(String)baseMap.get("headimgurl");
        StringBuilder sb=new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress=sb.toString();
        try {
            //解决乱码
            nickname=new String(nickname.getBytes("ISO-8859-1"),"UTF-8");
            finalAddress=new String(finalAddress.getBytes("ISO-8859-1"),"UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        User user=new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(city);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.save(user);
        return user;
    }
}
