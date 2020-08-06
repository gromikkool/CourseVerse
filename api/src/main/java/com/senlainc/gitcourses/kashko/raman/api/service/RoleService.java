package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.dto.RoleDto;
import com.senlainc.gitcourses.kashko.raman.entity.RoleEnum;

public interface RoleService extends GenericService<RoleDto, Integer> {
    RoleDto getByName(RoleEnum name);
}
