package com.example.demo;

import com.example.demo.mapper.VideoMapper;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.service.VideoService;
import com.example.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoMapper videoMapper;

    @Test
    void testGeneJwt() {
        User user=new User();
        user.setId(999);
        user.setHeadImg("www.xdclass.net");
        user.setName("yhd");
        String token=JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    @Test
    void testCheck(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5aGQiLCJpZCI6OTk5LCJuYW1lIjoieWhkIiwiaW1nIjoid3d3LnhkY2xhc3MubmV0IiwiaWF0IjoxNTg0ODM3MDE4LCJleHAiOjE1ODU0NDE4MTh9.6F6EF9Rhq4eFLqxB-7dbJ6vdHyYsDxoj67_F5Rzwb1k";
        Claims claims =JwtUtils.checkJWT(token);
        if (claims!=null){
            String name=(String)claims.get("name");
            int id=(Integer) claims.get("id");
            String img=(String)claims.get("img");
            System.out.println(name);
            System.out.println(id);
            System.out.println(img);
        }
    }

    @Test
    void findAll(){
        List<Video> list=videoService.findAll();
        assertNotNull(list);
        for (Video video : list) {
            System.out.println(video.getTitle());
        }
    }
}
