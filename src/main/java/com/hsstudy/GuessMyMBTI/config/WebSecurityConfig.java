package com.hsstudy.GuessMyMBTI.config;


import com.hsstudy.GuessMyMBTI.api.testJwt.config.JwtSecurityConfig;
import com.hsstudy.GuessMyMBTI.api.testJwt.jwt.JwtAccessDeniedHandler;
import com.hsstudy.GuessMyMBTI.api.testJwt.jwt.JwtAuthenticationEntryPoint;
import com.hsstudy.GuessMyMBTI.api.testJwt.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노케이션을 메소드단위로 추가하기 위해 사용
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * jwt 사용을 위해 추가한 것들
     */
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public WebSecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // DB 때문에 사용함
//        web
//                .ignoring()
//                .antMatchers("/h2 console/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 토큰을 사용할 것이기 때문에 csrf 설정은 disable

                // ExceptionalHandeling 을 위해 우리가 만든 jwt로 추가해주기
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션을 사용하지 않기 때문에 Stateless 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // security 관련 접근 제한 허용여부
                .and()
                .authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한 설정
                .antMatchers("/api/hello").permitAll() // /api/hello 에 대한 요청은 인증없이 접근 허용한다는 의미
                // 로그인, 회원가입은 토큰이 없는 상태에서 요청이 들어오기 때문에 모두 permit all
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/**").permitAll()
                // MBTI 설명, 이름에 관련한 내용에 대해서는 모두 허용
                .antMatchers("/result/all").permitAll()
                // 토큰 관련 부분
                .antMatchers("/token/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated() // 나머지 요청들에 대해서는 모두 인증을 받아야 한다는 의미

                // JwtFilter 를 addFilterBefore 로 등록했던 jwtSecurityConfig 클래스도 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

}
