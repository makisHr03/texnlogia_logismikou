package com.festival.musicFestival.Performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/performance")
public class PerformanceController {

    private final PerformanceService PerformanceService;

    @Autowired
    public PerformanceController(PerformanceService PerformanceService) {
        this.PerformanceService = PerformanceService;
    }

    // Get all
    @GetMapping
    public List<Performance> getPerformances() {
        return PerformanceService.getPerformance();
    }

    // Post add new performance
    @Autowired
    private PerformanceService performanceService;

    @PostMapping
    public void registerNewPerformance(@RequestBody Performance performance) {
        performanceService.addNewPerformance(performance);
    }

//    // Del, for testing
//    @DeleteMapping(path = "{performanceId}")
//    public void deletePerformance(@PathVariable("performanceId") Long performanceId) {
//        PerformanceService.deletePerformance(performanceId);
//    }

    // Put update performance
    @PutMapping(path = "{performanceId}")
    public void updatePerformance(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String performanceName,
            @RequestParam(required = false) List<String> performanceGroupNames,
            @RequestParam(required = false) String performanceType,
            @RequestParam(required = false) Integer performanceDuration,
            @RequestParam(required = false) String performanceTechnicalRequirements,
            @RequestParam(required = false) String performanceMerchandiseItems,
            @RequestParam(required = false) List<String> performanceSongList,
            @RequestParam(required = false) LocalDateTime performancePreferredTimeRehearsal,
            @RequestParam(required = false) LocalDateTime performancePreferredTime,
            @RequestParam(required = false) String performanceDescription,
            @RequestParam(required = false) String performanceCreatorName) {

        PerformanceService.updatePerformance(performanceId, performanceName, performanceGroupNames,
                performanceType, performanceDuration, performanceTechnicalRequirements, performanceMerchandiseItems,
                performanceSongList, performancePreferredTimeRehearsal, performancePreferredTime, performanceDescription,
                performanceCreatorName);
    }

    // Put add kalitexnes
    @PutMapping(path = "{performanceId}/addKalitexnes")
    public void addKalitexnes(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String kalitexnis,
            @RequestParam(required = false) String performanceCreatorName) {

        // Call the service method to update kalitexnes after validation
        PerformanceService.addKalitexnes(performanceId, kalitexnis, performanceCreatorName);
    }


    // Del withdrawal
    @DeleteMapping(path = "{performanceId}/withdrawal")
    public void withdrawal(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String kalitexnis) {
        PerformanceService.withdrawal(performanceId, kalitexnis);
    }

    // Put add staff
    @PutMapping(path = "{performanceId}/staff")
    public void setStaff(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String staffName) {
        PerformanceService.setStaff(performanceId, staffName);

    }

    //put add organizer
    @PutMapping(path = "{performanceId}/addOrganizer")
    public void addOrganizer(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String organizerName) {
        PerformanceService.addOrganizer(performanceId, organizerName);
    }


    // States
    // Put submit state
    @PutMapping(path = "{performanceId}/submitState")
    public void submitPerformance(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.submit(performanceId);

    }

    // Put review state
    @PutMapping(path = "{performanceId}/reviewState")
    public void reviewState(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.reviewState(performanceId);

    }

    // Put review State
    @PutMapping(path = "{performanceId}/review")
    public void reviewPerformance(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String staffName,
            @RequestParam(required = false) float score,
            @RequestParam(required = false) String scoreDetail) {
        PerformanceService.review(performanceId, staffName, score, scoreDetail);
    }

    // Put scheduling state
    @PutMapping(path = "{performanceId}/schedulingState")
    public void schedulingPerformance(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.scheduling(performanceId);
    }

    // Put approval state
    @PutMapping(path = "{performanceId}/approvalState")
    public void approvalPerformance(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.approval(performanceId);
    }

    // Put final submission state
    @PutMapping(path = "{performanceId}/finalSubmission")
    public void finalSubmission(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.finalSubmission(performanceId);
    }

    // Put rejection state
    @PutMapping(path = "{performanceId}/performanceManualRejection")
    public void performanceRejection(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String organizerName,
            @RequestParam(required = false) String rejectionReason) {
        PerformanceService.performanceRejection(performanceId, organizerName, rejectionReason);
    }

    // Put Performance status acceptance
    @PutMapping(path = "{performanceId}/performanceAcceptance")
    public void performanceRejection(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.performanceAcceptance(performanceId);
    }

    @GetMapping("/search")
    public List<Performance> searchPerformances(
            @RequestParam(required = false) String performanceName,
            @RequestParam(required = false) String performanceType) {

        return performanceService.searchPerformances(performanceName, performanceType);

    }
}
