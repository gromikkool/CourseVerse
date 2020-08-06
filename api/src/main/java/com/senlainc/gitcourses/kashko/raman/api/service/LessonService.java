package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.dto.LessonDto;

import java.util.List;

public interface LessonService extends GenericService<LessonDto, Integer> {
    List<LessonDto> sortByAlphabet();

    List<LessonDto> getAvailableLessons();

    void checkValidityOfLessons();
}
