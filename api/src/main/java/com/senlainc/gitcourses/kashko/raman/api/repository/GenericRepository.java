package com.senlainc.gitcourses.kashko.raman.api.repository;


import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T, PK extends Serializable> {
    T getById(PK id) throws ObjectNotFoundException;

    List<T> getAll();

    void save(T t);

    T update(T t);

    void delete(T t);
}
