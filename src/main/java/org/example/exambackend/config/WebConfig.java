package org.example.exambackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Applies to endpoints starting with /api
                        .allowedOrigins("http://localhost:63342") // Replace with your frontend's URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Define allowable HTTP methods
                        .allowCredentials(true); // Allow sending cookies if needed
            }
        };
    }
}