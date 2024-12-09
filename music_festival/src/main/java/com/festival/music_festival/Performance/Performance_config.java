package com.festival.music_festival.Performance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Performance_config {

        @Bean
        CommandLineRunner commandLineRunner(
                        Performance_repository repository) {
                return args -> {
                        Performance festival1 = new Performance(
                                        "festival1",
                                        "per1",
                                        "craetor1",
                                        "group1",
                                        LocalDate.of(2024, 1, 1),
                                        "AAA");

                        Performance festival2 = new Performance(
                                        "festival2",
                                        "per2",
                                        "craetor2",
                                        "group2",
                                        LocalDate.of(2024, 1, 1),
                                        "bbb");

                        repository.saveAll(
                                        List.of(festival1, festival2));

                };
        };
}
