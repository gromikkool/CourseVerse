package com.senlainc.gitcourses.kashko.raman.entity;


import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "number_of_phone")
    private String numberOfPhone;
    @OneToOne(mappedBy = "profile", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private User user;
    @OneToOne(mappedBy = "profile", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address address;

    public Profile() {
    }

    public Profile(Integer id, String firstName, String lastName, String numberOfPhone, User user, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPhone = numberOfPhone;
        this.user = user;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumberOfPhone() {
        return numberOfPhone;
    }

    public void setNumberOfPhone(String numberOfPhone) {
        this.numberOfPhone = numberOfPhone;
    }

}
