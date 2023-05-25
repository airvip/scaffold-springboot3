package wang.diff.scaffold.common.component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import wang.diff.scaffold.common.constant.JwtConstants;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

public class JwtTools {


    public static String createToken(String userId, List<String> role) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.JWT_SECRET.getBytes());
        String token = Jwts.builder()
                .setHeaderParam("AUT", JwtConstants.TOKEN_TYPE)
                .setIssuer(JwtConstants.TOKEN_ISSUER) // iss: 该JWT的签发者，是否使用是可选的；
                .setSubject(userId) // sub: 该JWT所面向的用户，是否使用是可选的；
                .setAudience(JwtConstants.TOKEN_AUDIENCE) // aud: 接收该JWT的一方，是否使用是可选的；
                .setIssuedAt(new Date()) // iat(issued at): 在什么时候签发的(UNIX时间)，是否使用是可选的；
                .setExpiration(new Date(System.currentTimeMillis() + (JwtConstants.TOKEN_EXPIRATION * 1000)) ) // exp(expires): 什么时候过期，这里是一个Unix时间戳，是否使用是可选的；
                .claim("role", role)
                .signWith(secretKey).compact();
        return JwtConstants.TOKEN_PREFIX + token;
    }

}
