package com.asama.onlinequiz.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.asama.onlinequiz.model.Answer;
import com.asama.onlinequiz.model.Question;
import com.asama.onlinequiz.model.Test;

@SessionScope
@Service
public class DoTestService {

    @Autowired
    TestService testService;

    private final Integer PAGE_SIZE = 5;

    private Long testId;

    Map<Long, Question> questions = new HashMap<Long, Question>();
    
    
    public void clear() {
        this.questions.clear();
        this.testId = null;
    }
    
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
   
    public Test getCurrentTest() {
        Optional<Test> tests = testService.findById(this.testId);
        return tests.get();
    }
    
    public Float countMark() {
        Float mark = (float) (10/this.questions.size());
        for (Question qs : this.getQuestions()) {
            if (qs.getIsTicked()) {
                for (Answer as : qs.getAnswers()) {
                    if (as.getIsTicked() && as.getId() == qs.getRightAnswer().getId()) {
                        mark++;
                    }
                }
            }
        }
        return mark;
    }

    public Test getAllQuestionTest(Long id) {
        Optional<Test> test = testService.findById(id);
        if (test.isPresent()) {
            this.questions.clear();
            for (Question qs : test.get().getQuestions()) {
                this.questions.put(qs.getId(), qs);
            }
        }
        return test.get();
    }

    public void setAnswerQuestionTick(Long qid, Long aid) {
        Question qs = this.questions.get(qid);
        for (Answer as : qs.getAnswers()) {

            if (as.getId() == aid) {
                as.setIsTicked(true);
            } else {

                as.setIsTicked(false);

            }
        }
        qs.setIsTicked(true);
        this.questions.put(qid, qs);
    }

    public List<Question> getQuestions() {
        Collection<Question> qss = this.questions.values();
        List<Question> qList = new ArrayList<Question>(qss);
        return qList;
    }

    public List<Question> getPage(Integer page) {
        Collection<Question> qss = this.questions.values();
        List<Question> qList = new ArrayList<Question>(qss);
        int startPage = PAGE_SIZE * (page - 1);
        int finishPage = PAGE_SIZE * page > qList.size() - 1 ? qList.size() : PAGE_SIZE * page;

        return qList.subList(startPage, finishPage);
    }

    public Integer getTotalPage() {
        return (int) Math.ceil(1.0 * getCount() / PAGE_SIZE);
    }

    public Integer getCount() {
        return this.questions.size();
    }
}
