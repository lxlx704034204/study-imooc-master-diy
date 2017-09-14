package com.myimooc.wxdevaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 慕课网-初识Java微信公众号开发 
 * 开发模式接入
 * @author ZhangCheng on 2017-08-11
 *
 */
@SpringBootApplication
public class AccessWebApp extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(AccessWebApp.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AccessWebApp.class);
    }
}
