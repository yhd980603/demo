package com.example.demo.controller;

import com.example.demo.model.Video;
import com.example.demo.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 分页接口
     * @param page 当前第几页，默认是第一页
     * @param size  每页显示几条
     * @return
     */
    @GetMapping("/page")
    public Object pageVideo(@RequestParam(value = "page",defaultValue = "1")int page,
                            @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(page, size);
        List<Video>videos=videoService.findAll();
        PageInfo<Video>pageInfo=new PageInfo<>(videos);
        Map<String,Object>data=new HashMap<>();
        data.put("total_size",pageInfo.getTotal());
        data.put("total_page",pageInfo.getPages());
        data.put("current_page",page);
        data.put("data",pageInfo.getList());
        return data;
    }

    /**
     * 根据Id找视频
     * @param videoId
     * @return
     */
    @GetMapping("/find_by_id")
    public Object findById(@RequestParam(value = "video_id",required = true)int videoId){
        return videoService.findById(videoId);
    }
}
