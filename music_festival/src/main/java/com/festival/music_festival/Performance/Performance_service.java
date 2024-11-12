package com.festival.music_festival.Performance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Performance_service {
    public List<Performance> get_performance() {
        return List.of(
                new Performance(
                        1L,
                        "BTS",
                        "K-pop",
                        "Korea",
                        LocalDate.of(2024, 1, 1),
                        "aaa"));
    }
}
