package com.festival.music_festival.Performance;

import java.time.LocalDate;

public class Performance {
    private Long id;
    private String festival_name;
    private String performance_name;
    private String performance_creator_name;
    private LocalDate performance_date;
    private String performance_description;

    public Performance() {
    }

    public Performance(Long id, String festival_name, String performance_name,
            String performance_creator_name, LocalDate performance_date, String performance_description) {
        this.id = id;
        this.festival_name = festival_name;
        this.performance_name = performance_name;
        this.performance_creator_name = performance_creator_name;
        this.performance_date = performance_date;
        this.performance_description = performance_description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFestival_name() {
        return festival_name;
    }

    public void setFestival_name(String festival_name) {
        this.festival_name = festival_name;
    }

    public String getPerformance_name() {
        return performance_name;
    }

    public void setPerformance_name(String performance_name) {
        this.performance_name = performance_name;
    }

    public String getPerformance_creator_name() {
        return performance_creator_name;
    }

    public void setPerformance_creator_name(String performance_creator_name) {
        this.performance_creator_name = performance_creator_name;
    }

    public LocalDate getPerformance_date() {
        return performance_date;
    }

    public void setPerformance_date(LocalDate performance_date) {
        this.performance_date = performance_date;
    }

    public String getPerformance_description() {
        return performance_description;
    }

    public void setPerformance_description(String performance_description) {
        this.performance_description = performance_description;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "id=" + id +
                ", festival_name='" + festival_name + '\'' +
                ", performance_name='" + performance_name + '\'' +
                ", performance_creator_name='" + performance_creator_name + '\'' +
                ", performance_date=" + performance_date +
                ", performance_description='" + performance_description + '\'' +
                '}';
    }
}
