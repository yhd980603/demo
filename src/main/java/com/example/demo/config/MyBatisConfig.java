package com.example.demo.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * MyBatis分页插件配置
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        //设置为true时，会将RowBounds第一参数offset当成pageNum页面使用
        properties.setProperty("offsetAsPageNum","true");
        //设置为true时，使用RowBounds分页进行count查询
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
