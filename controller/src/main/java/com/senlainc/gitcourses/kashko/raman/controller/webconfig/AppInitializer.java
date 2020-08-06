package com.senlainc.gitcourses.kashko.raman.controller.webconfig;

import com.senlainc.gitcourses.kashko.raman.controller.security.SecurityJavaConfig;
import com.senlainc.gitcourses.kashko.raman.repositoryimpl.config.DBConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DBConfig.class, SecurityJavaConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
