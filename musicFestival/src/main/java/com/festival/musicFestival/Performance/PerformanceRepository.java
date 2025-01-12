package com.festival.musicFestival.Performance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceRepository
        extends JpaRepository<Performance, Long> {

    @Query("SELECT p FROM Performance p WHERE p.performanceName = ?1")
    Optional<Performance> findPerformanceByName(String performanceName);

}
