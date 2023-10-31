package com.company.dinner.auth.config;

import com.company.dinner.auth.controller.interceptor.LoginCheckerInterceptor;
import com.company.dinner.auth.controller.interceptor.LoginInterceptor;
import com.company.dinner.auth.controller.interceptor.PathMatcherInterceptor;
import com.company.dinner.auth.controller.support.resolver.AuthArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.company.dinner.auth.controller.interceptor.HttpMethod.ANY;
import static com.company.dinner.auth.controller.interceptor.HttpMethod.GET;
import static com.company.dinner.auth.controller.interceptor.HttpMethod.OPTIONS;

@RequiredArgsConstructor
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final AuthArgumentResolver authArgumentResolver;
    private final LoginCheckerInterceptor loginCheckerInterceptor;
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckerInterceptor());
        registry.addInterceptor(loginInterceptor());
    }

    private HandlerInterceptor loginCheckerInterceptor() {
        return new PathMatcherInterceptor(loginCheckerInterceptor)
                .excludePathPattern("/**", OPTIONS)
                .addPathPatterns("/tests/**", GET);
    }

    private HandlerInterceptor loginInterceptor() {
        return new PathMatcherInterceptor(loginInterceptor)
                .excludePathPattern("/**", ANY, OPTIONS);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }
}
