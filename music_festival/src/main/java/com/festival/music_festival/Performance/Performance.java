package com.festival.music_festival.Performance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
public class Performance {
    @Id
    @SequenceGenerator(name = "performance_sequence", sequenceName = "performance_sequence", allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performance_sequence")

    private Long id;

    private String festival_name;
    private String performance_name;
    private String performance_creator_name;
    private String performace_group_names;
    private LocalDate performance_date;
    private String performance_description;

    // Constructor, getters, setters, and toString() method

    public Performance(String festival_name, String performance_name, String performance_creator_name,
            String performace_group_names, LocalDate performance_date, String performance_description) {
        this.festival_name = festival_name;
        this.performance_name = performance_name;
        this.performance_creator_name = performance_creator_name;
        this.performace_group_names = performace_group_names;
        this.performance_date = performance_date;
        this.performance_description = performance_description;
    }

    public Performance(Long id, String festival_name, String performance_name, String performance_creator_name,
            String performace_group_names, LocalDate performance_date, String performance_description) {
        this.id = id;
        this.festival_name = festival_name;
        this.performance_name = performance_name;
        this.performance_creator_name = performance_creator_name;
        this.performace_group_names = performace_group_names;
        this.performance_date = performance_date;
        this.performance_description = performance_description;
    }

    // Default constructor for JPA
    public Performance() {
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

    public String getPerformace_group_names() {
        return performace_group_names;
    }

    public void setPerformace_group_names(String performace_group_names) {
        this.performace_group_names = performace_group_names;
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
        return "Performance [id=" + id + ", festival_name=" + festival_name + ", performance_name=" + performance_name
                + ", performance_creator_name=" + performance_creator_name + ", performace_group_names="
                + performace_group_names + ", performance_date=" + performance_date + ", performance_description="
                + performance_description + "]";
    }

}