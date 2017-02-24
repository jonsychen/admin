package com.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.admin.interfaces.intercept.NavMenuActiveInterceptor;


/**
 * @author Jonsy
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/to-login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/403").setViewName("error/403");
        registry.addViewController("/401").setViewName("error/401");
        registry.addViewController("/404").setViewName("error/404");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NavMenuActiveInterceptor());
    }
}
