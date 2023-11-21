package com.grungapda.backend.config;

import com.grungapda.backend.jwt.JwtAccessDeniedHandler;
import com.grungapda.backend.jwt.JwtAuthenticationEntryPoint;
import com.grungapda.backend.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                //토큰사용시 CSRF 설정 Disable

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // exception handling

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 세션을 사용하지 않기 때문에 STATELESS로 설정

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 단순 접근
                .antMatchers("/api/v1/authentication/**").permitAll() // 로그인
                .antMatchers("/api/v1/users/byUserNo/{userNo}**").permitAll() // 회원 번호로 조회
                .antMatchers("/api/v1/fileUpload/**").permitAll()
                .antMatchers("/api/v1/findAllfiles/**").permitAll()
                .antMatchers("/api/v1/findParticipant/**").permitAll()
                .antMatchers("/api/v1/customize/**").permitAll()
                .antMatchers("/api/v1/users/byToken/{accessToken}**").permitAll() // 토큰으로 조회
                .antMatchers("/api/v1/users/email/{userEmail}/check/**").permitAll()
                .antMatchers("/api/v1/users/info/{userNo}**").permitAll()
                .antMatchers("/api/v1/users/nickname/{userNickname}/check/**").permitAll()
                .antMatchers("/api/v1/users/pwd/{userNo}**").permitAll()
                .antMatchers("/api/v1/authentication/login/**").permitAll()
                .antMatchers("/api/v1/authentication/logout/**").permitAll()
                .antMatchers("/api/v1/authentication/new/**").permitAll()
                .antMatchers(
                        "/swagger-ui/**",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/webjar/**"
                ).permitAll()
                .anyRequest().authenticated() // 그 외 인증 없이 접근X
                .and()
                .apply(new JwtConfig(tokenProvider)); // JwtFilter를 addFilterBefore로 등록했던 JwtConfig class 적용


        return httpSecurity.build();
    }
}
