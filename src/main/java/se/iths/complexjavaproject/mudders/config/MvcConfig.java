package se.iths.complexjavaproject.mudders.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        //registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("playercharacter");
        registry.addViewController("/playercharacter").setViewName("playercharacter");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        //registry.addViewController("/perform_login").setViewName("perform_login");

    }

}
