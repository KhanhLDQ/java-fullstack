package org.tommap.eazystorebe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {
    @Value("${cors.allowed-origins}")
    private List<String> allowedOrigins;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(allowedOrigins); //should configure specific origins
        config.setAllowedMethods(List.of("*")); //allow all HTTP methods
        config.setAllowedHeaders(List.of("*")); //allow all HTTP headers
        config.setAllowCredentials(true); //allow to include credentials (JWT, cookie, ...)
        config.setMaxAge(3600L); //cache preflight response for an hour per browser\

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); //apply CORS config to all endpoints

        return new CorsFilter(source);
    }
}
