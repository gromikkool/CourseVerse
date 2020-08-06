package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.LoginNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.UserRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Course;
import com.senlainc.gitcourses.kashko.raman.entity.User;
import com.senlainc.gitcourses.kashko.raman.entity.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User, Integer> implements UserRepository {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }


    @Override
    public User getAccountByUsername(String username) throws LoginNotFoundException {
            TypedQuery<User> query = getEntityManager().createQuery(
                    "select o from User o  where login = :login", User.class);
            query.setParameter("login", username);
            try {
                return query.getSingleResult();
            }catch (NoResultException e) {
                throw new LoginNotFoundException("Login not found");
            }
    }

    @Override
    public List<Course> getCoursesOfUser(Integer userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder
                .createQuery(Course.class);
        Root<User> courseRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(courseRoot.get(User_.id),
                userId));
        SetJoin<User, Course> users = courseRoot
                .join(User_.courses);
        CriteriaQuery<Course> cq = criteriaQuery.select(users);
        TypedQuery<Course> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

}
