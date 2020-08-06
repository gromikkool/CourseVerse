package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.ReviewRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl extends AbstractRepository<Review, Integer> implements ReviewRepository {

    @Override
    public Class<Review> getEntityClass() {
        return Review.class;
    }
}
