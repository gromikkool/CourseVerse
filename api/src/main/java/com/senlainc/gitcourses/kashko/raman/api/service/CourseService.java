package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.dto.CourseDto;

import java.util.List;

public interface CourseService extends GenericService<CourseDto, Integer> {
    List<CourseDto> sortByAlphabet();

    CourseDto getAvailableCourseById(Integer id);

    List<CourseDto> getAvailableCourses();

    CourseDto getUnavailableCourseById(Integer id);

    List<CourseDto> getUnavailableCourses();

    void checkValidityOfCourse();
}
