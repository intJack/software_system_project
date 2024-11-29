package edu.just.resource_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/home", "/css/**","/search","/login", "/index","/register").permitAll()  // Allow login and register without authentication
                                .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/home")  // Specify the login page
                        .permitAll()  // Allow everyone to access the login page
                        .defaultSuccessUrl("/index", true)  // Redirect to home page after successful login
                        .failureUrl("/login?error=true")  // Redirect on login failure
                )
                .logout(logout -> logout
                        .permitAll()  // Allow logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")  // Redirect to login page on successful logout
                );

        return http.build();
    }
}


