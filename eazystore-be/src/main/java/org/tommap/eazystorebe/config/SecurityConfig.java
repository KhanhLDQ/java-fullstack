package org.tommap.eazystorebe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
/*
    - spring boot automatically enable web security when this lib is present on the classpath
    - completely optional -> just to improve readability
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${cors.allowed-origins}")
    private List<String> allowedOrigins;

    private final List<String> publicPaths;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .cors(corsConfigure ->
                corsConfigure.configurationSource(corsConfigurationSource())
            )
            .authorizeHttpRequests(request -> {
                //public endpoints
                publicPaths.forEach(path -> request.requestMatchers(path).permitAll());
                //other endpoints
                request.anyRequest().authenticated(); //always put at the end of configuration
            })
            .formLogin(withDefaults())
            .httpBasic(withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        EazyStoreAuthenticationProvider eazyStoreAuthenticationProvider
//        UserDetailsService userDetailsService,
//        PasswordEncoder passwordEncoder
    ) {
//      var daoAuthenticationProvider = new DaoAuthenticationProvider();
//      daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//      return new ProviderManager(daoAuthenticationProvider);
        return new ProviderManager(eazyStoreAuthenticationProvider);
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() { //part of Spring Security version 6.3
        return new HaveIBeenPwnedRestApiPasswordChecker(); //rest API call to 3rd party which is going to maintain all the weak|compromised passwords
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        var khanh = User.builder()
//                .username("khanh")
//                .password("$2a$12$Jlxg.w.C2UUmC5y4t5MgRO2Mx1xUuSgbs8ti2GcmrfmZPKU/ETGyC") //123456
//                .roles("USER")
//                .build();
//
//        var tom = User.builder()
//                .username("tom")
//                .password("$2a$12$iCEUG0Iqp2.0fK2yr76MduSqV99mtaf5kGaTVITKFxuTSiMhhCSQK") //654321
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(khanh, tom);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(allowedOrigins); //should configure specific origins
        config.setAllowedMethods(List.of("*")); //allow all HTTP methods
        config.setAllowedHeaders(List.of("*")); //allow all HTTP headers
        config.setAllowCredentials(true); //allow to include credentials (JWT, cookie, ...)
        config.setMaxAge(3600L); //cache preflight response for an hour per browser

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); //apply CORS config to all endpoints

        return source;
    }
}
