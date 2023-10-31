package com.company.dinner.auth.infrastructure;

import com.company.dinner.auth.domain.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@NoArgsConstructor
@Component
public class JwtTokenProvider implements TokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-period}")
    private int expirationPeriod;

    private Key key;

    @PostConstruct
    private void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public String create(final Long id) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        return createToken(claims);
    }

    private String createToken(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt())
                .setExpiration(expiredAt())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Date issuedAt() {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private Date expiredAt() {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.plusHours(expirationPeriod)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    @Override
    public Long extract(final String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id", Long.class);
        } catch (SecurityException e) {
            throw new IllegalArgumentException("서명을 확인하지 못했습니다");
        } catch (MalformedJwtException e) {
            throw new IllegalArgumentException("토큰의 길이 및 형식이 올바르지 않습니다");
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("이미 만료된 토큰입니다");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰입니다");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다");
        }
    }
}
