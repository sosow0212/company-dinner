package com.company.dinner.auth.controller.interceptor;

import com.company.dinner.auth.controller.support.AuthenticationContext;
import com.company.dinner.auth.exception.exceptions.LoginInvalidException;
import com.company.dinner.auth.infrastructure.JwtTokenProvider;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class LoginInterceptorTest {

    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse res = mock(HttpServletResponse.class);

    @Test
    void token이_없다면_예외를_발생한다() throws Exception {
        // given
        LoginInterceptor loginInterceptor = new LoginInterceptor(
                new JwtTokenProvider(),
                new AuthenticationContext()
        );

        when(req.getHeader("any")).thenReturn(null);

        // when
        assertThatThrownBy(() -> loginInterceptor.preHandle(req, res, new Object()))
                .isInstanceOf(LoginInvalidException.class);
    }
}
