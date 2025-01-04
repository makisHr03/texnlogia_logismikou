package com.festival.music_festival.Performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/performance")
public class PerformanceController {

    private final PerformanceService PerformanceService;

    @Autowired
    public PerformanceController(PerformanceService PerformanceService) {
        this.PerformanceService = PerformanceService;
    }

    //get
    @GetMapping
    public List<Performance> getPerformances() {
        return PerformanceService.getPerformance();
    }

    //post
    @PostMapping
    public void registerNewPerformance(@RequestBody Performance performance) {
        PerformanceService.addNewPerformance(performance);
    }


    //del
    @DeleteMapping(path = "{performanceId}")
    public void deletePerformance(@PathVariable("performanceId") Long performanceId) {
        PerformanceService.deletePerformance(performanceId);
    }

    //put
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

    //put-kalitexnes
    @PutMapping(path = "{performanceId}/addKalitexnes")
    public void addKalitexnes(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String kalitexnis,
            @RequestParam(required = false) String performanceCreatorName) {

        // Call the service method to update kalitexnes after validation
        PerformanceService.addKalitexnes(performanceId, kalitexnis, performanceCreatorName);
    }

    //put-submit-state
    @PutMapping(path = "{performanceId}/submitState")
    public void submitPerformance(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.submit(performanceId);

    }

    //del withdrawal
    @DeleteMapping(path = "{performanceId}/withdrawal")
    public void withdrawal(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String kalitexnis) {
        PerformanceService.withdrawal(performanceId, kalitexnis);
    }

    //put-staff
    @PutMapping(path = "{performanceId}/staff")
    public void setStaff(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String staffName) {
        PerformanceService.setStaff(performanceId, staffName);

    }

    //put-review-state
    @PutMapping(path = "{performanceId}/reviewState")
    public void reviewState(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.reviewState(performanceId);

    }

    //put-review
    @PutMapping(path = "{performanceId}/review")
    public void reviewPerformance(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String staffName,
            @RequestParam(required = false) float score,
            @RequestParam(required = false) String scoreDetail) {
        PerformanceService.review(performanceId, staffName, score, scoreDetail);
    }

    //put-scheduling-state
    @PutMapping(path = "{performanceId}/schedulingState")
    public void schedulingPerformance(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.scheduling(performanceId);
    }

    //put-approval-state
    @PutMapping(path = "{performanceId}/approvalState")
    public void approvalPerformance(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.approval(performanceId);
    }

    //put-final_Sumbision
    @PutMapping(path = "{performanceId}/finalSumbision")
    public void finalSumbision(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.finalSumbision(performanceId);
    }

    //put add organizer
    @PutMapping(path = "{performanceId}/addOrganizer")
    public void addOrganizer(
            @PathVariable("performanceId") Long performanceId,
            @RequestParam(required = false) String organizerName) {
        PerformanceService.addOrganizer(performanceId, organizerName);
    }

    //put festival status DECISION
    @PutMapping(path = "{performanceId}/festivalDecision")
    public void festivalDecision(
            @PathVariable("performanceId") Long performanceId) {
        PerformanceService.festivalDecision(performanceId);
    }


}
