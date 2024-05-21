
package com.example.mindboost.Security;

import com.example.mindboost.Security.Service.UserDetailService;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor

public class SecurityConfig {

    private final UserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;
    //@Value("${jwt.secret}")
    //private String Key;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/auth/signup", "/auth/login").permitAll()
                        .anyRequest().authenticated())
                /*.authorizeHttpRequests(ar -> ar
                        .requestMatchers("/**").permitAll())*/
                //.authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                //.httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
                .userDetailsService(userDetailService)
                .build();
    }
    @Bean
    JwtEncoder jwtEncoder() {
        String secretKey = "7B7C57F468884CC64739275DFC3A54C2A3782489B9DB32E7A75F6C4066242F36";
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }
    @Bean
    JwtDecoder jwtDecoder(){
        String secretKey = "7B7C57F468884CC64739275DFC3A54C2A3782489B9DB32E7A75F6C4066242F36";
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }
    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        return new ProviderManager(daoAuthenticationProvider);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setExposedHeaders(List.of("x-auth-token"));
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

}

