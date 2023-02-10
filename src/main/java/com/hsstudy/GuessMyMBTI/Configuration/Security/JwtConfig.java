package com.hsstudy.GuessMyMBTI.Configuration.Security;

import com.hsstudy.GuessMyMBTI.Oauth.Token.AuthTokenProvider;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value(staticConstructor = "${jwt.secret}")
    private String secret;

    @Bean
    public AuthTokenProvider jwtProvider() {
        return new AuthTokenProvider(secret);
    }
}
