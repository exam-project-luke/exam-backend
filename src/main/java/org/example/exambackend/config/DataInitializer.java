package org.example.exambackend.config;

import org.example.exambackend.service.PizzaService;
import org.example.exambackend.service.StationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(StationService stationService, PizzaService pizzaService) {
        return args -> {
            stationService.initializeStations();
            pizzaService.initializePizzas();
        };
    }
}
