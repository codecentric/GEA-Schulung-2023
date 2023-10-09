package com.gea.geaschulung2023.v5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpBasicSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.DELETE, "/v5/farmer/*").hasRole("FARM_MANAGER")
                        .requestMatchers(HttpMethod.GET, "/v5/farms").hasAuthority("READ_PRIVILEGES")
                        .requestMatchers("/v1/**", "/v2/**", "/v3/**", "/v4/**", "/v5/**").permitAll()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();
        var encodedPassword = encoder.encode("password");
        System.out.println("encoded password in security config: " + encodedPassword);

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("farm.manager")
                .password(encodedPassword)
//                .password("password")
                .roles("FARM_MANAGER")
                .authorities("READ_PRIVILEGES")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}

