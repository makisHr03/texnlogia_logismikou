package com.festival.musicFestival.Performance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceRepository
        extends JpaRepository<Performance, Long> {

    @Query("SELECT p FROM Performance p WHERE p.performanceName = ?1")
    Optional<Performance> findPerformanceByName(String performanceName);


    @Query("SELECT c FROM Performance c WHERE c.performanceName = ?1 OR c.performanceType = ?2 ORDER BY c.performanceName DESC, c.performanceType DESC")
    List<Performance> searchPerformance(String performanceName, String performanceType);

    @Query("SELECT c FROM Performance c WHERE c.performanceType = ?1 ORDER BY c.performanceType DESC")
    List<Performance> searchPerformanceByType(String performanceType);

    @Query("SELECT c FROM Performance c WHERE c.performanceName = ?1 ORDER BY c.performanceName DESC")
    List<Performance> searchPerformanceByName(String performanceName);




}




