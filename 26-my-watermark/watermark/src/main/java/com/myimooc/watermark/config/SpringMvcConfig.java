package com.myimooc.watermark.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 项目配置类
 * @author ZhangCheng on 2017-07-21
 *
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{
	
	/**
     * 配置视图解析器
     * @return
     */
    @Bean
    public FreeMarkerViewResolver getFreeMarkerViewResolver(){
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        
        freeMarkerViewResolver.setOrder(1);
        freeMarkerViewResolver.setSuffix(".html");
        freeMarkerViewResolver.setCache(false);
        freeMarkerViewResolver.setRequestContextAttribute("request");
        freeMarkerViewResolver.setContentType("text/html;charset=utf-8");
        freeMarkerViewResolver.setViewClass(FreeMarkerView.class);
        
        return freeMarkerViewResolver;
    }
    
    /**
     * 配置静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    
    /**
     * 配置FreeMarker
     * @return
     */
    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "5");
        settings.setProperty("url_escaping_charset", "UTF-8");
        settings.setProperty("defaultEncoding", "UTF-8");
        settings.setProperty("whitespace_stripping", "true");
        settings.setProperty("boolean_format", "true,false");
        settings.setProperty("number_format", "0.##########");
        settings.setProperty("locale", "zh_CN");
        settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.setProperty("date_format", "yyyy-MM-dd");
        settings.setProperty("time_format", "HH:mm:ss");
        settings.setProperty("tag_syntax", "square_bracket");
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_exception_handler", "ignore");
        settings.setProperty("auto_import", "/common/common.ftl as common");
        freeMarkerConfigurer.setFreemarkerSettings(settings);
        
        return freeMarkerConfigurer;
    }
}
