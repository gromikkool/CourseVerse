package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.LoginAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectIsFullException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.UserService;
import com.senlainc.gitcourses.kashko.raman.dto.CourseShortDto;
import com.senlainc.gitcourses.kashko.raman.dto.LessonShortDto;
import com.senlainc.gitcourses.kashko.raman.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable Integer id) throws ObjectNotFoundException {
        return userService.getById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDto userDto) throws LoginAlreadyExistsException {
        userService.save(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteUserById(@PathVariable Integer id) throws ObjectNotFoundException {
        return userService.delete(id);
    }

    @PutMapping(value = "/courses/{courseId}")
    public void addCourseToUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer courseId) throws ObjectNotFoundException, ObjectIsFullException {
        UserDto authenticatedUser = userService.getAccountByUsername(userDetails.getUsername());
        int userId = authenticatedUser.getId();
        userService.addCoursesToUser(userId, courseId);
    }

    @GetMapping(value = "/logged")
    public UserDto getLoggedUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserDto authenticatedUser = userService.getAccountByUsername(userDetails.getUsername());
        int userId = authenticatedUser.getId();
        return userService.getById(userId);
    }

    @PutMapping(value = "/{id}")
    public UserDto updateUser(@PathVariable(name = "id") Integer id, @RequestBody UserDto userDto) throws ObjectNotFoundException {
        return userService.update(id, userDto);
    }

    @GetMapping(value = "/courses")
    public List<CourseShortDto> getCoursesOfUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserDto authenticatedUser = userService.getAccountByUsername(userDetails.getUsername());
        int userId = authenticatedUser.getId();
        return userService.getCoursesOfUser(userId);
    }

    @GetMapping(value = "/lessons")
    public List<LessonShortDto> getLessonsOfUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserDto authenticatedUser = userService.getAccountByUsername(userDetails.getUsername());
        int userId = authenticatedUser.getId();
        return userService.getLessonsOfUser(userId);
    }
}
