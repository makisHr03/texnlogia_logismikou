package com.festival.music_festival.Performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/performance")
public class Performance_controller {

    private final Performance_service performance_service;

    @Autowired
    public Performance_controller(Performance_service performance_service) {
        this.performance_service = performance_service;
    }

    @GetMapping
    public List<Performance> get_performance() {
        return performance_service.get_performance();
    }
}
