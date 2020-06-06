package com.asama.onlinequiz.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class TestDTO {

    private Long id;

    private String name;

    private Integer numQuestions;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date open;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date close;

    private Long subjectId;

    private Integer numDifficult;

    private Integer numNormal;

    private Integer numEasy;

    private Integer limitTime;

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

    public Integer getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }

    public Date getOpen() {
        return open;
    }

    public void setOpen(Date open) {
        this.open = open;
    }

    public Date getClose() {
        return close;
    }

    public void setClose(Date close) {
        this.close = close;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getNumDifficult() {
        return numDifficult;
    }

    public void setNumDifficult(Integer numDifficult) {
        this.numDifficult = numDifficult;
    }

    public Integer getNumNormal() {
        return numNormal;
    }

    public void setNumNormal(Integer numNormal) {
        this.numNormal = numNormal;
    }

    public Integer getNumEasy() {
        return numEasy;
    }

    public void setNumEasy(Integer numEasy) {
        this.numEasy = numEasy;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

}
