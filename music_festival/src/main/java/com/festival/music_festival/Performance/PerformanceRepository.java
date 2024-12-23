package com.festival.music_festival.Performance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository
        extends JpaRepository<Performance, Long> {

    @Query("SELECT s FROM Performance s WHERE s.performanceName = ?1")
    Optional<Performance> findPerformanceByName(String performanceName);

}
