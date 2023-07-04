package com.cc.model.file;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class files implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    	 String fileLocation;
         
         fileLocation = "file:///Users//files/";
     

     registry.addResourceHandler("/Downloads/**")
     .addResourceLocations(fileLocation)
     .setCacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES));
 }
}