package com.company.dinner.auth.controller.interceptor;

import com.company.dinner.auth.domain.TokenProvider;
import com.company.dinner.auth.controller.support.AuthenticationContext;
import com.company.dinner.auth.controller.support.AuthenticationExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;
    private final AuthenticationContext authenticationContext;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String token = AuthenticationExtractor.extract(request)
                .orElseThrow(() -> new IllegalArgumentException("로그인한 정보가 없습니다"));

        Long memberId = tokenProvider.extract(token);
        authenticationContext.setAuthentication(memberId);

        return true;
    }
}
