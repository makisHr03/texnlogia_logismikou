package com.festival.musicFestival.Festival;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class FestivalConfig {

    // Pre set festivals, for testing
    @Bean
    public Festival festival1() {
        return new Festival(
                "Festival1",
                "SUBMISSION",
                "A vibrant music festival with multiple stages.",
                LocalDate.of(2025, 6, 15),
                5,
                10,
                50.0f,
                20.0f,
                150.0f,
                100.0f,
                List.of("Staff1", "Staff2"),
                List.of("Organizer1")
        );
    }

    @Bean
    public Festival festival2() {
        return new Festival(
                "Festival2",
                "SUBMISSION",
                "An annual festival celebrating diverse music genres.",
                LocalDate.of(2025, 9, 10),
                8,
                15,
                70.0f,
                30.0f,
                20.0f,
                150.0f,
                List.of("Staff3", "Staff4"),
                List.of("Organizer2, Organizer2")
        );
    }

    @Bean
    public CommandLineRunner festivalCommandLineRunner(FestivalRepository repository) {
        return args -> {
            Festival f1 = festival1();
            Festival f2 = festival2();

            // Save festivals to the repository
            repository.saveAll(List.of(f1, f2));
        };
    }
}
