package com.lihainuo.bubulog.auth.utils;

import com.lihainuo.bubulog.auth.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 *     JWT 工具类
 * </p>
 * @author lihainuo
 * @url www.lihainuo.com
 */

@Component
public class JwtUtil {

    @Resource
    private JwtConfig jwtConfig;

    public String createToken(HashMap<String, Object> map) {
        Claims claims = Jwts.claims();
        claims.putAll(map);
        return createToken(claims);
    }

    public String createToken(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getTtl()))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getKey().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getKey().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 校验token  
     *     * @param token token  
     * @return 校验结果
     * Valid-合法  
     * Expired-过期  
     * Invalid-无效  
     * Malformed-结构不符合标准  
     * Invalid Signature-签名无效  
     */
    public String validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getKey().getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return "Valid";
        } catch (ExpiredJwtException e) {
            return "Expired";
        } catch (SignatureException e) {
            return "Invalid Signature";
        } catch (MalformedJwtException e) {
            return "Malformed";
        } catch (Exception e) {
            return "Invalid";
        }
    }


    public boolean isTokenExpired(String token) {
        try {
            Claims claims = parseToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

}