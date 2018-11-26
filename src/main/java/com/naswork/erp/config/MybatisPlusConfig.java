package com.naswork.erp.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: MybatisPlusConfig
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-22 10:39:12
 **/

@Configuration
@MapperScan("com.naswork.erp.mapper.*")
public class MybatisPlusConfig {

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


//    @Bean
//    @ConfigurationProperties("spring.datasource.type." )
//    public DataSource dataSource() {
//        return DruidDataSourceBuilder
//                .create()
//                .build();
//    }

}



