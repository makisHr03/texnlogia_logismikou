package com.festival.musicFestival.Performance;

import com.festival.musicFestival.Festival.Festival;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "performance")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performance_sequence")
    @SequenceGenerator(name = "performance_sequence", sequenceName = "performance_sequence", allocationSize = 1)
    private Long performanceId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "festival_id")
    private Festival festival;


    private String performanceName;
    private String performanceCreatorName;
    private List<String> performanceGroupNames;
    private LocalDate performanceDate;
    private String performanceType;
    private int performanceDuration;
    private String performanceTechnicalRequirements;
    private String performanceMerchandiseItems;
    private List<String> performanceSongList;
    private LocalDateTime performancePreferredTimeRehearsal;
    private LocalDateTime performancePreferredTime;
    private String performanceDescription;
    private List<String> kalitexnes = new ArrayList<>();
    private String performanceStatus;
    private String staff;
    private float score;
    private String scoreDetail;
    private List<String> organizer = new ArrayList<>();
    private String rejectionReason;

    @CreationTimestamp
    private LocalDateTime performanceCreationDate;


    // Default constructor
    public Performance() {
    }

    // Main constructor
    public Performance(Festival festival, String performanceName, String performanceCreatorName,
                       List<String> performanceGroupNames, LocalDate performanceDate, String performanceType,
                       int performanceDuration, String performanceTechnicalRequirements,
                       String performanceMerchandiseItems, List<String> performanceSongList,
                       LocalDateTime performancePreferredTimeRehearsal, LocalDateTime performancePreferredTime,
                       String performanceDescription) {
        this.festival = festival;
        this.performanceName = performanceName;
        this.performanceCreatorName = performanceCreatorName;
        this.performanceGroupNames = performanceGroupNames;
        this.performanceDate = performanceDate;
        this.performanceType = performanceType;
        this.performanceDuration = performanceDuration;
        this.performanceTechnicalRequirements = performanceTechnicalRequirements;
        this.performanceMerchandiseItems = performanceMerchandiseItems;
        this.performanceSongList = performanceSongList;
        this.performancePreferredTimeRehearsal = performancePreferredTimeRehearsal;
        this.performancePreferredTime = performancePreferredTime;
        this.performanceDescription = performanceDescription;
        this.performanceStatus = "CREATED";

        this.kalitexnes.add(performanceCreatorName);
    }


    // Getters and Setters
    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public String getPerformanceName() {
        return performanceName;
    }

    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    public String getPerformanceCreatorName() {
        return performanceCreatorName;
    }

    public void setPerformanceCreatorName(String performanceCreatorName) {
        this.performanceCreatorName = performanceCreatorName;
    }

    public List<String> getPerformanceGroupNames() {
        return performanceGroupNames;
    }

    public void setPerformanceGroupNames(List<String> performanceGroupNames) {
        this.performanceGroupNames = performanceGroupNames;
    }

    public LocalDate getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(LocalDate performanceDate) {
        this.performanceDate = performanceDate;
    }

    public String getPerformanceType() {
        return performanceType;
    }

    public void setPerformanceType(String performanceType) {
        this.performanceType = performanceType;
    }

    public int getPerformanceDuration() {
        return performanceDuration;
    }

    public void setPerformanceDuration(int performanceDuration) {
        this.performanceDuration = performanceDuration;
    }

    public String getPerformanceTechnicalRequirements() {
        return performanceTechnicalRequirements;
    }

    public void setPerformanceTechnicalRequirements(String performanceTechnicalRequirements) {
        this.performanceTechnicalRequirements = performanceTechnicalRequirements;
    }

    public String getPerformanceMerchandiseItems() {
        return performanceMerchandiseItems;
    }

    public void setPerformanceMerchandiseItems(String performanceMerchandiseItems) {
        this.performanceMerchandiseItems = performanceMerchandiseItems;
    }

    public List<String> getPerformanceSongList() {
        return performanceSongList;
    }

    public void setPerformanceSongList(List<String> performanceSongList) {
        this.performanceSongList = performanceSongList;
    }

    public LocalDateTime getPerformancePreferredTimeRehearsal() {
        return performancePreferredTimeRehearsal;
    }

    public void setPerformancePreferredTimeRehearsal(LocalDateTime performancePreferredTimeRehearsal) {
        this.performancePreferredTimeRehearsal = performancePreferredTimeRehearsal;
    }

    public LocalDateTime getPerformancePreferredTime() {
        return performancePreferredTime;
    }

    public void setPerformancePreferredTime(LocalDateTime performancePreferredTime) {
        this.performancePreferredTime = performancePreferredTime;
    }

    public String getPerformanceDescription() {
        return performanceDescription;
    }

    public void setPerformanceDescription(String performanceDescription) {
        this.performanceDescription = performanceDescription;
    }

    public List<String> getKalitexnes() {
        return kalitexnes;
    }

    public void setKalitexnes(List<String> kalitexnes) {
        this.kalitexnes = kalitexnes;
    }

    public LocalDateTime getPerformanceCreationDate() {
        return performanceCreationDate;
    }

    public void setPerformanceCreationDate(LocalDateTime performanceCreationDate) {
        this.performanceCreationDate = performanceCreationDate;
    }

    public void addKalitexnes(String kalitenxis) {
        if (kalitexnes == null) {
            kalitexnes = new ArrayList<>();
        }
        this.kalitexnes.add(kalitenxis);
    }

    public String getPerformanceStatus() {
        return performanceStatus;
    }

    public void setPerformanceStatus(String performanceStatus) {
        this.performanceStatus = performanceStatus;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public void submitPerformance(){
        this.performanceStatus="SUBMITTED";
    }

    public void reviewPerformance(){
        this.performanceStatus="REVIEW";
    }

    public void createReview(float score, String scoreDetail){
        this.score = score;
        this.scoreDetail = scoreDetail;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getScoreDetail() {
        return scoreDetail;
    }

    public void setScoreDetail(String scoreDetail) {
        this.scoreDetail = scoreDetail;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public List<String> getOrganizer() {
        return organizer;
    }

    public void setOrganizer(List<String> organizer) {
        this.organizer = organizer;
    }

    public void setOrganizer(String organizerName) {
        if (organizer == null) {
            organizer = new ArrayList<>();
        }
        this.organizer.add(organizerName);
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }


    // States
    public void schedulingState(){
        this.performanceStatus = "SCHEDULING";
    }

    public void approvalState(){
        this.performanceStatus = "APPROVAL";
    }

    public void finalSubmissionState(){
        this.performanceStatus = "FINAL_SUBMISSION";
    }

    public void rejectionState(){
        this.performanceStatus = "REJECTION";
    }

    public void acceptanceState() {
        this.performanceStatus = "ACCEPTANCE";
    }

    @Override
    public String toString() {
        return "Performance{" +
                "performanceId=" + performanceId +
                ", festival=" + festival +
                ", performanceName='" + performanceName + '\'' +
                ", performanceCreatorName='" + performanceCreatorName + '\'' +
                ", performanceGroupNames=" + performanceGroupNames +
                ", performanceDate=" + performanceDate +
                ", performanceType='" + performanceType + '\'' +
                ", performanceDuration=" + performanceDuration +
                ", performanceTechnicalRequirements='" + performanceTechnicalRequirements + '\'' +
                ", performanceMerchandiseItems='" + performanceMerchandiseItems + '\'' +
                ", performanceSongList=" + performanceSongList +
                ", performancePreferredTimeRehearsal=" + performancePreferredTimeRehearsal +
                ", performancePreferredTime=" + performancePreferredTime +
                ", performanceDescription='" + performanceDescription + '\'' +
                ", kalitexnes=" + kalitexnes +
                ", performanceStatus='" + performanceStatus + '\'' +
                ", staff='" + staff + '\'' +
                ", score=" + score +
                ", scoreDetail='" + scoreDetail + '\'' +
                ", organizer=" + organizer +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", performanceCreationDate=" + performanceCreationDate +
                '}';
    }
}
