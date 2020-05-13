package com.yyc.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//@Configuration
// public class WebConfig  extends WebMvcConfigurationSupport {
//
//     @Override
//     public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        super.configureMessageConverters(converters);
//        converters.add(0, new MappingJackson2HttpMessageConverter());
//     }
// }