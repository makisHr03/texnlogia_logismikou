package com.festival.music_festival.Performance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository
        extends JpaRepository<Performance, Long> {

    @Query("SELECT p FROM Performance p WHERE p.performanceName = ?1")
    Optional<Performance> findPerformanceByName(String performanceName);

}
