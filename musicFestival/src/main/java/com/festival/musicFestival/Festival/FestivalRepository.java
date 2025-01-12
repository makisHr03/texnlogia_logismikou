package com.festival.musicFestival.Festival;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// The repository find a festival by its name
@Repository
public interface FestivalRepository
        extends JpaRepository<Festival, Long> {

    @Query("SELECT f FROM Festival f WHERE f.festivalName = ?1")
    Optional<Festival> findFestivalByName(String festivalName);

}
