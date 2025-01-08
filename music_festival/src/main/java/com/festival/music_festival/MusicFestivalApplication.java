package com.festival.music_festival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.festival")
public class MusicFestivalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicFestivalApplication.class, args);
    }
}