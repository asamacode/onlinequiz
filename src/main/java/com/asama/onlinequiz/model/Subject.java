package com.asama.onlinequiz.model;

import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar")
    private String name;

    private Integer credits;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sepcialization_id")
    private Specialization specialization;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "class_subject", joinColumns = { @JoinColumn(name = "subject_id") }, inverseJoinColumns = {
            @JoinColumn(name = "class_id") })
    private Set<AppClass> appClasses;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Question> questions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
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

    public Set<AppClass> getAppClasses() {
        return appClasses;
    }

    public void setAppClasses(Set<AppClass> appClasses) {
        this.appClasses = appClasses;
    }

}
