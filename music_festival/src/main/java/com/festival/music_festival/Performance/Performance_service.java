package com.festival.music_festival.Performance;

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

    public List<Performance> get_performance() {
        return performance_repository.findAll();
    }

    public void add_new_student(Performance performance) {
        Optional<Performance> Performance_optional = performance_repository
                .find_Performance_by_name(performance.getPerformance_name());
        if (Performance_optional.isPresent()) {
            throw new IllegalStateException("Name taken");
        }
        performance_repository.save(performance);
    }

    public void delete_performace(Long performace_id) {
        boolean exists = performance_repository.existsById(performace_id);
        if (!exists) {
            throw new IllegalStateException("Performance not found with id: " + performace_id);
        }
        performance_repository.deleteById(performace_id);
    }

    @Transactional
    public void update_performace(Long performace_id,
            String performance_name) {
        Performance performance = performance_repository.findById(performace_id)
                .orElseThrow(() -> new IllegalStateException(
                        "Performance not found with id: " + performace_id));

        if (performance_name != null &&
                performance_name.length() > 0 && !Objects.equals(performance.getPerformance_name(), performance_name)) {
            Optional<Performance> performace_optional = performance_repository
                    .find_Performance_by_name(performance_name);
            if (performace_optional.isPresent()) {
                throw new IllegalStateException("The name is alraidy taken");
            }
            performance.setPerformance_name(performance_name);
        }
    }
}