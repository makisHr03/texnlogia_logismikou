package com.festival.music_festival.Festival;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/festival")
public class FestivalController {

    private final com.festival.music_festival.Festival.FestivalService FestivalService;

    @Autowired
    public FestivalController(FestivalService FestivalService) {
        this.FestivalService = FestivalService;
    }

    //get
    @GetMapping
    public List<Festival> getFestival() {
        return FestivalService.getFestival();
    }

    //post
    @PostMapping
    public void registerNewFestival(@RequestBody Festival festival) {
        FestivalService.addNewFestival(festival);
    }

    //put
    @PutMapping("/{festivalId}")
    public void updateFestival(
            @PathVariable("festivalId") Long festivalId,
            @RequestParam(required = false) String festivalName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) LocalDate dates,
            @RequestParam(required = false) Integer stagesSpace,
            @RequestParam(required = false) Integer vendorAreasAndFacilities,
            @RequestParam(required = false) Float budgetTracking,
            @RequestParam(required = false) Float performanceCost,
            @RequestParam(required = false) Float logistics,
            @RequestParam(required = false) Float expectedRevenue,
            @RequestBody(required = false) List<String> staff) {

        // Calling the service to update the festival information
        FestivalService.updateFestival(festivalId, festivalName, description, dates, stagesSpace,
                vendorAreasAndFacilities, budgetTracking, performanceCost, logistics,
                expectedRevenue, staff);
    }

    //del
    @DeleteMapping(path = "{festivalId}")
    public void deleteFestival(@PathVariable("festivalId") Long festivalId) {
        FestivalService.deleteFestival(festivalId);
    }

    //put-approval-state
    @PutMapping(path = "{festivalId}/announcedState")
    public void announcedState(
            @PathVariable("festivalId") Long festivalId) {
        FestivalService.announcedState(festivalId);
    }

    //put-add organizer
    @PutMapping(path = "{festivalId}/addOrganizer")
    public void addOrganizer(
            @PathVariable("festivalId") Long festivalId,
            @RequestParam(required = false) String organizerName) {
        FestivalService.addOrganizer(festivalId, organizerName);
    }

    //put-add organizer
    @PutMapping(path = "{festivalId}/addStaff")
    public void addStaff(
            @PathVariable("festivalId") Long festivalId,
            @RequestParam(required = false) String staffName) {
        FestivalService.addStaff(festivalId, staffName);
    }

    //put-Festival-Deletion
    @DeleteMapping(path = "{festivalId}/festivalDeletion")
    public void FestivalDeletion(
            @PathVariable("festivalId") Long festivalId,
            @RequestParam(required = false) String organizerName) {
        FestivalService.festivalDeletion(festivalId, organizerName);
    }

    //put-submission-state
    @PutMapping(path = "{festivalId}/submissionState")
    public void submissionState(
            @PathVariable("festivalId") Long festivalId) {
        FestivalService.submissionStatus(festivalId);
    }

    //put-assignment-state
    @PutMapping(path = "{festivalId}/assignmentState")
    public void assignmentState(
            @PathVariable("festivalId") Long festivalId) {
        FestivalService.assignmentStatus(festivalId);
    }

    //put-review-state
    @PutMapping(path = "{festivalId}/reviewState")
    public void reviewState(
            @PathVariable("festivalId") Long festivalId) {
        FestivalService.reviewStatus(festivalId);
    }
}