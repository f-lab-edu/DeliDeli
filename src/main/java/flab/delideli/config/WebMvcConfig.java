package flab.delideli.config;

import flab.delideli.interceptor.LoginInterceptorImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] EXCLUDE_PATHS= {"/users", "/login", "/logout", "/{userid}/duplicated"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptorImpl loginInterceptor = new LoginInterceptorImpl();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/{userId}")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}
