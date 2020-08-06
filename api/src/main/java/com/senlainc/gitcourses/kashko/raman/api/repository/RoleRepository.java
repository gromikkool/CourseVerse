package com.senlainc.gitcourses.kashko.raman.api.repository;

import com.senlainc.gitcourses.kashko.raman.entity.Role;

public interface RoleRepository extends GenericRepository<Role, Integer> {
    Role getByName(String name);
}
