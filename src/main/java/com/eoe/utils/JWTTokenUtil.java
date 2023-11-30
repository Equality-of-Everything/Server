package com.eoe.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.eoe.entity.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/28 19:48
 * @Decription : jwt工具类
 */

@Component
@Slf4j
public class JWTTokenUtil {

    /**
     * 密钥
     * 用于Jwt签名
     */
    private static final String SECRET = "EqualityOfEverything";

    /**
     * 过期时间
     * 单位：秒
     */
    private static final long EXPIRATION = 10L;

    /**
     * @param userLogin
     * @return
     * @describtion: 生成用户token，设置token超时时间
     */
    public static String generateToken(UserLogin userLogin) {
        System.out.println("TokenUtil:"+userLogin.getUserId());
        // 过期时间
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> claims = new HashMap<>();
        // alg表示加密算法
        claims.put("alg", "HS256");
        // typ表示token类型
        claims.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(claims)
                .withClaim("username", userLogin.getUsername())
                .withClaim("password", userLogin.getPassword())
                .withExpiresAt(expirationDate)
                .withIssuedAt(new java.util.Date())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * @param token
     * @return
     * 校验Token是否正确
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);

            //decodedJWT.getClaim("属性").asString()  获取负载中的属性值

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("token解码异常");
            // 解码异常则抛出异常
            return null;
        }
        return jwt.getClaims();
    }
}
