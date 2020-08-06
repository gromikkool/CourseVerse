package com.senlainc.gitcourses.kashko.raman.api.repository;

import com.senlainc.gitcourses.kashko.raman.entity.Course;

import java.util.List;

public interface CourseRepository extends GenericRepository<Course, Integer> {
    List<Course> sortByAlphabet();

    List<Course> getAvailableCourses();

    Course getAvailableCourseById(Integer id);

    List<Course> getUnavailableCourses();

    Course getUnavailableCourseById(Integer id);

    Integer checkSizeOfCourse(Integer id);

    Integer checkCountOfUsersInCourse(Integer id);

    Boolean canAddUserInCourse(Integer id);
}
