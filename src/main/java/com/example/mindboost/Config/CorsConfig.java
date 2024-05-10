package com.example.mindboost.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Autoriser les requêtes depuis ce domaine
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autoriser ces méthodes HTTP
                .allowedHeaders("*"); // Autoriser tous les en-têtes
    }
}
