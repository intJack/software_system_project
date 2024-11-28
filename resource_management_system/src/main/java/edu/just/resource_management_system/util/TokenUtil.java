package edu.just.resource_management_system.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    // 设置失效时间
    private static final long EXPIRE_DATE = 60 * 60 * 1000; // 60 minutes
    // Token密钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    public static String token(String username, String password) {
        String token = "";
        try {
            // 失效截止时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            // 密钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置请求头信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 生成 Token
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
    判断token是否过期了.
    true --没过期
    false --过期了 重新登录
     */
    public static boolean IsNotExpire(String token){
        //得到令牌失效的时间
        long expires = JWT.decode(token).getExpiresAt().getTime();
        return expires >= System.currentTimeMillis()?true:false;
    }

    public static boolean validateToken(String token){
        return (verify(token) && IsNotExpire(token));
    }

    public static void main(String[] args) {
        String username = "zhansan";
        String password = "123";
        String token = token(username, password);
        System.out.println(validateToken(token));
//        String token2 = token("zhansan",password)+'a';
//        System.out.println(verify(token2));
//        System.out.println(IsNotExpire(token));
//        System.out.println(token);
//        boolean isValid = verify(token);
//        System.out.println(isValid);
    }
}