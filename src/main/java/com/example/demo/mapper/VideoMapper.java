package com.example.demo.mapper;

import com.example.demo.model.Video;
import com.example.demo.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * video数据访问层
 */
public interface VideoMapper {

    @Select("select * from video")
    List<Video> findAll();

    @Select("select * from video where id=#{id}")
    Video findById(int id);

    @UpdateProvider(type = VideoProvider.class,method = "updateVideo")
    int update(Video video);

    @Delete("delete from video where id=#{id}")
    int delete(int id);

    @Insert("INSERT INTO `video` (`title`,`summary`,`cover_img`,`view_num`,`price`,`create_time`,`online`,`point`) VALUES" +
            "(#{title},#{summary},#{coverImg},#{viewNum},#{price},#{createTime},#{online},#{point})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")//保存对象，获取数据库自增id
    int save(Video video);
}
