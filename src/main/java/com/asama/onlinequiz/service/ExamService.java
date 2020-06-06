package com.asama.onlinequiz.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.asama.onlinequiz.model.Question;

@SessionScope
@Service
public class ExamService {
    
    @Autowired
    QuestionService questionService;
    
    Map<Long, Question> exams = new HashMap<Long, Question>();
    
    private final Integer PAGE_SIZE = 1;

    private Integer diff, nor, easy;

    private Long subId, speId;

    public Long getSpeId() {
        return speId;
    }

    public void setSpeId(Long speId) {
        this.speId = speId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }

    public Integer getNor() {
        return nor;
    }

    public void setNor(Integer nor) {
        this.nor = nor;
    }

    public Integer getEasy() {
        return easy;
    }

    public void setEasy(Integer easy) {
        this.easy = easy;
    }
    
    public Integer getTotalPage() {
        return (int) Math.ceil(1.0*getCount()/PAGE_SIZE);
    }
    
    public List<Question> getPage(Integer page) {
        Collection<Question> questions = exams.values();
        List<Question> qList = new ArrayList<Question>(questions);
        int startPage = PAGE_SIZE*(page-1);
        int finishPage = PAGE_SIZE*page> qList.size()-1? qList.size() :PAGE_SIZE*page;
        
        return qList.subList(startPage, finishPage);
    }

    public Collection<Question> getItems() {
        return exams.values();
    }

    public void findAllByLevel(Long subId, Integer easy, Integer normal, Integer diff) {
        clear();

        List<Question> questions = questionService.findAllBySubjectIdDif(subId, diff);
        List<Question> questions1 = questionService.findAllBySubjectIdNor(subId, normal);
        List<Question> questions2 = questionService.findAllBySubjectIdEasy(subId, easy);

        setDiff(questions.size());
        setNor(questions1.size());
        setEasy(questions2.size());
        setSubId(subId);

        questions.addAll(questions1);
        questions.addAll(questions2);

        for (Question qs : questions) {
            exams.put(qs.getId(), qs);
        }

    }

    public void add(Long id) {
        Question qs = questionService.findById(id).get();
        
        if (exams.get(qs.getId()) != null) {
            return;
        } else {
            exams.put(qs.getId(), qs);
            
            if (qs.getLevel() == Question.LV_EASY) {
                setEasy(this.easy + 1);
            } else if (qs.getLevel() == Question.LV_NORMAL) {
                setNor(this.nor + 1);
            } else if (qs.getLevel() == Question.LV_HARD) {
                setDiff(this.diff + 1);
            }
        }
        
    }

    public void remove(Long id) {
        Question qs = questionService.findById(id).get();
        if (qs.getLevel() == Question.LV_EASY) {
            setEasy(this.easy - 1);
        } else if (qs.getLevel() == Question.LV_NORMAL) {
            setNor(this.nor - 1);
        } else if (qs.getLevel() == Question.LV_HARD) {
            setDiff(this.diff - 1);
        }
        exams.remove(id);
    }

    public void clear() {
        exams.clear();
        setDiff(0);
        setNor(0);
        setEasy(0);
        setSubId((long) 0);
    }

    public Integer getCount() {
        return exams.size();
    }

}
