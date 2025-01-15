package com.festival.musicFestival.Performance;

import com.festival.musicFestival.Festival.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class PerformanceConfig {

    @Autowired
    private Festival festival1;  // Inject festival1 bean

    @Autowired
    private Festival festival2;  // Inject festival2 bean

    // Pre set performance, for testing
    @Bean(name = "performanceCommandLineRunner")
    public CommandLineRunner commandLineRunner(PerformanceRepository repository) {
        return args -> {
            Performance performance1 = new Performance(
                    festival1, "Performance 1", "Creator 1", List.of("Member 1", "Member 2"),
                    LocalDate.of(2024, 1, 1), "Type 1", 100, "Tech Req 1", "Merch 1",
                    List.of("Song 1", "Song 2"), LocalDateTime.of(2023, 12, 31, 10, 0),
                    LocalDateTime.of(2024, 1, 1, 15, 0), "Description 1"
            );

            Performance performance2 = new Performance(
                    festival2, "Performance 2", "Creator 2", List.of("Member 3", "Member 4"),
                    LocalDate.of(2024, 1, 2), "Type 2", 200, "Tech Req 2", "Merch 2",
                    List.of("Song 3", "Song 4"), LocalDateTime.of(2023, 12, 31, 12, 0),
                    LocalDateTime.of(2024, 1, 2, 18, 0), "Description 2"
            );

            repository.saveAll(List.of(performance1, performance2));
        };
    }
}
