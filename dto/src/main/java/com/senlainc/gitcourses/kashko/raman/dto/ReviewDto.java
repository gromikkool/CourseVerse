package com.senlainc.gitcourses.kashko.raman.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto {
    private Integer id;
    private Double rating;
    private String message;
    private CourseShortDto course;
    private LessonShortDto lesson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CourseShortDto getCourse() {
        return course;
    }

    public void setCourse(CourseShortDto course) {
        this.course = course;
    }

    public LessonShortDto getLesson() {
        return lesson;
    }

    public void setLesson(LessonShortDto lesson) {
        this.lesson = lesson;
    }
}