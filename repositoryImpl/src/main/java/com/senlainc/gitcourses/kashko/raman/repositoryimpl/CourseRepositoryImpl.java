package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.CourseRepository;
import com.senlainc.gitcourses.kashko.raman.entity.Course;
import com.senlainc.gitcourses.kashko.raman.entity.Course_;
import com.senlainc.gitcourses.kashko.raman.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;

@Repository
public class CourseRepositoryImpl extends AbstractRepository<Course, Integer> implements CourseRepository {
    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }


    @Override
    public List<Course> sortByAlphabet() {
        return getEntityManager().createQuery("SELECT c FROM Course c ORDER BY nameOfCourse asc",
                Course.class).getResultList();
    }

    @Override
    public List<Course> getAvailableCourses() {
        return getEntityManager().createQuery("SELECT c FROM Course c where status = 'AVAILABLE'",
                Course.class).getResultList();
    }

    @Override
    public Course getAvailableCourseById(Integer id) {
        TypedQuery<Course> typedQuery = getEntityManager()
                .createQuery("select o from Course  o where id = :id  and status = 'AVAILABLE'", Course.class);
        typedQuery.setParameter("id", id);

        List<Course> results = typedQuery.getResultList();
        if (results.isEmpty()) {
            throw new ObjectNotFoundException("user id:" + id);
        }

        return results.get(0);
    }

    @Override
    public List<Course> getUnavailableCourses() {
        return getEntityManager().createQuery("SELECT c FROM Course c where status = 'UNAVAILABLE'",
                Course.class).getResultList();
    }

    @Override
    public Course getUnavailableCourseById(Integer id) {
        TypedQuery<Course> typedQuery = getEntityManager()
                .createQuery("select o from Course  o where id = :id  and status = 'UNAVAILABLE'", Course.class);
        typedQuery.setParameter("id", id);

        List<Course> results = typedQuery.getResultList();
        if (results.isEmpty()) {
            throw new ObjectNotFoundException("user id:" + id);
        }
        return results.get(0);
    }

    @Override
    public Integer checkCountOfUsersInCourse(Integer courseId) {

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder
                .createQuery(User.class);
        Root<Course> courseRoot = criteriaQuery.from(Course.class);
        criteriaQuery.where(criteriaBuilder.equal(courseRoot.get(Course_.id),
                courseId));
        SetJoin<Course, User> courses = courseRoot
                .join(Course_.users);
        CriteriaQuery<User> cq = criteriaQuery.select(courses);
        TypedQuery<User> query = getEntityManager().createQuery(cq);
        return query.getResultList().size();
    }

    @Override
    public Integer checkSizeOfCourse(Integer id) {
        Integer count;
        Query query = getEntityManager()
                .createQuery("select c.size  from Course as c where id = :id");
        query.setParameter("id", id);
        count = (Integer) query.getSingleResult();
        return count;
    }

    @Override
    public Boolean canAddUserInCourse(Integer id) {
        return checkCountOfUsersInCourse(id) < checkSizeOfCourse(id);
    }

}
