package com.senlainc.gitcourses.kashko.raman.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Integer id;
    private String country;
    private String city;
//    private ProfileDto profile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    public ProfileDto getProfile() {
//        return profile;
//    }
//
//    public void setProfile(ProfileDto profile) {
//        this.profile = profile;
//    }
}
