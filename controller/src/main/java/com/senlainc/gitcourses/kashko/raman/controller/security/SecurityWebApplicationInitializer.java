package com.senlainc.gitcourses.kashko.raman.controller.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@ComponentScan(basePackages = {"com.senlainc.gitcourses.kashko.raman.serviceimpl", "com.senlainc.gitcourses.kashko.raman.security",
        "com.senlainc.gitcourses.kashko.raman.controller.security"})
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
