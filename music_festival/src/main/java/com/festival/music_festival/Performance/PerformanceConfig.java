package com.festival.music_festival.Performance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PerformanceConfig {

    @Bean
    CommandLineRunner commandLineRunner(PerformanceRepository repository) {
        return args -> {
            Performance performance1 = new Performance(
                    "festival1",
                    "per1",
                    "creator1",
                    List.of("group_member1", "group_member2"),
                    LocalDate.of(2024, 1, 1),
                    "Type1",
                    10,
                    "tech_req1",
                    "merch_item1",
                    List.of("song1", "song2"),
                    LocalDateTime.of(2023, 12, 30, 10, 0),
                    LocalDateTime.of(2024, 1, 1, 15, 0),
                    "Description of performance 1"
            );
            Performance performance2 = new Performance(
                    "festival2",
                    "per2",
                    "creator2",
                    List.of("group_member3", "group_member4"),
                    LocalDate.of(2024, 1, 1),
                    "Type2",
                    20,
                    "tech_req2",
                    "merch_item2",
                    List.of("song3", "song4"),
                    LocalDateTime.of(2023, 12, 30, 14, 0),
                    LocalDateTime.of(2024, 1, 1, 18, 0),
                    "Description of performance 2"
            );

            repository.saveAll(List.of(performance1, performance2));
        };
    }
}
