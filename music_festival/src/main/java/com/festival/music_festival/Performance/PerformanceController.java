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


}
