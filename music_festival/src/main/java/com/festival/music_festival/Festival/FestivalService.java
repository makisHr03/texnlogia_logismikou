package com.festival.music_festival.Festival;

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

    @Autowired
    public FestivalService(FestivalRepository FestivalRepository) {
        this.festivalRepository = FestivalRepository;
    }

    // get
    public List<Festival> getFestival() {
        return festivalRepository.findAll();
    }


    // post
    public void addNewFestival(Festival festival) {


        Optional<Festival> festivalOptional = festivalRepository
                .findFestivalByName(festival.getFestivalName());

        if (festival.getFestivalStatus() == null || festival.getFestivalStatus().trim().isEmpty()) {
            festival.setFestivalStatus("CREATED");
        }

        festivalRepository.save(festival);
    }


    // Del
    public void deleteFestival(Long festivalId) {
        boolean exists = festivalRepository.existsById(festivalId);
        if (!exists) {
            throw new IllegalStateException("Festival not found with id: " + festivalId);
        }
        festivalRepository.deleteById(festivalId);
    }

    //put
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

        // Fetch the festival by ID or throw an exception if not found
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with id: " + festivalId));

        // Check the festival status
        if ("ANNOUNCED".equalsIgnoreCase(festival.getFestivalStatus())) {
            throw new IllegalStateException("Cannot update venue, budget, or vendor-related details after the festival is ANNOUNCED.");
        }

        // Update the name if valid and unique
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

        // Update venue layout (stages and vendor areas)
        if (stagesSpace != null) {
            festival.setStagesSpace(stagesSpace);
        }

        if (vendorAreasAndFacilities != null) {
            festival.setVendorAreasAndFacilities(vendorAreasAndFacilities);
        }

        // Update financial details
        if (budgetTracking != null) {
            festival.setBudgetTracking(budgetTracking);
        }

        if (performanceCost != null) {
            festival.setPerformanceCost(performanceCost);
        }
        if (logistics != null) {
            festival.setLogistics(logistics);
        }
        if (expectedRevenue != null) {
            festival.setExpectedRevenue(expectedRevenue);
        }

        // Update staff
        if (staff != null && !staff.isEmpty()) {
            festival.setStaff(staff);
        }
    }

    // put festival ANNOUNCED
    @Transactional
    public void announcedState(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("ANNOUNCED".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the ANNOUNCED state.");
        }


        if (festival.getFestivalName() == null || festival.getDescription() == null ||
                festival.getDates() == null || festival.getStagesSpace() <= 0 ||
                festival.getVendorAreasAndFacilities() <= 0 || festival.getBudgetTracking() <= 0 ||
                festival.getPerformanceCost() <= 0 || festival.getLogistics() <= 0 ||
                festival.getExpectedRevenue() <= 0 || festival.getStaff().isEmpty() ||
                festival.getOrganizer().isEmpty()) {

            throw new IllegalStateException("Festival details are incomplete. It cannot be set to ANNOUNCED state.");
        }


        festival.announcedStatus();
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

    //put add organizer
    @Transactional
    public void addStaff(Long festivalId, String staffName) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if (festival.getStaff().contains(staffName)) {
            throw new IllegalStateException("The " + staffName + " is already on organizer list");
        }

        festival.addStaff(staffName);
    }


    // del festival Deletion
    public void festivalDeletion(Long festivalId, String organizerName) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));

        if(!festival.getOrganizer().contains(organizerName)){
            throw new IllegalStateException("Only the organizer members can delete this Festival.");
        }

        if (!"CREATED".equalsIgnoreCase(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival isn't in CREATED state and cannot be deleted.");
        }

        festivalRepository.deleteById(festivalId);
    }


    // put festival ANNOUNCED
    @Transactional
    public void submissionStatus(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("SUBMISSION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the SUBMISSION state.");
        }

        festival.submissionStatus();
    }

    // put festival ANNOUNCED
    @Transactional
    public void assignmentStatus(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("ASSIGNMENT ".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the ASSIGNMENT   state.");
        }

        if (!"SUBMISSION".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on SUBMISSION state first.");
        }

        festival.assignmentStatus();
    }

    @Transactional
    public void reviewStatus(Long festivalId) {
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new IllegalStateException("Festival not found with ID: " + festivalId));


        if ("REVIEW".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival is already in the ASSIGNMENT  ION state.");
        }

        if (!"ASSIGNMENT".equals(festival.getFestivalStatus())) {
            throw new IllegalStateException("The festival must be on ASSIGNMENT state first.");
        }


        festival.reviewStatus();
    }



}