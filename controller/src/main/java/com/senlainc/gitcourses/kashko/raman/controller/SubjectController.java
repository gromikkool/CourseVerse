package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.SubjectService;
import com.senlainc.gitcourses.kashko.raman.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(value = "/{id}")
    public SubjectDto getSubjectById(@PathVariable Integer id) {
        return subjectService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSubject(@RequestBody SubjectDto subjectDto) {
        subjectService.save(subjectDto);
    }

    @CrossOrigin
    @GetMapping
    public List<SubjectDto> getAllSubjects() {
        return subjectService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteSubjectById(@PathVariable Integer id) throws ObjectNotFoundException {
        return subjectService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public SubjectDto updateSubject(@PathVariable(name = "id") Integer id, @RequestBody SubjectDto subjectDto) throws ObjectNotFoundException {
        return subjectService.update(id, subjectDto);
    }
}
