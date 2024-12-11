package com.festival.music_festival.Performance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
                                        "creator1",
                                        "group1",
                                        LocalDate.of(2024, 1, 1),
                                        "Type1",
                                        10,
                                        "tech_req1",
                                        "merch_item1",
                                        new ArrayList<>(List.of("song1", "song2")),
                                        new ArrayList<>(List.of(LocalDateTime.of(2023, 12, 30, 10, 0))),
                                        new ArrayList<>(List.of(LocalDateTime.of(2024, 1, 1, 15, 0))),
                                        "Description of performance 1");

                        Performance festival2 = new Performance(
                                        "festival2",
                                        "per2",
                                        "creator2",
                                        "group2",
                                        LocalDate.of(2024, 1, 1),
                                        "Type2",
                                        20,
                                        "tech_req2",
                                        "merch_item2",
                                        new ArrayList<>(List.of("song3", "song4")),
                                        new ArrayList<>(List.of(LocalDateTime.of(2023, 12, 30, 14, 0))),
                                        new ArrayList<>(List.of(LocalDateTime.of(2024, 1, 1, 18, 0))),
                                        "Description of performance 2");

                        repository.saveAll(List.of(festival1, festival2));

                };
        };
}
