package com.webserver.WebServerTemplate.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWT {

    private static final long expire = 7200000;   // 过期时间,2个小时
    private static String secretKey = "HeFeiSiQinInformationTechnologyV";    //32位密钥
    @Value("${JWT.enabled}")
    private boolean jwtEnabled;

    // 创建Token
    public static String GenerateToken(String uid){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(uid)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    //  解析TOKEN
    public static Claims GetClaims(String token){
        return (Claims) Jwts.parser()
                .setSigningKey(secretKey)
                .parse(token)
                .getBody();
    }
    public static boolean isExpired(Claims claims){
        try {
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        }catch (Exception e){
            return true;    // 发生错误视为过期
        }
    }
}
