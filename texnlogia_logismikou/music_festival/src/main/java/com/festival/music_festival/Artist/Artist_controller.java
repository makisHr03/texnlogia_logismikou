package com.festival.music_festival.Artist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/artist")
public class Artist_controller {

    private final Artist_service artist_service;

    @Autowired
    public Artist_controller(Artist_service artist_service) {
        this.artist_service = artist_service;
    }


    @GetMapping
    public List<Artist> get_artist() {
        return artist_service.get_artist();


    }
}
