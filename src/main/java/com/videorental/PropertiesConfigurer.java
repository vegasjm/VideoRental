package com.videorental;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Configuration
@EnableSwagger2
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

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:"+System.getProperty ("user.home")+"\\sqlitedb.db");
        dataSourceBuilder.username("");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver()  {
        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
        resolver.setPrefix ("/WEB-INF/jsp/");
        resolver.setSuffix (".jsp");
        resolver.setViewClass (JstlView.class);
        return resolver;
    }

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfo(
                "Video Rentals API made With  Apache Tomcat 7/SQLLite Database",
                "Here you will find the endpoints to manage our VideoClub",
                "1.0.0",
                "",
                "jordy_poseydon@hotmail.es",
                null,
                null
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(pathsAPI())
                .build()
                .apiInfo(apiInfo);
    }
    private Predicate<String> pathsAPI() {
        return or(regex("/api.*"));
    }
}