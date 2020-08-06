package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.SectionRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Section;
import org.springframework.stereotype.Repository;

@Repository
public class SectionRepositoryImpl extends AbstractRepository<Section, Integer> implements SectionRepository {

    @Override
    public Class<Section> getEntityClass() {
        return Section.class;
    }
}
