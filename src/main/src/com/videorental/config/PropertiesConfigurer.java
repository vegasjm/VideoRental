package com.videorental.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 4/11/2017.
 */
@Configuration
public class PropertiesConfigurer {


    @Bean
    public PropertyPlaceholderConfigurer properties() {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreUnresolvablePlaceholders(false);
        ppc.setIgnoreResourceNotFound(true);

        final List<Resource> resourceLst = new ArrayList<Resource>();
        resourceLst.add(new ClassPathResource("VideoRental.properties"));
        resourceLst.add(new ClassPathResource("applicationQueries.xml"));

        ppc.setLocations(resourceLst.toArray(new Resource[]{}));

        return ppc;
    }
}