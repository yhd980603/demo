package com.example.demo.service;

import com.example.demo.model.Video;

import java.util.List;

/**
 * 视频业务类接口
 */
public interface VideoService {

    List<Video> findAll();
    Video findById(int id);
    int update(Video video);
    int delete(int id);
    int save(Video video);
}
