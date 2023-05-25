package wang.diff.scaffold.common.constant;

public class JwtConstants {
    public static final String AUTH_LOGIN_URL = "/api/token";
    public static final String JWT_SECRET = "iQCfwM6Zl12b7T2CjPK3ZFaVFWqoydF1cDnQyb5ed20";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "www.diff.wang";
    public static final String TOKEN_AUDIENCE = "secure-app";
    public static final int TOKEN_EXPIRATION = 7200;

    private JwtConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
