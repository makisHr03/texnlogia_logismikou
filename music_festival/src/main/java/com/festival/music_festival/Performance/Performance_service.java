package com.festival.music_festival.Performance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class Performance_service {

    private final Performance_repository performance_repository;

    @Autowired
    public Performance_service(Performance_repository performance_repository) {
        this.performance_repository = performance_repository;
    }

    // get
    public List<Performance> get_performance() {
        return performance_repository.findAll();
    }

    // post
    public void add_new_student(Performance performance) {
        Optional<Performance> Performance_optional = performance_repository
                .find_Performance_by_name(performance.getPerformance_name());
        if (Performance_optional.isPresent()) {
            throw new IllegalStateException("Name taken");
        }
        performance_repository.save(performance);
    }

    // delete
    public void delete_performance(Long performance_id) {
        boolean exists = performance_repository.existsById(performance_id);
        if (!exists) {
            throw new IllegalStateException("Performance not found with id: " + performance_id);
        }
        performance_repository.deleteById(performance_id);
    }

    @Transactional
    // put

    public void update_performance(Long performance_id,
            String performance_name, String performance_group_names, String performance_type,
            Integer performance_duration,
            String performance_technical_requirements, String performance_merchandise_items,
            ArrayList<String> performance_song_list, ArrayList<LocalDateTime> performance_preferred_time_rehearsal,
            ArrayList<LocalDateTime> performance_preferred_time, String performance_description) {

        Performance performance = performance_repository.findById(performance_id)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performance_id));

        if (performance_name != null &&
                performance_name.length() > 0 &&
                !Objects.equals(performance.getPerformance_name(), performance_name)) {
            String currentFestivalId = performance.getFestival_name();
            Optional<Performance> performance_optional = performance_repository
                    .find_Performance_by_name(performance_name);
            if (performance_optional.isPresent()) {
                Performance existingPerformance = performance_optional.get();
                if (!Objects.equals(existingPerformance.getFestival_name(), currentFestivalId)) {
                    performance.setPerformance_name(performance_name);
                } else {
                    throw new IllegalStateException("The name is already taken for this festival");
                }
            } else {
                performance.setPerformance_name(performance_name);
            }
        }

        if (performance_group_names != null && performance_group_names.length() > 0
                && !Objects.equals(performance.getPerformance_group_names(), performance_group_names)) {
            performance.setPerformance_group_names(performance_group_names);
        }

        if (performance_type != null && performance_type.length() > 0
                && !Objects.equals(performance.getPerformance_type(), performance_type)) {
            performance.setPerformance_type(performance_type);
        }

        if (performance_duration != null && performance_duration > 0
                && performance.getPerformance_duration() != performance_duration) {
            performance.setPerformance_duration(performance_duration);
        }

        if (performance_technical_requirements != null && performance_technical_requirements.length() > 0
                && !Objects.equals(performance.getPerformance_technical_requirements(),
                        performance_technical_requirements)) {
            performance.setPerformance_technical_requirements(performance_technical_requirements);
        }

        if (performance_merchandise_items != null && performance_merchandise_items.length() > 0
                && !Objects.equals(performance.getPerformance_merchandise_items(), performance_merchandise_items)) {
            performance.setPerformance_merchandise_items(performance_merchandise_items);
        }

        if (performance_song_list != null && !performance_song_list.isEmpty()
                && !Objects.equals(performance.getPerformance_song_list(), performance_song_list)) {
            performance.setPerformance_song_list(performance_song_list);
        }

        if (performance_preferred_time_rehearsal != null && !performance_preferred_time_rehearsal.isEmpty()
                && !Objects.equals(performance.getPerformance_preferred_time_rehearsal(),
                        performance_preferred_time_rehearsal)) {
            performance.setPerformance_preferred_time_rehearsal(performance_preferred_time_rehearsal);
        }

        if (performance_preferred_time != null && !performance_preferred_time.isEmpty()
                && !Objects.equals(performance.getPerformance_preferred_time(), performance_preferred_time)) {
            performance.setPerformance_preferred_time(performance_preferred_time);
        }

        if (performance_description != null && performance_description.length() > 0
                && !Objects.equals(performance.getPerformance_description(), performance_description)) {
            performance.setPerformance_description(performance_description);
        }
    }

}