package dev.gergely.springbootneo4j.config;

import dev.gergely.springbootneo4j.services.NeoUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final NeoUserDetailsService neoUserDetailsService;

    public SecurityConfig(NeoUserDetailsService neoUserDetailsService) {
        this.neoUserDetailsService = neoUserDetailsService;
    }

    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors((Customizer.withDefaults()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "api/v1/auth/me"
                        ).authenticated()
                        .anyRequest().permitAll()
                )
                .userDetailsService(neoUserDetailsService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
