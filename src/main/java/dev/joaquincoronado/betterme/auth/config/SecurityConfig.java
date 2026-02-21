package dev.joaquincoronado.betterme.auth.config;

import dev.joaquincoronado.betterme.auth.filter.JWTAccessDeniedHandler;
import dev.joaquincoronado.betterme.auth.filter.JWTAuthenticationEntryPoint;
import dev.joaquincoronado.betterme.auth.filter.JWTTokenFilter;
import dev.joaquincoronado.betterme.auth.service.AuthUserDetailsService;
import dev.joaquincoronado.betterme.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthUserDetailsService authUserDetailsService;

    //JWT
    private final JWTTokenFilter jwtTokenFilter;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] ROLE_USER = {"USER", "ADMIN"};
    private final String[] ROLE_ADMIN = {"ADMIN"};

    private final String[] PUBLIC_ENDPOINT = {
        "/user/v1/**",
        "/auth/v1/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        return http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(
                management ->
                    management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .exceptionHandling(  handling ->
                handling
                    .accessDeniedHandler(jwtAccessDeniedHandler)
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )
            .authorizeHttpRequests( auth ->
                auth.requestMatchers(HttpMethod.PUT, "/user/v1/user/**").hasAnyRole(ROLE_USER)
                    .requestMatchers(HttpMethod.DELETE, "/user/v1/user/**").hasAnyRole(ROLE_ADMIN)
                    .requestMatchers(PUBLIC_ENDPOINT).permitAll()
            )
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http){
        http
            .getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(authUserDetailsService)
            .passwordEncoder(passwordEncoder());

        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
