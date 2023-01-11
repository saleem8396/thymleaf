package com.tcs.assignment.springbootassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebSecurity {
    private static final String[] WHITE_LIST_URLS = {
            "/createuser"
    };

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(11);
//    }

//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeHttpRequests();
//
//
//    }
}