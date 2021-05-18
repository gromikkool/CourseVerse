package com.senlainc.gitcourses.kashko.raman.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_course")
    private String nameOfCourse;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Column(name = "begin_date", columnDefinition = "timestamp")
    private Date beginDate;
    @Column(name = "end_date", columnDefinition = "timestamp")
    private Date endDate;
    @Column(name = "amount_hours")
    private Integer amountHours;
    @Column(name = "size")
    private Integer size;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "status('AVAILABLE', 'UNAVAILABLE')")
    private Status status;
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Course() {
    }

    public Course(Date beginDate, Date endDate, String nameOfCourse, Status status, Subject subject, Integer amountHours, Set<User> users, List<Lesson> lessons, List<Review> reviews, Integer size) {
        this.nameOfCourse = nameOfCourse;
        this.subject = subject;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.amountHours = amountHours;
        this.users = users;
        this.lessons = lessons;
        this.reviews = reviews;
        this.status = status;
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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
