package com.festival.music_festival.Performance;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PerformanceService {

    private final PerformanceRepository PerformanceRepository;

    @Autowired
    public PerformanceService(PerformanceRepository PerformanceRepository) {
        this.PerformanceRepository = PerformanceRepository;
    }

    // get
    public List<Performance> getPerformance() {
        return PerformanceRepository.findAll();
    }


    // post
    public void addNewPerformance(Performance performance) {

        Optional<Performance> performanceOptional = PerformanceRepository
                .findPerformanceByName(performance.getPerformanceName());
        if (performanceOptional.isPresent()) {
            Performance existingPerformance = performanceOptional.get();
            String currentFestivalId = performance.getFestivalName();
            if (Objects.equals(existingPerformance.getFestivalName(), currentFestivalId)) {
                throw new IllegalStateException("The performance name is already taken for this festival");
            } else {
                performance.setPerformanceName(performance.getPerformanceName());
            }
        }

        if (performance.getKalitexnes() == null) {
            performance.setKalitexnes(new ArrayList<>());
        }

        if (!performance.getKalitexnes().contains(performance.getPerformanceCreatorName())) {
            performance.getKalitexnes().add(performance.getPerformanceCreatorName());
        }

        PerformanceRepository.save(performance);
    }


    // Del
    public void deletePerformance(Long performanceId) {
        boolean exists = PerformanceRepository.existsById(performanceId);
        if (!exists) {
            throw new IllegalStateException("Performance not found with id: " + performanceId);
        }
        PerformanceRepository.deleteById(performanceId);
    }

    //put
    @Transactional
    public void updatePerformance(Long performanceId,
                                  String performanceName, List<String> performanceGroupNames, String performanceType,
                                  Integer performanceDuration, String performanceTechnicalRequirements,
                                  String performanceMerchandiseItems, List<String> performanceSongList,
                                  LocalDateTime performancePreferredTimeRehearsal, LocalDateTime performancePreferredTime,
                                  String performanceDescription, String performanceCreatorName) {


        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if (!Objects.equals(performance.getPerformanceCreatorName(), performanceCreatorName) &&
                !performance.getKalitexnes().contains(performanceCreatorName)) {
            throw new IllegalStateException("Only the performance creator or existing kalitexnes members can update the performance.");
        }

        if (performanceName != null && !performanceName.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceName(), performanceName)) {
            String currentFestivalId = performance.getFestivalName();
            PerformanceRepository.findPerformanceByName(performanceName)
                    .ifPresent(existingPerformance -> {
                        if (Objects.equals(existingPerformance.getFestivalName(), currentFestivalId)) {
                            throw new IllegalStateException("The name is already taken for this festival");
                        }
                    });
            performance.setPerformanceName(performanceName);
        }


        if (performanceGroupNames != null && !performanceGroupNames.isEmpty() &&
                !Objects.equals(performance.getPerformanceGroupNames(), performanceGroupNames)) {
            performance.setPerformanceGroupNames(performanceGroupNames);
        }

        if (performanceType != null && !performanceType.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceType(), performanceType)) {
            performance.setPerformanceType(performanceType);
        }


        if (performanceDuration != null && performanceDuration > 0 &&
                !Objects.equals(performance.getPerformanceDuration(), performanceDuration)) {
            performance.setPerformanceDuration(performanceDuration);
        }


        if (performanceTechnicalRequirements != null && !performanceTechnicalRequirements.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceTechnicalRequirements(), performanceTechnicalRequirements)) {
            performance.setPerformanceTechnicalRequirements(performanceTechnicalRequirements);
        }


        if (performanceMerchandiseItems != null && !performanceMerchandiseItems.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceMerchandiseItems(), performanceMerchandiseItems)) {
            performance.setPerformanceMerchandiseItems(performanceMerchandiseItems);
        }


        if (performanceSongList != null && !performanceSongList.isEmpty() &&
                !Objects.equals(performance.getPerformanceSongList(), performanceSongList)) {
            performance.setPerformanceSongList(performanceSongList);
        }


        if (performancePreferredTimeRehearsal != null &&
                !Objects.equals(performance.getPerformancePreferredTimeRehearsal(), performancePreferredTimeRehearsal)) {
            performance.setPerformancePreferredTimeRehearsal(performancePreferredTimeRehearsal);
        }


        if (performancePreferredTime != null &&
                !Objects.equals(performance.getPerformancePreferredTime(), performancePreferredTime)) {
            performance.setPerformancePreferredTime(performancePreferredTime);
        }


        if (performanceDescription != null && !performanceDescription.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceDescription(), performanceDescription)) {
            performance.setPerformanceDescription(performanceDescription);
        }
    }

    //put-addkalitexnes
    @Transactional
    public void addKalitexnes(Long performanceId, String kalitexnis, String performanceCreatorName) {

        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if (!Objects.equals(performance.getPerformanceCreatorName(), performanceCreatorName) &&
                !performance.getKalitexnes().contains(performanceCreatorName)) {
            throw new IllegalStateException("Only the performance creator or existing kalitexnes members can add new kalitexnes.");
        }

        if (kalitexnis != null && !kalitexnis.isEmpty()) {

            List<String> groupNames = performance.getPerformanceGroupNames();

            if (!groupNames.contains(kalitexnis)) {

                throw new IllegalStateException("The kalitexnis '" + kalitexnis + "' does not exist in the group names. Please add it first.");
            }

            if (performance.getKalitexnes().contains(kalitexnis)) {

                throw new IllegalStateException("The kalitexnis '" + kalitexnis + "' is already in the kalitexnes list.");
            }
            performance.addKalitexnes(kalitexnis);
        }
    }

}