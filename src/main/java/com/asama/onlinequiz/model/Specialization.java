package com.asama.onlinequiz.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "specialization")
public class Specialization {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "nvarchar")
    private String name;
    
    @OneToMany(mappedBy = "specialization")
    private List<Subject> subjects;

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

}
