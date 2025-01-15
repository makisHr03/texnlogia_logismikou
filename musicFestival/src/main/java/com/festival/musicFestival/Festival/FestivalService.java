package com.festival.musicFestival.Festival;

import com.festival.musicFestival.Performance.Performance;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class FestivalService {


    private final FestivalRepository festivalRepository;

    // Constructor
    @Autowired
    public FestivalService(FestivalRepository FestivalRepository) {
        this.festivalRepository = FestivalRepository;
    }

    // Get all
    public List<Festival> getFestival() {
        return festivalRepository.findAll();
    }


    // post
    public void addNewFestival(Festival festival) {

        // Check if exist the festival with the id
        Optional<Festival> festivalOptional = festivalRepository
                .findFestivalByName(festival.getFestivalName());

        if (festival.getFestivalStatus() == null || festival.getFestivalStatus().trim().isEmpty()) {
            festival.setFestivalStatus("CREATED");
        }

        festivalRepository.save(festival);
    }


//    // Del for testing
//    public void deleteFestival(Long festivalId) {
//        // Check if exist the festival with the id
//        boolean exists = festivalRepository.existsById(festivalId);
//        if (!exists) {
//            throw new IllegalStateException("Festival not found with id: " + festivalId);
//        }
//        festivalRepository.deleteById(festivalId);
//    }

    //put festival
    @Transactional
    public void updateFestival(Long festivalId,
                               String festivalName,
                               String description,
                               LocalDate dates,
                               Integer stagesSpace,
                               Integer vendorAreasAndFacilities,
                               Float budgetTracking,
                               Float performanceCost,
                               Float logistics,
                               Float expectedRevenue,
                               List<String> staff) {

        // Check if exist the festival with the id
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with id: " + festivalId));

        // Check the festival status
        if ("ANNOUNCED".equalsIgnoreCase(festival.getFestivalStatus())) {
            throw new IllegalStateException("Cannot update venue, budget, or vendor-related details after the festival is ANNOUNCED.");
        }

        // Update the name if valid
        if (festivalName != null && !festivalName.trim().isEmpty() &&
                !Objects.equals(festival.getFestivalName(), festivalName)) {
            festivalRepository.findFestivalByName(festivalName).ifPresent(existingFestival -> {
                throw new IllegalStateException("Festival name " + festivalName + " is already taken.");
            });
            festival.setFestivalName(festivalName);
        }

        // Update description
        if (description != null && !description.trim().isEmpty()) {
            festival.setDescription(description);
        }

        // Update dates
        if (dates != null) {
            festival.setDates(dates);
        }

        // Update venue layout
        if (stagesSpace != null) {
            festival.setStagesSpace(stagesSpace);
        }

        // Update vendor Areas And Facilities
        if (vendorAreasAndFacilities != null) {
            festival.setVendorAreasAndFacilities(vendorAreasAndFacilities);
        }

        // Update financial details
        if (budgetTracking != null) {
            festival.setBudgetTracking(budgetTracking);
        }

        // Update performance cost
        if (performanceCost != null) {
            festival.setPerformanceCost(performanceCost);
        }

        // Update logistics
        if (logistics != null) {
            festival.setLogistics(logistics);
        }

        // Update expected revenue
        if (expectedRevenue != null) {
            festival.setExpectedRevenue(expectedRevenue);
        }

        // Update staff
        if (staff != null && !staff.isEmpty()) {
            festival.setStaff(staff);
        }
    }


    //put add organizer
    @Transactional
    public void addOrganizer(Long festivalId, String organizerName) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if (festival.getOrganizer().contains(organizerName)) {
            throw new IllegalStateException("The " + organizerName + " is already on organizer list");
        }

        festival.addOrganizer(organizerName);
    }

    //put add staff
    @Transactional
    public void addStaff(Long festivalId, String staffName) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if (festival.getStaff().contains(staffName)) {
            throw new IllegalStateException("The " + staffName + " is already on organizer list");
        }

        festival.addStaff(staffName);
    }


    // States
    // del festival Deletion state
    public void festivalDeletion(Long festivalId, String organizerName) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));

        if (!festival.getOrganizer().contains(organizerName)) {
            throw new IllegalStateException("Only the organizer members can delete this Festival.");
        }

        if (!"CREATED".equalsIgnoreCase(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival isn't in CREATED state and cannot be deleted.");
        }

        festivalRepository.deleteById(festivalId);
    }


    // put festival submission state
    @Transactional
    public void submissionStatus(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("SUBMISSION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the SUBMISSION state.");
        }

        festival.submissionState();
    }

    // put festival assignment state
    @Transactional
    public void assignmentStatus(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("ASSIGNMENT ".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the ASSIGNMENT state.");
        }

        if (!"SUBMISSION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on SUBMISSION state first.");
        }

        festival.assignmentState();
    }

    //put Review State
    @Transactional
    public void reviewStatus(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("REVIEW".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the review state.");
        }

        if (!"ASSIGNMENT".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on ASSIGNMENT state first.");
        }


        festival.reviewState();
    }

    // put schedule state
    @Transactional
    public void scheduleStatus(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("SCHEDULING".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the scheduling state.");
        }

        if (!"REVIEW".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on review state first.");
        }


        festival.scheduleState();
    }

    // put FINAL_SUBMISSION state
    @Transactional
    public void finalSubmissionState(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("FINAL_SUBMISSION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the final FINAL_SUBMISSION state.");
        }

        if (!"SCHEDULING".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on scheduling state first.");
        }

        festival.finalSubmissionState();
    }

    // put decision state
    @Transactional
    public void decisionState(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("DECISION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the decision state.");
        }

        if (!"FINAL_SUBMISSION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on FINAL_SUBMISSION state first.");
        }

        festival.decisionState();
    }

    // put festival ANNOUNCED state
    @Transactional
    public void announcedState(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("ANNOUNCED".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the ANNOUNCED state.");
        }

        if (!"DECISION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on decision state first.");
        }


        if (festival.getFestivalName() == null || festival.getDescription() == null ||
                festival.getDates() == null || festival.getStagesSpace() <= 0 ||
                festival.getVendorAreasAndFacilities() <= 0 || festival.getBudgetTracking() <= 0 ||
                festival.getPerformanceCost() <= 0 || festival.getLogistics() <= 0 ||
                festival.getExpectedRevenue() <= 0 || festival.getStaff().isEmpty() ||
                festival.getOrganizer().isEmpty()) {

            throw new IllegalStateException("Festival details are incomplete. It cannot be set to ANNOUNCED state.");
        }


        festival.announcedState();
    }

    public List<Festival> searchFestival(String festivalName, String description) {
        if (festivalName == null && description == null) {
            return festivalRepository.findAll();
        }
        if (festivalName == null) {
            return festivalRepository.searchFestivalByDecription(description);
        }
        if (description == null) {
            return festivalRepository.searchFestivalByName(festivalName);
        }
        return festivalRepository.searchFestival(festivalName, description);
    }


}