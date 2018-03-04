package com.example.springbootdemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * jwt工具类
 */
public class JwtHelper {
    /**
     * JWT总共包含三个部分
     * 1.头部
     * {
     *     'typ':"JWT"
     *     "alg":"HS256"
     * }
     * 2.载荷（payload）
     * 3.签证（sinature)
     */

    //构造JWT
    public static String createJWT(String name,String userId,String role,String audience,
                                   String issuer,long TTLMilis,String base64Security)
    {
        //获得算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //获得当前时间，为后来设置过期时间
        long nowMilis = System.currentTimeMillis();
        Date nowDate = new Date(nowMilis);

        //生成密钥
        byte[] bytes  = DatatypeConverter.parseBase64Binary(base64Security);
        Key key = new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
        JwtBuilder jwtBuilder = Jwts.builder().setHeaderParam("typ","JWT")
                .claim("role",role)
                .claim("unique_name",name)
                .claim("userId",userId)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm,key);

        //添加过期时间
        if(TTLMilis>=0){
            long expiresMilis = nowMilis+TTLMilis;
            Date expiresDate = new Date(expiresMilis);
            jwtBuilder.setExpiration(expiresDate).setNotBefore(nowDate);

        }
        return jwtBuilder.compact();
    }

    //解析JWT
    public static Claims parseJWT(String jsonWebToken,String base64Security)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                .parseClaimsJws(jsonWebToken).getBody();

        return claims;
    }
}
