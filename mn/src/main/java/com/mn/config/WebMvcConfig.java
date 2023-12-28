package com.mn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}") //설정파일(properties)의 값을 주입받을 때 사용
    String uploadPath;

    @Value("${resource.handler}")
    private String resourceHandler;

    @Value("${resource.location}")
    private String resourceLocation;

    @Override
    //정적리소스(css, js, img)에 대한 요청을 처리하는 방법
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**",resourceHandler)   //브라우저에 입력한 url이 /images로 시작하면 파일을 읽어오도록 함
                .addResourceLocations(uploadPath, resourceLocation);  //저장된 파일을 읽어올 경로
    }
}

