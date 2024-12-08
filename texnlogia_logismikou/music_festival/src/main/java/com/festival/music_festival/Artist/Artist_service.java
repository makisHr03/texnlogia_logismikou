package com.festival.music_festival.Artist;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class Artist_service {
    public List<Artist> get_artist() {
        return List.of(
                new Artist(
                        2L,
                        "BTS",
                        "perm_test"));
    }
}
