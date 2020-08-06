package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.SectionService;
import com.senlainc.gitcourses.kashko.raman.dto.SectionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {

    private static final Logger LOGGER = LogManager.getLogger(SectionController.class);
    private final SectionService sectionService;

    @Autowired
    public SectionController(@Qualifier("sectionServiceImpl") SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping(value = "/{id}")
    public SectionDto getSectionById(@PathVariable Integer id) {
        return sectionService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSection(@RequestBody SectionDto sectionDto) {
        sectionService.save(sectionDto);
    }

    @GetMapping
    public List<SectionDto> getAllSections() {
        return sectionService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteSectionById(@PathVariable Integer id) throws ObjectNotFoundException {
        return sectionService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public SectionDto updateSection(@PathVariable(name = "id") Integer id, @RequestBody SectionDto sectionDto) throws ObjectNotFoundException {
        return sectionService.update(id, sectionDto);
    }
}
