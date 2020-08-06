package com.senlainc.gitcourses.kashko.raman.controller;


import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.service.ProfileService;
import com.senlainc.gitcourses.kashko.raman.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(value = "/{id}")
    public ProfileDto getProfileById(@PathVariable Integer id) {

        return profileService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProfile(@RequestBody ProfileDto profileDto) {
        profileService.save(profileDto);
    }

    @GetMapping
    public List<ProfileDto> getAllProfiles() {
        return profileService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteProfileById(@PathVariable Integer id) throws ObjectNotFoundException {
        return profileService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public ProfileDto updateProfile(@PathVariable(name = "id") Integer id, @RequestBody ProfileDto profileDto) throws ObjectNotFoundException {
        return profileService.update(id, profileDto);
    }
}
