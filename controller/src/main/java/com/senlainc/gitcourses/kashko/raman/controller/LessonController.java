package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.LessonService;
import com.senlainc.gitcourses.kashko.raman.dto.LessonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping(value = "/{id}")
    public LessonDto getLessonById(@PathVariable Integer id) {
        return lessonService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLesson(@RequestBody LessonDto lessonDto) {
        lessonService.save(lessonDto);
    }

    @GetMapping
    public List<LessonDto> getAllLessons() {
        return lessonService.getAll();
    }

    @GetMapping(value = "/sort")
    public List<LessonDto> getAllLessonsSortByAlphabet() {
        return lessonService.sortByAlphabet();
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteLessonById(@PathVariable Integer id) throws ObjectNotFoundException {
        return lessonService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public LessonDto updateLesson(@PathVariable(name = "id") Integer id, @RequestBody LessonDto lessonDto) throws ObjectNotFoundException {
        return lessonService.update(id, lessonDto);
    }

    @GetMapping(value = "/available")
    public List<LessonDto> getAvailableLessons() {
        return lessonService.getAvailableLessons();
    }

}
