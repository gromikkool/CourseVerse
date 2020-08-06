package com.senlainc.gitcourses.kashko.raman.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseShortDto {
    private Integer id;
    private String nameOfCourse;
    private StatusDto status;
    private List<LessonShortDto> lessons;

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public List<LessonShortDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonShortDto> lessons) {
        this.lessons = lessons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }
}
