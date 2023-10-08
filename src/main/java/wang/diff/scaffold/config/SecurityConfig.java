package wang.diff.scaffold.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wang.diff.scaffold.common.component.JwtAuthenticationFilter;
import wang.diff.scaffold.common.component.MyAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //配置身份失效时访问接口和跳转路径的处理
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
        // 设置自定义请求需要验证
        http
                .csrf().disable() // 禁用 csrf
                .cors().disable() // 禁用 cors
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //不使用 session
                .and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/common/**").permitAll()
                        .requestMatchers("/session/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated() // 任何请求都需要安全认证;
                )
                .httpBasic().disable();
        return http.build();
    }
}
