package com.senlainc.gitcourses.kashko.raman.api.repository;


import com.senlainc.gitcourses.kashko.raman.entity.Lesson;

import java.util.List;

public interface LessonRepository extends GenericRepository<Lesson, Integer> {
    List<Lesson> sortByAlphabet();

    List<Lesson> getAvailableLessons();
}
