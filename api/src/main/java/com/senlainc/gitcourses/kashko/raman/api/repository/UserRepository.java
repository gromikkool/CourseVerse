package com.senlainc.gitcourses.kashko.raman.api.repository;

import com.senlainc.gitcourses.kashko.raman.entity.Course;
import com.senlainc.gitcourses.kashko.raman.entity.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User, Integer> {
    User getAccountByUsername(String username);

    List<Course> getCoursesOfUser(Integer userId);
}
