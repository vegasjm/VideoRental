package com.videorental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.videorental")
@EnableAutoConfiguration
public class VideoRentalApplication extends SpringBootServletInitializer {

	private static Class applicationClass = VideoRentalApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(VideoRentalApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}
