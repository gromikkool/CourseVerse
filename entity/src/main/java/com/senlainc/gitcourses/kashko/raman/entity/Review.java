package com.senlainc.gitcourses.kashko.raman.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(1)
    @Max(5)
    private Double rating;
    private String message;
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public Review() {
    }

    public Review(Double rating, String message, Course course, Lesson lesson) {
        this.rating = rating;
        this.message = message;
        this.course = course;
        this.lesson = lesson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
