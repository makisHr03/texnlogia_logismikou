package com.festival.musicFestival.Performance;

import com.festival.musicFestival.Festival.Festival;
import com.festival.musicFestival.Festival.FestivalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PerformanceService {

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private PerformanceRepository PerformanceRepository;

    // Get all
    public List<Performance> getPerformance() {
        return PerformanceRepository.findAll();
    }


    // Post new performance
    public void addNewPerformance(Performance performance) {

        // Check if exist the festival with the id
        Optional<Festival> festivalOptional = festivalRepository.findFestivalByName(performance.getFestival().getFestivalName());
        if (festivalOptional.isEmpty()) {
            throw new IllegalStateException("Festival with name " + performance.getFestival().getFestivalName() + " does not exist");
        }

        // Associate the existing festival with the performance
        performance.setFestival(festivalOptional.get());

        // Check if a performance with the same name already exists for the same festival
        Optional<Performance> performanceOptional = PerformanceRepository
                .findPerformanceByName(performance.getPerformanceName());

        // Check if the festival is on correct state
        if (!"SUBMISSION".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on submission state");
        }

        if (performanceOptional.isPresent()) {
            Performance existingPerformance = performanceOptional.get();
            String currentFestivalId = performance.getFestival().getFestivalName();
            if (Objects.equals(existingPerformance.getFestival().getFestivalName(), currentFestivalId)) {
                throw new IllegalStateException("The performance name is already taken for this festival");
            }
        }

        // Set default status if not already set
        if (performance.getPerformanceStatus() == null || performance.getPerformanceStatus().trim().isEmpty()) {
            performance.setPerformanceStatus("CREATED");
        }

        // Initialize kalitexnes list if null
        if (performance.getKalitexnes() == null) {
            performance.setKalitexnes(new ArrayList<>());
        }

        // Add the creator to the kalitexnes list if not already present
        if (!performance.getKalitexnes().contains(performance.getPerformanceCreatorName())) {
            performance.getKalitexnes().add(performance.getPerformanceCreatorName());
        }

        // Check if the festival is on correct state
        if (performance.getFestival().getFestivalStatus().equals("CREATED")) {
            throw new IllegalStateException("The festival is in CREATED state, you can't create new performance on this festival now.");
        }

        // Save the new performance to the repository
        PerformanceRepository.save(performance);
    }


//    // Del, for testing
//    public void deletePerformance(Long performanceId) {
//        boolean exists = PerformanceRepository.existsById(performanceId);
//        if (!exists) {
//            throw new IllegalStateException("Performance not found with id: " + performanceId);
//        }
//        PerformanceRepository.deleteById(performanceId);
//    }


    // Put update performance
    @Transactional
    public void updatePerformance(Long performanceId,
                                  String performanceName, List<String> performanceGroupNames, String performanceType,
                                  Integer performanceDuration, String performanceTechnicalRequirements,
                                  String performanceMerchandiseItems, List<String> performanceSongList,
                                  LocalDateTime performancePreferredTimeRehearsal, LocalDateTime performancePreferredTime,
                                  String performanceDescription, String performanceCreatorName) {


        // Check if exist the festival with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the performance is on correct state
        if ("FINAL_SUBMISSION".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is on FINAL_SUBMISSION state, you can not change anything.");
        }

        // Check if the given name is the creator ot kalitexnis
        if (!Objects.equals(performance.getPerformanceCreatorName(), performanceCreatorName) &&
                !performance.getKalitexnes().contains(performanceCreatorName)) {
            throw new IllegalStateException("Only the performance creator or existing kalitexnes members can update the performance.");
        }

        // Check if the new name is already taken on this festival
        if (performanceName != null && !performanceName.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceName(), performanceName)) {
            String currentFestivalId = performance.getFestival().getFestivalName();
            PerformanceRepository.findPerformanceByName(performanceName)
                    .ifPresent(existingPerformance -> {
                        if (Objects.equals(existingPerformance.getFestival().getFestivalName(), currentFestivalId)) {
                            throw new IllegalStateException("The name is already taken for this festival");
                        }
                    });
            performance.setPerformanceName(performanceName);
        }


        // Update group
        if (performanceGroupNames != null && !performanceGroupNames.isEmpty() &&
                !Objects.equals(performance.getPerformanceGroupNames(), performanceGroupNames)) {
            performance.setPerformanceGroupNames(performanceGroupNames);
        }

        // Update type
        if (performanceType != null && !performanceType.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceType(), performanceType)) {
            performance.setPerformanceType(performanceType);
        }

        // Update duration
        if (performanceDuration != null && performanceDuration > 0 &&
                !Objects.equals(performance.getPerformanceDuration(), performanceDuration)) {
            performance.setPerformanceDuration(performanceDuration);
        }

        // Update technical requirements
        if (performanceTechnicalRequirements != null && !performanceTechnicalRequirements.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceTechnicalRequirements(), performanceTechnicalRequirements)) {
            performance.setPerformanceTechnicalRequirements(performanceTechnicalRequirements);
        }

        // Update merchandise items
        if (performanceMerchandiseItems != null && !performanceMerchandiseItems.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceMerchandiseItems(), performanceMerchandiseItems)) {
            performance.setPerformanceMerchandiseItems(performanceMerchandiseItems);
        }

        // Update song list
        if (performanceSongList != null && !performanceSongList.isEmpty() &&
                !Objects.equals(performance.getPerformanceSongList(), performanceSongList)) {
            performance.setPerformanceSongList(performanceSongList);
        }

        // Update preferred time rehearsal
        if (performancePreferredTimeRehearsal != null &&
                !Objects.equals(performance.getPerformancePreferredTimeRehearsal(), performancePreferredTimeRehearsal)) {
            performance.setPerformancePreferredTimeRehearsal(performancePreferredTimeRehearsal);
        }

        // Update preferred time rehearsal
        if (performancePreferredTime != null &&
                !Objects.equals(performance.getPerformancePreferredTime(), performancePreferredTime)) {
            performance.setPerformancePreferredTime(performancePreferredTime);
        }

        // Update description
        if (performanceDescription != null && !performanceDescription.trim().isEmpty() &&
                !Objects.equals(performance.getPerformanceDescription(), performanceDescription)) {
            performance.setPerformanceDescription(performanceDescription);
        }
    }

    // Put add kalitexnes
    @Transactional
    public void addKalitexnes(Long performanceId, String kalitexnis, String performanceCreatorName) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the given name is the creator ot kalitexnis
        if (!Objects.equals(performance.getPerformanceCreatorName(), performanceCreatorName) &&
                !performance.getKalitexnes().contains(performanceCreatorName)) {
            throw new IllegalStateException("Only the performance creator or existing kalitexnes members can add new kalitexnes.");
        }


        if (kalitexnis != null && !kalitexnis.isEmpty()) {
            List<String> groupNames = performance.getPerformanceGroupNames();

            // Check if the new name is on group
            if (!groupNames.contains(kalitexnis)) {
                throw new IllegalStateException("The kalitexnis '" + kalitexnis + "' does not exist in the group names. Please add it first.");
            }

            // Check if ius already on the list
            if (performance.getKalitexnes().contains(kalitexnis)) {
                throw new IllegalStateException("The kalitexnis '" + kalitexnis + "' is already in the kalitexnes list.");
            }

            // Add the new name to kalitexnes list
            performance.addKalitexnes(kalitexnis);
        }
    }

    // Put submit
    @Transactional
    public void submit(Long performanceId) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the performance is on correct state
        if ("SUBMITTED".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is already in a SUBMITTED state.");
        }

        // Check if the performance is on correct state
        if ("CREATED".equals(performance.getPerformanceStatus())) {

            // Check if is all the field valid
            if (performance.getPerformanceName() == null || performance.getPerformanceName().trim().isEmpty() ||
                    performance.getPerformanceDescription() == null || performance.getPerformanceDescription().trim().isEmpty() ||
                    performance.getPerformanceType() == null || performance.getPerformanceType().trim().isEmpty() ||
                    performance.getPerformanceDuration() <= 0 ||
                    performance.getPerformanceGroupNames() == null || performance.getPerformanceGroupNames().isEmpty() ||
                    performance.getPerformanceTechnicalRequirements() == null || performance.getPerformanceTechnicalRequirements().trim().isEmpty() ||
                    performance.getPerformanceMerchandiseItems() == null || performance.getPerformanceMerchandiseItems().trim().isEmpty() ||
                    performance.getPerformanceSongList() == null || performance.getPerformanceSongList().isEmpty() ||
                    performance.getPerformancePreferredTimeRehearsal() == null ||
                    performance.getPerformancePreferredTime() == null) {
                throw new IllegalStateException("The performance details are incomplete. All fields must be filled.");
            }
            performance.submitPerformance();
        } else {
            throw new IllegalStateException("The performance has been pass the SUBMITTED state.");
        }
    }

    // Del withdrawal
    public void withdrawal(Long performanceId, String performanceCreatorName) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the given name is the creator ot kalitexnis
        if (!Objects.equals(performance.getPerformanceCreatorName(), performanceCreatorName) &&
                !performance.getKalitexnes().contains(performanceCreatorName)) {
            throw new IllegalStateException("Only the performance creator or existing kalitexnes members can withdraw the performance.");
        }

        // Check if the performance is on correct state
        if ("SUBMITTED".equalsIgnoreCase(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is already in a SUBMITTED state and cannot be withdrawn.");
        }

        // Check if the performance is on correct state
        if ("CREATED".equalsIgnoreCase(performance.getPerformanceStatus())) {
            // Delete the performance
            PerformanceRepository.deleteById(performanceId);
        } else {
            throw new IllegalStateException("The performance can not delete on this state.");
        }
    }

    // Put add staff
    @Transactional
    public void setStaff(Long performanceId, String staffName) {

        // Check if the performance is on correct state
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the festival is on correct state
        if (!"ASSIGNMENT".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on assignment state.");
        }
        performance.setStaff(staffName);
    }


    // Put review state
    @Transactional
    public void reviewState(Long performanceId) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the performance is on correct state
        if ("REVIEW".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is already in a Review state.");
        }

        // Check if the performance is on correct state
        if (!"REVIEW".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on review state.");
        }
        performance.reviewPerformance();
    }


    // Put review
    @Transactional
    public void review(Long performanceId, String staffName, float score, String scoreDetails) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the given name is on staff list
        if (!Objects.equals(performance.getStaff(), staffName)) {
            throw new IllegalStateException("Only the staff can review the performance.");
        }

        // Check if the festival is on correct state
        if (!"REVIEW".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is not in a Review state.");
        }

        performance.createReview(score, scoreDetails);
    }

    // Put scheduling state
    @Transactional
    public void scheduling(Long performanceId) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the performance is on correct state
        if ("SCHEDULING ".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival is already in a SCHEDULING  state.");
        }
        performance.schedulingState();
    }

    // Put approval state
    @Transactional
    public void approval(Long performanceId) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the performance is on correct state
        if ("APPROVAL".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival is already in a APPROVAL  state.");
        }

        // Check if the performance is on correct state
        if (!"SCHEDULING".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival must be in a SCHEDULING state first and after on approval state.");
        }

        performance.approvalState();
    }

    // Put final submission
    @Transactional
    public void finalSubmission(Long performanceId) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the festival is on correct state
        if (!"FINAL_SUBMISSION".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on state final submission.");
        }

        // Check if the performance is on correct state
        if ("FINAL_SUBMISSION".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival is already in a FINAL_SUBMISSION state.");
        }

        // Check if the performance details are valid
        if (performance.getPerformanceName() == null || performance.getPerformanceName().trim().isEmpty() ||
                performance.getPerformanceDescription() == null || performance.getPerformanceDescription().trim().isEmpty() ||
                performance.getPerformanceType() == null || performance.getPerformanceType().trim().isEmpty() ||
                performance.getPerformanceDuration() <= 0 ||
                performance.getPerformanceGroupNames() == null || performance.getPerformanceGroupNames().isEmpty() ||
                performance.getPerformanceTechnicalRequirements() == null || performance.getPerformanceTechnicalRequirements().trim().isEmpty() ||
                performance.getPerformanceMerchandiseItems() == null || performance.getPerformanceMerchandiseItems().trim().isEmpty() ||
                performance.getPerformanceSongList() == null || performance.getPerformanceSongList().isEmpty() ||
                performance.getPerformancePreferredTimeRehearsal() == null ||
                performance.getPerformancePreferredTime() == null) {

            throw new IllegalStateException("The performance details are incomplete.");
        }

        performance.finalSubmissionState();

    }

    // Put add organizer
    @Transactional
    public void addOrganizer(Long performanceId, String organizerName) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        performance.setOrganizer(organizerName);

    }


    // Put Performance rejection
    @Transactional
    public void performanceRejection(Long performanceId, String organizerName, String rejectionReason) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));


        // Check if the given name is on organizer list
        if (!performance.getOrganizer().contains(organizerName)) {
            throw new IllegalStateException("Only the Organizer members can rejection the performance.");
        }

        // Check if the festival is on correct state
        if (!"SCHEDULING".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on scheduling state");
        }

        // Check if the performance is on correct state
        if ("SCHEDULING".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("You can not rejection the performance on this state");
        }

        // Check if the performance is on correct state
        if ("DECISION".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("You can not rejection the performance on this state");
        }

        // Score >= 5 approve , score < 5 reject
        if (performance.getScore() < 5) {
            performance.rejectionState();
            performance.setRejectionReason(rejectionReason);
        } else {
            throw new IllegalStateException("The performance have score > 5, you can not rejection it.");
        }
    }

    // Put performance rejection
    @Transactional
    public void performanceAcceptance(Long performanceId) {

        // Check if exist the performance with the id
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the festival is on correct state
        if ("DECISION".equals(performance.getFestival().getFestivalStatus())) {
            performance.acceptanceState();
        } else {
            throw new IllegalStateException("The festival must be on decision state for accept the performance");
        }

    }

    // Get search
    public List<Performance> searchPerformances(String performanceName, String performanceType) {
        if (performanceName == null && performanceType == null) {
            return PerformanceRepository.findAll();
        }
        if (performanceName == null) {
            return PerformanceRepository.searchPerformanceByType(performanceType);
        }
        if (performanceType == null) {
            return PerformanceRepository.searchPerformanceByName(performanceName);
        }
        return PerformanceRepository.searchPerformance(performanceName, performanceType);
    }


}



