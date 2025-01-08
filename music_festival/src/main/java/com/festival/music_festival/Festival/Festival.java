package com.festival.music_festival.Festival;

import jakarta.persistence.*;


@Entity
@Table(name = "festival")
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "festival_sequence")
    @SequenceGenerator(name = "festival_sequence", sequenceName = "festival_sequence", allocationSize = 1)

    private Long festivalID;

    private String festivalName;
    private String FestivalStatus;

    public Festival() {
    }

    public Festival(String festivalName, String festivalStatus) {
        this.festivalName = festivalName;
        FestivalStatus = festivalStatus;
    }

    public Long getFestivalID() {
        return festivalID;
    }

    public void setFestivalID(Long festivalID) {
        this.festivalID = festivalID;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getFestivalStatus() {
        return FestivalStatus;
    }

    public void setFestivalStatus(String festivalStatus) {
        FestivalStatus = festivalStatus;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "festivalName='" + festivalName + '\'' +
                ", FestivalStatus='" + FestivalStatus + '\'' +
                '}';
    }
}
