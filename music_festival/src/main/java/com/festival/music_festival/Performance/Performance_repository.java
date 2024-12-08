package com.festival.music_festival.Performance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Performance_repository
                extends JpaRepository<Performance, Long> {

        @Query("SELECT s FROM Performance s WHERE s.performance_name = ?1")
        Optional<Performance> find_Performance_by_name(String performance_name);

}
