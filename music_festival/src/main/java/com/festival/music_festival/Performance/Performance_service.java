package com.festival.music_festival.Performance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Performance_service {
    public List<Performance> get_performance() {
        return List.of(
                new Performance(
                        null, // ID will be auto-generated
                        "festival1",
                        "per1",
                        "craetor1",
                        "group123",
                        LocalDate.of(2024, 1, 1),
                        "AAA"));
    }
}