package wang.diff.scaffold.common.component;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import wang.diff.scaffold.common.constant.JwtConstants;

import java.io.IOException;
import java.util.List;

/**
 * 自定义 jwt 全局过滤器
 * 1. 不存在 token 直接放行
 * 2. 携带 token，将用户信息添加到 security 上下文中
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (null == authentication) {
//            String requestURI = request.getRequestURI();
            filterChain.doFilter(request, response);
//            log.info("request uri end >>> {}", requestURI);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtConstants.TOKEN_HEADER);
        if (StringUtils.hasText(token) && token.startsWith(JwtConstants.TOKEN_PREFIX)) {
            try {
                Jws<Claims> parsedToken = Jwts.parserBuilder()
                        .setSigningKey(JwtConstants.JWT_SECRET.getBytes())
                        .build()
                        .parseClaimsJws(token.replace(JwtConstants.TOKEN_PREFIX, ""));

                String username = parsedToken.getBody().getSubject();

                List<SimpleGrantedAuthority> role = ((List<?>) parsedToken.getBody()
                        .get("role")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .toList();

                if (StringUtils.hasText(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, role);
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
            }
        }
        return null;
    }
}
