package com.festival.music_festival.Festival;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FestivalConfig {

    @Bean(name = "festival1")
    public Festival festival1() {
        return new Festival("festival1", "CREATED");
    }

    @Bean(name = "festival2")
    public Festival festival2() {
        return new Festival("festival2", "CREATED");
    }

    @Bean(name = "festivalCommandLineRunner")
    public CommandLineRunner festivalCommandLineRunner(FestivalRepository repository) {
        return args -> {
            Festival festival1 = festival1();
            Festival festival2 = festival2();

            repository.saveAll(List.of(festival1, festival2));
        };
    }
}
