package com.videorental.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by usuario on 4/11/2017.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public HttpRequestHandlerServlet updateServlet()
    {
        return new HttpRequestHandlerServlet();
    }

    @Bean
    public ServletRegistrationBean updateServletRegistrationBean()
    {
        return new ServletRegistrationBean(updateServlet(), "/videorental");
    }

}