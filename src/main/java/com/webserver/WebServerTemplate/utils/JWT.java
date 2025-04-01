package com.webserver.WebServerTemplate.utils;

import com.webserver.WebServerTemplate.core.SessionSystem;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWT {

    private static final long expire = 7200000;   // 过期时间,2个小时
    private static String SECRET_KEY = "HeFeiSiQinInformationTechnologyV";    //32位密钥
    @Value("${JWT.enabled}")
    private boolean jwtEnabled;

    // 创建Token
    public static String GenerateToken(String uid){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expire);

        // 携带载荷
        Integer session = SessionSystem.GenerateSession();
        Map<String, Object>claims = new HashMap<>();
        claims.put("Session",session);

        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(uid)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }
    // 验证 JWT 的方法
    // 此处只检查Token是否过期，数据是否被篡改请自行检查。
    public static Claims validateJWT(String token) {
        try {
            // 解析 JWT，验证签名并返回其中的 Claims
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY) // 设置密钥
                    .parseClaimsJws(token) // 解析 JWT
                    .getBody(); // 获取 JWT 的内容（Claims）

            // 如果 JWT 已过期，会抛出异常
            if (claims.getExpiration().before(new Date())) {
                throw new Exception("JWT expired（已过期）");
            }

            return claims; // 返回解析后的 Claims
        } catch (Exception e) {
            System.out.println("Invalid JWT or expired JWT: " + e.getMessage());
            return null; // 如果解析失败，则返回 null
        }
    }
}
