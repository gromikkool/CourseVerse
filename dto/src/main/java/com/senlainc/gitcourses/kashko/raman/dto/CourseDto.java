package com.senlainc.gitcourses.kashko.raman.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto {
    private Integer id;
    private String nameOfCourse;
    private SubjectDto subjectDto;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date beginDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    private Integer size;
    private Integer amountHours;
    private StatusDto status;
    private Set<UserShortDto> users;
    private List<LessonShortDto> lessons;
    private List<ReviewShortDto> reviews;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public Set<UserShortDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserShortDto> users) {
        this.users = users;
    }

    public List<ReviewShortDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewShortDto> reviews) {
        this.reviews = reviews;
    }

    public List<LessonShortDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonShortDto> lessons) {
        this.lessons = lessons;
    }

    public SubjectDto getSubjectDto() {
        return subjectDto;
    }

    public void setSubjectDto(SubjectDto subjectDto) {
        this.subjectDto = subjectDto;
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAmountHours() {
        return amountHours;
    }

    public void setAmountHours(Integer amountHours) {
        this.amountHours = amountHours;
    }

}
