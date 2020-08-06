package com.senlainc.gitcourses.kashko.raman.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_section")
    private String name;
    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private List<Subject> subjects;

    public Section() {
    }

    public Section(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
