package com.company.dinner.auth.config;

import com.company.dinner.auth.controller.interceptor.LoginValidCheckerInterceptor;
import com.company.dinner.auth.controller.interceptor.ParseMemberIdFromTokenInterceptor;
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
import static com.company.dinner.auth.controller.interceptor.HttpMethod.OPTIONS;

@RequiredArgsConstructor
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final AuthArgumentResolver authArgumentResolver;
    private final ParseMemberIdFromTokenInterceptor parseMemberIdFromTokenInterceptor;
    private final LoginValidCheckerInterceptor loginValidCheckerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(parseMemberIdFromTokenInterceptor());
        registry.addInterceptor(loginValidCheckerInterceptor());
    }

    private HandlerInterceptor parseMemberIdFromTokenInterceptor() {
        return new PathMatcherInterceptor(parseMemberIdFromTokenInterceptor)
                .excludePathPattern("/**", OPTIONS);
    }

    private HandlerInterceptor loginValidCheckerInterceptor() {
        return new PathMatcherInterceptor(loginValidCheckerInterceptor)
                .addPathPatterns("/need-permit-to-login-here", ANY)
                .excludePathPattern("/**", OPTIONS);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }
}
