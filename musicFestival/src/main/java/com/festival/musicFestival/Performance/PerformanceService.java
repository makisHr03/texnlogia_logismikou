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


    // post
    public void addNewPerformance(Performance performance) {
        // Check if the festival exists
        Optional<Festival> festivalOptional = festivalRepository.findFestivalByName(performance.getFestival().getFestivalName());
        if (festivalOptional.isEmpty()) {
            throw new IllegalStateException("Festival with name " + performance.getFestival().getFestivalName() + " does not exist");
        }

        // Associate the existing festival with the performance
        performance.setFestival(festivalOptional.get());

        // Check if a performance with the same name already exists for the same festival
        Optional<Performance> performanceOptional = PerformanceRepository
                .findPerformanceByName(performance.getPerformanceName());

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

        if (performance.getFestival().getFestivalStatus().equals("CREATED")) {
            throw new IllegalStateException("The festival is in CREATED state, you can't create new performance on this festival now.");
        }

        // Save the new performance to the repository
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


        if ("FINAL_SUBMISSION".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is on FINAL_SUBMISSION state, you can not change anything.");
        }

        if (!Objects.equals(performance.getPerformanceCreatorName(), performanceCreatorName) &&
                !performance.getKalitexnes().contains(performanceCreatorName)) {
            throw new IllegalStateException("Only the performance creator or existing kalitexnes members can update the performance.");
        }

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

    // put submit
    @Transactional
    public void submit(Long performanceId) {
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));


        if ("SUBMITTED".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is already in a SUBMITTED state.");
        }

        if ("CREATED".equals(performance.getPerformanceStatus())) {

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
        // Retrieve the performance by ID or throw an exception if not found
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        // Check if the requester is the creator or a member of the kalitexnes
        if (!Objects.equals(performance.getPerformanceCreatorName(), performanceCreatorName) &&
                !performance.getKalitexnes().contains(performanceCreatorName)) {
            throw new IllegalStateException("Only the performance creator or existing kalitexnes members can withdraw the performance.");
        }

        // Prevent withdrawal if the performance is already submitted
        if ("SUBMITTED".equalsIgnoreCase(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is already in a SUBMITTED state and cannot be withdrawn.");
        }

        if ("CREATED".equalsIgnoreCase(performance.getPerformanceStatus())) {
            // Delete the performance
            PerformanceRepository.deleteById(performanceId);
        } else {
            throw new IllegalStateException("The performance can not delete on this state.");
        }
    }

    // put staff
    @Transactional
    public void setStaff(Long performanceId, String staffName) {
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if (!"ASSIGNMENT".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on assignment state.");
        }
        performance.setStaff(staffName);
    }


    // put review
    @Transactional
    public void reviewState(Long performanceId) {
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if ("REVIEW".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is already in a Review state.");
        }

        if (!"REVIEW".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on review state.");
        }
        performance.reviewPerformance();
    }


    // put review
    @Transactional
    public void review(Long performanceId, String staffName, float score, String scoreDetails) {
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if (!Objects.equals(performance.getStaff(), staffName)) {
            throw new IllegalStateException("Only the staff can review the performance.");
        }

        if (!"REVIEW".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The performance is not in a Review state.");
        }

        performance.createReview(score, scoreDetails);
    }

    // put scheduling
    @Transactional
    public void scheduling(Long performanceId) {
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if ("SCHEDULING ".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival is already in a SCHEDULING  state.");
        }
        performance.schedulingState();
    }

    // put approval
    @Transactional
    public void approval(Long performanceId) {
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if ("APPROVAL".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival is already in a APPROVAL  state.");
        }

        if (!"SCHEDULING".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival must be in a SCHEDULING state first and after on approval state.");
        }

        performance.approvalState();
    }

    // put final_Sumbision
    @Transactional
    public void finalSubmission(Long performanceId) {
        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if (!"FINAL_SUBMISSION".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on state final submission.");
        }

        if ("FINAL_SUBMISSION".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("The festival is already in a FINAL_SUBMISSION state.");
        }

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

    //put-addOrganizer
    @Transactional
    public void addOrganizer(Long performanceId, String organizerName) {

        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        performance.setOrganizer(organizerName);

    }


    //put-performanceRejection
    @Transactional
    public void performanceRejection(Long performanceId, String organizerName, String rejectionReason) {

        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));


        if (!performance.getOrganizer().contains(organizerName)) {
            throw new IllegalStateException("Only the Organizer members can rejection the performance.");
        }

        if (!"SCHEDULING".equals(performance.getFestival().getFestivalStatus())) {
            throw new IllegalStateException("The Festival is not on scheduling state");
        }

        if ("SCHEDULING".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("You can not rejection the performance on this state");
        }

        if ("DECISION".equals(performance.getPerformanceStatus())) {
            throw new IllegalStateException("You can not rejection the performance on this state");
        }

        if (performance.getScore() < 5) {
            performance.rejectionState();
            performance.setRejectionReason(rejectionReason);
        } else {
            throw new IllegalStateException("The performance have score > 5, you can not rejection it.");
        }
    }

    //put-performanceRejection
    @Transactional
    public void performanceAcceptance(Long performanceId) {

        Performance performance = PerformanceRepository.findById(performanceId)
                .orElseThrow(() -> new IllegalStateException("Performance not found with id: " + performanceId));

        if ("DECISION".equals(performance.getFestival().getFestivalStatus())) {
            performance.acceptanceState();
        } else {
            throw new IllegalStateException("The festival must be on decision state for accept the performance");
        }

    }

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



