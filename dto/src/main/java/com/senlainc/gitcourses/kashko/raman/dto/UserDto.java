package com.senlainc.gitcourses.kashko.raman.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class UserDto {
    private int id;
    @NotBlank
    @NotEmpty
    @NotNull
    @UniqueElements
    private String login;

    @NotBlank
    @NotEmpty
    @NotNull
    @JsonProperty
    private String password;

    //    private Role role;
    private Set<CourseShortDto> courses;
    private ProfileDto profile;
    private List<RoleDto> roles;

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    public Set<CourseShortDto> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseShortDto> courses) {
        this.courses = courses;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }
}
