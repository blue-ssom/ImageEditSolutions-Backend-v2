package com.example.api_server.global;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final Key key;
    private final long TokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}") long tokenExpTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.TokenExpTime = tokenExpTime;
    }

    // JWT 만들어주는 함수
    public String createToken(Long id, String username) {
        return Jwts.builder()
                .subject(String.valueOf(id))
                .claim("username", username)
                .setIssuedAt(Date.from(Instant.now())) // 토큰 발급 시간
                .setExpiration(Date.from(Instant.now().plusSeconds(TokenExpTime))) // 만료 시간
                .signWith(key) // 키로 서명
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject(); // 토큰에서 subject 추출 (username)
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key) // 서명 키 설정
                    .build() // 파서 생성
                    .parseClaimsJws(token); // 토큰 파싱 및 검증
            return true; // 검증 성공
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN); // 유효하지 않은 JWT
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN); // 만료된 JWT
        } catch (UnsupportedJwtException e) {
            throw new CustomException(ErrorCode.UNSUPPORTED_TOKEN); // 지원하지 않는 JWT
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.EMPTY_CLAIMS); // 빈 클레임
        }
    }

}
