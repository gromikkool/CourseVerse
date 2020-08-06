package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;

import java.io.Serializable;
import java.util.List;


public interface GenericService<T, PK extends Serializable> {
    T getById(PK id) throws ObjectNotFoundException;

    List<T> getAll();

    void save(T dto);

    T update(PK id, T dto) throws ObjectNotFoundException;

    Integer delete(Integer id);
}
