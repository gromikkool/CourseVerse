package com.senlainc.gitcourses.kashko.raman.api.exceptions;

public class LoginNotFoundException extends RuntimeException {
    public LoginNotFoundException(String message) {
        super(message);
    }
}
