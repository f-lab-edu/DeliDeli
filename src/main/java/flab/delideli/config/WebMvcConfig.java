package flab.delideli.config;

import flab.delideli.interceptor.LoginInterceptor;
import flab.delideli.resolver.CurrentUserHandlerMethodArgumentResolver;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] EXCLUDE_PATHS= {"/users", "/login", "/logout", "/{userId}/duplicate", "/shops", "/shops/search", "/shops/{shopId}"};
    
    private final LoginInterceptor loginInterceptor;
    private final CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/{userId}")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserHandlerMethodArgumentResolver);
    }

}