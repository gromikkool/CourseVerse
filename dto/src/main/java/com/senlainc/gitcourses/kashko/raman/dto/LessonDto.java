package com.senlainc.gitcourses.kashko.raman.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonDto {
    private Integer id;
    private String nameLesson;
    private CourseShortDto course;
    private Timestamp beginTime;
    private Timestamp endTime;
    private List<ReviewShortDto> reviews;
    private StatusDto status;

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public void setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
    }

    public CourseShortDto getCourse() {
        return course;
    }

    public void setCourse(CourseShortDto course) {
        this.course = course;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public List<ReviewShortDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewShortDto> reviews) {
        this.reviews = reviews;
    }
}
