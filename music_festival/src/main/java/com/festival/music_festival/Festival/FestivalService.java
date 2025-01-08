package com.festival.music_festival.Festival;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class FestivalService {


    private final FestivalRepository FestivalRepository;

    @Autowired
    public FestivalService(FestivalRepository FestivalRepository) {
        this.FestivalRepository = FestivalRepository;
    }

    // get
    public List<Festival> getFestival() {
        return FestivalRepository.findAll();
    }


    // post
    public void addNewFestival(Festival festival) {


        Optional<Festival> festivalOptional = FestivalRepository
                .findFestivalByName(festival.getFestivalName());

        if (festival.getFestivalStatus() == null || festival.getFestivalStatus().trim().isEmpty()) {
            festival.setFestivalStatus("CREATED");
        }

        FestivalRepository.save(festival);
    }


    // Del
    public void deleteFestival(Long festivalId) {
        boolean exists = FestivalRepository.existsById(festivalId);
        if (!exists) {
            throw new IllegalStateException("Festival not found with id: " + festivalId);
        }
        FestivalRepository.deleteById(festivalId);
    }

    //put
    @Transactional
    public void updateFestival(Long festivalId, String festivalName) {


        Festival festival = FestivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with id: " + festivalId));


        if (festivalName != null && !festivalName.trim().isEmpty() &&
                !Objects.equals(festival.getFestivalName(), festivalName)) {
            String currentFestivalId = festival.getFestivalName();
            FestivalRepository.findFestivalByName(festivalName)
                    .ifPresent(existingFestival -> {
                        if (Objects.equals(existingFestival.getFestivalName(), currentFestivalId)) {
                            throw new IllegalStateException("The name is already taken for this festival");
                        }
                    });

            festival.setFestivalName(festivalName);
        }


    }
}