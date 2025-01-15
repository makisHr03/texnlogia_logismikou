package com.festival.musicFestival.Festival;

import com.festival.musicFestival.Performance.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// The repository find a festival by its name
@Repository
public interface FestivalRepository
        extends JpaRepository<Festival, Long> {

    @Query("SELECT f FROM Festival f WHERE f.festivalName = ?1")
    Optional<Festival> findFestivalByName(String festivalName);


    @Query("SELECT f FROM Festival f WHERE f.festivalName = ?1 OR f.description = ?2 ORDER BY f.festivalName DESC, f.description DESC")
    List<Festival> searchFestival(String festivalName, String description);

    @Query("SELECT f FROM Festival f WHERE f.description = ?1 ORDER BY f.description DESC")
    List<Festival> searchFestivalByDecription(String description);

    @Query("SELECT f FROM Festival f WHERE f.festivalName = ?1 ORDER BY f.festivalName DESC")
    List<Festival> searchFestivalByName(String festivalName);


}
