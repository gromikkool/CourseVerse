package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.ProfileRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Profile;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepositoryImpl extends AbstractRepository<Profile, Integer> implements ProfileRepository {

    @Override
    public Class<Profile> getEntityClass() {
        return Profile.class;
    }
}
