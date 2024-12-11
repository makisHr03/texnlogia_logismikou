package com.festival.music_festival.Performance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table
public class Performance {
    @Id
    @SequenceGenerator(name = "performance_sequence", sequenceName = "performance_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performance_sequence")
    private Long performace_id;

    private String festival_name;
    private String performance_name;
    private String performance_creator_name;
    private String performance_group_names;
    private LocalDate performance_date;
    private String performance_type;
    private int performance_duration;
    private String performance_technical_requirements;
    private String performance_merchandise_items;

    public void setPerformace_id(Long performace_id) {
        this.performace_id = performace_id;
    }

    private ArrayList<String> performance_song_list;
    private ArrayList<LocalDateTime> performance_preferred_time_rehearsal;
    private ArrayList<LocalDateTime> performance_preferred_time;

    @CreationTimestamp
    private Timestamp performance_creasion_date;

    private String performance_description;

    // Default constructor
    public Performance() {
    }

    // Main constractor
    public Performance(String festival_name, String performance_name, String performance_creator_name,
            String performance_group_names, LocalDate performance_date, String performance_type,
            int performance_duration,
            String performance_technical_requirements, String performance_merchandise_items,
            ArrayList<String> performance_song_list, ArrayList<LocalDateTime> performance_preferred_time_rehearsal,
            ArrayList<LocalDateTime> performance_preferred_time, String performance_description) {
        this.festival_name = festival_name;
        this.performance_name = performance_name;
        this.performance_creator_name = performance_creator_name;
        this.performance_group_names = performance_group_names;
        this.performance_date = performance_date;
        this.performance_type = performance_type;
        this.performance_duration = performance_duration;
        this.performance_technical_requirements = performance_technical_requirements;
        this.performance_merchandise_items = performance_merchandise_items;
        this.performance_song_list = performance_song_list;
        this.performance_preferred_time_rehearsal = performance_preferred_time_rehearsal;
        this.performance_preferred_time = performance_preferred_time;
        this.performance_description = performance_description;
    }

    // for delete
    public Performance(
            Long performace_id, String festival_name, String performance_name, String performance_creator_name,
            String performance_group_names, LocalDate performance_date, String performance_type,
            int performance_duration,
            String performance_technical_requirements, String performance_merchandise_items,
            ArrayList<String> performance_song_list, ArrayList<LocalDateTime> performance_preferred_time_rehearsal,
            ArrayList<LocalDateTime> performance_preferred_time, Timestamp performance_creasion_date,
            String performance_description) {
        this.performace_id = performace_id;
        this.festival_name = festival_name;
        this.performance_name = performance_name;
        this.performance_creator_name = performance_creator_name;
        this.performance_group_names = performance_group_names;
        this.performance_date = performance_date;
        this.performance_type = performance_type;
        this.performance_duration = performance_duration;
        this.performance_technical_requirements = performance_technical_requirements;
        this.performance_merchandise_items = performance_merchandise_items;
        this.performance_song_list = performance_song_list;
        this.performance_preferred_time_rehearsal = performance_preferred_time_rehearsal;
        this.performance_preferred_time = performance_preferred_time;
        this.performance_creasion_date = performance_creasion_date;
        this.performance_description = performance_description;
    }

    public Long getPerformace_id() {
        return performace_id;
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

    public String getPerformance_group_names() {
        return performance_group_names;
    }

    public void setPerformance_group_names(String performance_group_names) {
        this.performance_group_names = performance_group_names;
    }

    public LocalDate getPerformance_date() {
        return performance_date;
    }

    public void setPerformance_date(LocalDate performance_date) {
        this.performance_date = performance_date;
    }

    public String getPerformance_type() {
        return performance_type;
    }

    public void setPerformance_type(String performance_type) {
        this.performance_type = performance_type;
    }

    public int getPerformance_duration() {
        return performance_duration;
    }

    public void setPerformance_duration(int performance_duration) {
        this.performance_duration = performance_duration;
    }

    public String getPerformance_technical_requirements() {
        return performance_technical_requirements;
    }

    public void setPerformance_technical_requirements(String performance_technical_requirements) {
        this.performance_technical_requirements = performance_technical_requirements;
    }

    public String getPerformance_merchandise_items() {
        return performance_merchandise_items;
    }

    public void setPerformance_merchandise_items(String performance_merchandise_items) {
        this.performance_merchandise_items = performance_merchandise_items;
    }

    public ArrayList<String> getPerformance_song_list() {
        return performance_song_list;
    }

    public void setPerformance_song_list(ArrayList<String> performance_song_list) {
        this.performance_song_list = performance_song_list;
    }

    public ArrayList<LocalDateTime> getPerformance_preferred_time_rehearsal() {
        return performance_preferred_time_rehearsal;
    }

    public void setPerformance_preferred_time_rehearsal(ArrayList<LocalDateTime> performance_preferred_time_rehearsal) {
        this.performance_preferred_time_rehearsal = performance_preferred_time_rehearsal;
    }

    public ArrayList<LocalDateTime> getPerformance_preferred_time() {
        return performance_preferred_time;
    }

    public void setPerformance_preferred_time(ArrayList<LocalDateTime> performance_preferred_time) {
        this.performance_preferred_time = performance_preferred_time;
    }

    public Timestamp getPerformance_creasion_date() {
        return performance_creasion_date;
    }

    public void setPerformance_creasion_date(Timestamp performance_creasion_date) {
        this.performance_creasion_date = performance_creasion_date;
    }

    public String getPerformance_description() {
        return performance_description;
    }

    public void setPerformance_description(String performance_description) {
        this.performance_description = performance_description;
    }

    @Override
    public String toString() {
        return "Performance [performace_id=" + performace_id + ", festival_name="
                + festival_name + ", performance_name=" + performance_name + ", performance_creator_name="
                + performance_creator_name + ", performance_group_names=" + performance_group_names
                + ", performance_date=" + performance_date + ", performance_type=" + performance_type
                + ", performance_duration=" + performance_duration + ", performance_technical_requirements="
                + performance_technical_requirements + ", performance_merchandise_items="
                + performance_merchandise_items + ", performance_song_list=" + performance_song_list
                + ", performance_preferred_time_rehearsal=" + performance_preferred_time_rehearsal
                + ", performance_preferred_time=" + performance_preferred_time + ", performance_creasion_date="
                + performance_creasion_date + ", performance_description=" + performance_description + "]";
    }

}