package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.LessonRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonRepositoryImpl extends AbstractRepository<Lesson, Integer> implements LessonRepository {
    @Override
    public Class<Lesson> getEntityClass() {
        return Lesson.class;
    }

    @Override
    public List<Lesson> sortByAlphabet() {
        return getEntityManager().createQuery("SELECT l FROM Lesson l ORDER BY nameLesson asc",
                Lesson.class).getResultList();
    }

    @Override
    public List<Lesson> getAvailableLessons() {
        return getEntityManager().createQuery("SELECT c FROM Lesson c where status = 'AVAILABLE'",
                Lesson.class).getResultList();
    }
}
