package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.repository.RoleRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role, Integer> implements RoleRepository {
    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }


    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = getEntityManager().createQuery(
                "select o from Role o  where nameOfRole = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
