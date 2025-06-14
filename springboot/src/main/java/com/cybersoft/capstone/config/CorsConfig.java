// package com.cybersoft.capstone.config;
//
// import java.util.Arrays;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
//
// @Configuration
// public class CorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://phucserver:3000")); // Allowed origins
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Allowed methods
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allowed headers
//        configuration.setAllowCredentials(true); // Allow credentials (cookies, etc.)
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // Apply to all paths
//        return new CorsFilter(source);
//    }
// }
