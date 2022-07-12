package com.example.dec2021springboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {


    // img/homer.jpg
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path ="file:///" + System.getProperty("user.home") + File.separator + "someImages" + File.separator;
        registry.addResourceHandler("img/**")
                .addResourceLocations(path);

    }
}
