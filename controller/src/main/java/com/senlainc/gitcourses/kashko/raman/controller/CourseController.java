package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.CourseService;
import com.senlainc.gitcourses.kashko.raman.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/{id}")
    public CourseDto getCourseById(@PathVariable Integer id) {
        return courseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody CourseDto courseDto) {
        courseService.save(courseDto);
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping(value = "/sort")
    public List<CourseDto> getAllCoursesSortByAlphabet() {
        return courseService.sortByAlphabet();
    }


    @DeleteMapping(value = "/{id}")
    public Integer deleteCourseById(@PathVariable Integer id) throws ObjectNotFoundException {
        return courseService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public CourseDto updateCourses(@PathVariable(name = "id") Integer id, @RequestBody CourseDto courseDto) throws ObjectNotFoundException {
        return courseService.update(id, courseDto);
    }

    @GetMapping(value = "/available")
    public List<CourseDto> getAllAvailableCourses() {
        return courseService.getAvailableCourses();
    }

    @GetMapping(value = "/available/{id}")
    public CourseDto getAvailableCourseById(@PathVariable Integer id) {
        return courseService.getAvailableCourseById(id);
    }

    @GetMapping(value = "/unavailable")
    public List<CourseDto> getAllUnavailableCourses() {
        return courseService.getUnavailableCourses();
    }

    @GetMapping(value = "/unavailable/{id}")
    public CourseDto getUnavailableCourseById(@PathVariable Integer id) {
        return courseService.getUnavailableCourseById(id);
    }

}
