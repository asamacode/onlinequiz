package com.asama.onlinequiz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends AppUser {

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private AppClass appClass;

    @OneToMany(mappedBy = "student")
    private List<ResultTest> resultTests;

    public List<ResultTest> getResultTests() {
        return resultTests;
    }

    public void setResultTests(List<ResultTest> resultTests) {
        this.resultTests = resultTests;
    }

    public AppClass getAppClass() {
        return appClass;
    }

    public void setAppClass(AppClass appClass) {
        this.appClass = appClass;
    }

}
