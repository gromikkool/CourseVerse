package com.senlainc.gitcourses.kashko.raman.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_lesson")
    private String nameLesson;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "begin_time")
    private Timestamp beginTime;
    @Column(name = "end_time")
    private Timestamp endTime;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "status('AVAILABLE', 'UNAVAILABLE')")
    private Status status;
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Lesson() {
    }

    public Lesson(String nameLesson, Course course, Timestamp beginTime, Status status, Timestamp endTime, List<Review> reviews) {
        this.nameLesson = nameLesson;
        this.course = course;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.reviews = reviews;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    public String getNameLesson() {
        return nameLesson;
    }

    public void setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
