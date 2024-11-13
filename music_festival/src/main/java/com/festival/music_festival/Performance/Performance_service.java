package com.festival.music_festival.Performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Performance_service {

    private final PerformanceRepository performanceRepository;

    @Autowired
    public Performance_service(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public List<Performance> get_performance() {
        return performanceRepository.findAll();
    }

    public void saveSamplePerformance() {
        Performance samplePerformance = new Performance(
                null, // ID will be auto-generated
                "Music Fest",
                "BTS",
                "K-pop",
                "Korea",
                LocalDate.of(2024, 1, 1),
                "AAA performance description");
        performanceRepository.save(samplePerformance);
    }
}
