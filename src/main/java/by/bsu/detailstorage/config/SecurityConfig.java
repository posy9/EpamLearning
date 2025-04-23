package by.bsu.detailstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/logout").authenticated()
                        .requestMatchers(HttpMethod.GET, "/login").anonymous()
                        .requestMatchers( "/logout").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/js/details.js").authenticated()
                        .requestMatchers("/js/adminPage.js").hasRole("ADMIN")
                        .requestMatchers("/details/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/", "/brands/", "/devices", "/countries", "/types", "/categories").authenticated()
                        .requestMatchers("/**").hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                )
                .build();
    }
}

