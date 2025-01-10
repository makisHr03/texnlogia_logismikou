package com.festival.music_festival.Festival;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "festival")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "festival_sequence")
    @SequenceGenerator(name = "festival_sequence", sequenceName = "festival_sequence", allocationSize = 1)
    private Long festivalId;

    private String festivalName;
    private String festivalStatus;
    private String description;

    private LocalDate dates;
    private int stagesSpace;
    private int vendorAreasAndFacilities;
    private float budgetTracking;
    private float performanceCost;
    private float logistics;
    private float expectedRevenue;

    //@ElementCollection
    private List<String> staff = new ArrayList<>();

    // @ElementCollection
    private List<String> organizer = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime creationDate;

    // Default constructor
    public Festival() {
    }


    public Festival(Long festivalId, String festivalName, String festivalStatus, String description, LocalDate dates, int stagesSpace, int vendorAreasAndFacilities, float budgetTracking, float performanceCost, float logistics, float expectedRevenue, List<String> staff, List<String> organizer) {
        this.festivalId = festivalId;
        this.festivalName = festivalName;
        this.festivalStatus = festivalStatus;
        this.description = description;
        this.dates = dates;
        this.stagesSpace = stagesSpace;
        this.vendorAreasAndFacilities = vendorAreasAndFacilities;
        this.budgetTracking = budgetTracking;
        this.performanceCost = performanceCost;
        this.logistics = logistics;
        this.expectedRevenue = expectedRevenue;
        this.staff = staff;
        this.organizer = organizer;

    }

    public Festival(String festivalName, String festivalStatus, String description, LocalDate dates, int stagesSpace, int vendorAreasAndFacilities, float budgetTracking, float performanceCost, float logistics, float expectedRevenue, List<String> staff, List<String> organizer) {
        this.festivalName = festivalName;
        this.festivalStatus = festivalStatus;
        this.description = description;
        this.dates = dates;
        this.stagesSpace = stagesSpace;
        this.vendorAreasAndFacilities = vendorAreasAndFacilities;
        this.budgetTracking = budgetTracking;
        this.performanceCost = performanceCost;
        this.logistics = logistics;
        this.expectedRevenue = expectedRevenue;
        this.staff = staff;
        this.organizer = organizer;
    }

    // Getters and Setters
    public Long getFestivalId() {
        return festivalId;
    }

    public void setFestivalId(Long festivalId) {
        this.festivalId = festivalId;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getFestivalStatus() {
        return festivalStatus;
    }

    public void setFestivalStatus(String festivalStatus) {
        this.festivalStatus = festivalStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDates() {
        return dates;
    }

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }

    public int getStagesSpace() {
        return stagesSpace;
    }

    public void setStagesSpace(int stagesSpace) {
        this.stagesSpace = stagesSpace;
    }

    public int getVendorAreasAndFacilities() {
        return vendorAreasAndFacilities;
    }


    public float getBudgetTracking() {
        return budgetTracking;
    }

    public void setBudgetTracking(float budgetTracking) {
        this.budgetTracking = budgetTracking;
    }

    public float getPerformanceCost() {
        return performanceCost;
    }

    public void setPerformanceCost(float performanceCost) {
        this.performanceCost = performanceCost;
    }

    public float getLogistics() {
        return logistics;
    }

    public void setLogistics(float logistics) {
        this.logistics = logistics;
    }

    public float getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(float expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public List<String> getStaff() {
        return staff;
    }

    public void setStaff(List<String> staff) {
        this.staff = staff;
    }

    public List<String> getOrganizer() {
        return organizer;
    }

    public void setOrganizer(List<String> organizer) {
        this.organizer = organizer;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void addStaff(String staffMember) {
        this.staff.add(staffMember);
    }

    public void addOrganizer(String organizerMember) {
        this.organizer.add(organizerMember);
    }

    public void setVendorAreasAndFacilities(int vendorAreasAndFacilities) {
        this.vendorAreasAndFacilities = vendorAreasAndFacilities;
    }

    public void announcedStatus(){
        this.festivalStatus = "ANNOUNCED";
    }

    public void submissionStatus(){
        this.festivalStatus = "SUBMISSION";
    }

    public  void assignmentStatus(){
        this.festivalStatus = "ASSIGNMENT";
    }

    public  void reviewStatus(){
        this.festivalStatus = "REVIEW";
    }


    @Override
    public String toString() {
        return "Festival{" +
                "festivalName='" + festivalName + '\'' +
                ", festivalStatus='" + festivalStatus + '\'' +
                ", organizer=" + organizer +
                '}';
    }
}
