package com.senlainc.gitcourses.kashko.raman.controller.webexception;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.LoginAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectIsFullException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerWeb {


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ServerDto ObjectNotFoundCatcher() {
        final ServerDto serverDto = new ServerDto();
        serverDto.setCode(2);
        serverDto.setMessage("Sorry. You are trying to get non existing resource.");
        return serverDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ServerDto ObjectAlreadyExistsException() {
        final ServerDto serverDto = new ServerDto();
        serverDto.setCode(3);
        serverDto.setMessage("Sorry, such object already exists.");
        return serverDto;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ServerDto loginAlreadyExistsException() {
        final ServerDto serverDto = new ServerDto();
        serverDto.setCode(3);
        serverDto.setMessage("Sorry, such login already exists.");
        return serverDto;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ObjectIsFullException.class)
    public ServerDto ObjectIsFullException() {
        final ServerDto serverDto = new ServerDto();
        serverDto.setCode(3);
        serverDto.setMessage("Sorry, such object is full.");
        return serverDto;
    }

}
