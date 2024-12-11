package com.festival.music_festival.Performance;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // get
    @GetMapping
    public List<Performance> get_performance() {
        return performance_service.get_performance();
    }

    // post
    @PostMapping
    public void registrer_new_performance(@RequestBody Performance performance) {
        performance_service.add_new_student(performance);
    }

    // del
    @DeleteMapping(path = "{performance_id}")
    public void delete_performance(@PathVariable("performance_id") Long performance_id) {
        performance_service.delete_performance(performance_id);
    }

    // put
    @PutMapping(path = "{performance_id}")
    public void update_performance(
            @PathVariable("performance_id") Long performance_id,
            @RequestParam(required = false) String performance_name,
            @RequestParam(required = false) String performance_group_names,
            @RequestParam(required = false) String performance_type,
            @RequestParam(required = false) Integer performance_duration,
            @RequestParam(required = false) String performance_technical_requirements,
            @RequestParam(required = false) String performance_merchandise_items,
            @RequestParam(required = false) ArrayList<String> performance_song_list,
            @RequestParam(required = false) ArrayList<LocalDateTime> performance_preferred_time_rehearsal,
            @RequestParam(required = false) ArrayList<LocalDateTime> performance_preferred_time,
            @RequestParam(required = false) String performance_description) {

        performance_service.update_performance(performance_id,
                performance_name, performance_group_names, performance_type,
                performance_duration, performance_technical_requirements,
                performance_merchandise_items, performance_song_list, performance_preferred_time_rehearsal,
                performance_preferred_time, performance_description);
    }

}
