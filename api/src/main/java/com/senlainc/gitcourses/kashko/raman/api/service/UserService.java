package com.senlainc.gitcourses.kashko.raman.api.service;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.dto.CourseShortDto;
import com.senlainc.gitcourses.kashko.raman.dto.LessonShortDto;
import com.senlainc.gitcourses.kashko.raman.dto.UserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService extends GenericService<UserDto, Integer> {
    void addCoursesToUser(Integer userId, Integer courseId) throws ObjectNotFoundException;

    List<CourseShortDto> getCoursesOfUser(Integer userId);

    List<LessonShortDto> getLessonsOfUser(Integer userId);

    UserDto getAccountByUsername(String username);

    boolean isPrincipalHasAdminRole(@AuthenticationPrincipal UserDetails userDetails);

    boolean isAdminRoleContainsInDto(UserDto userDto);
}
