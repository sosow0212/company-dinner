package com.company.dinner.auth.controller.support;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuthenticationExtractor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    public static Optional<String> extract(final HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);

        if (!StringUtils.hasText(header)) {
            return Optional.empty();
        }

        return extractFromHeader(header.split(" "));
    }

    public static Optional<String> extractFromHeader(final String[] headerParts) {
        if (headerParts.length == 2 && headerParts[0].equals(BEARER)) {
            return Optional.ofNullable(headerParts[1]);
        }

        return Optional.empty();
    }
}
