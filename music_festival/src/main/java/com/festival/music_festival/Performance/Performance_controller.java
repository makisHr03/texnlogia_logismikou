package com.festival.music_festival.Performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping
    public void registrer_new_performace(@RequestBody Performance performance) {
        performance_service.add_new_student(performance);
    }

    @DeleteMapping(path = "{performace_id}")
    public void delete_performace(@PathVariable("performace_id") Long performace_id) {
        performance_service.delete_performace(performace_id);
    }

    @PutMapping(path = "{performance_id}")
    public void update_performace(
            @PathVariable("performance_id") Long performance_id,
            @RequestParam(required = false) String performace_name) {
        performance_service.update_performace(performance_id, performace_name);
    }
}