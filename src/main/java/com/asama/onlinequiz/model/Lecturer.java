package com.asama.onlinequiz.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lecturers")
public class Lecturer extends AppUser {

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private Set<Question> questions;

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

}
