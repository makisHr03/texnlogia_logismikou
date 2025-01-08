package com.festival.music_festival.Festival;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping(path = "{festivalId}")
    public void updateFestival(
            @PathVariable("festivalId") Long festivalId,
            @RequestParam(required = false) String festivalName) {

        FestivalService.updateFestival(festivalId, festivalName);
    }

    //del
    @DeleteMapping(path = "{festivalId}")
    public void deleteFestival(@PathVariable("festivalId") Long festivalId) {
        FestivalService.deleteFestival(festivalId);
    }




}
