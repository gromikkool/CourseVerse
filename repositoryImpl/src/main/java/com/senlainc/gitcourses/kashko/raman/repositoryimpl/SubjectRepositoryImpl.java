package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.SubjectRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Subject;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepositoryImpl extends AbstractRepository<Subject, Integer> implements SubjectRepository {
    @Override
    public Class<Subject> getEntityClass() {
        return Subject.class;
    }
}
