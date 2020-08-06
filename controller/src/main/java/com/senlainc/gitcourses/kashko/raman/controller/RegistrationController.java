package com.senlainc.gitcourses.kashko.raman.controller;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.api.service.UserService;
import com.senlainc.gitcourses.kashko.raman.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto registerUser(@RequestBody UserDto userDTO) throws ObjectAlreadyExistsException {
        userService.save(userDTO);
        return userDTO;
    }
}
