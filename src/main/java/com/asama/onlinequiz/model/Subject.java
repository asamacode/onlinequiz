package com.asama.onlinequiz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    private Integer credits;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sepcialization_id")
    private Specialization specialization;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "class_subject", joinColumns = { @JoinColumn(name = "subject_id") }, inverseJoinColumns = {
            @JoinColumn(name = "class_id") })
    private List<AppClass> appClasses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Question> questions = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    public List<Question> getQuestions() {
        return questions;
    }

    public List<AppClass> getAppClasses() {
        return appClasses;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setAppClasses(List<AppClass> appClasses) {
        this.appClasses = appClasses;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

}
