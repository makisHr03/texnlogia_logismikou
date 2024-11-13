package com.festival.music_festival;

import com.festival.music_festival.Performance.Performance_service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicFestivalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicFestivalApplication.class, args);
    }

    @Bean
    CommandLineRunner run(Performance_service performance_service) {
        return args -> {
            performance_service.saveSamplePerformance();
        };
    }
}
