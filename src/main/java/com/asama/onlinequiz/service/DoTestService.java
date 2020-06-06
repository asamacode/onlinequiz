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

import com.asama.onlinequiz.model.Question;
import com.asama.onlinequiz.model.Test;

@SessionScope
@Service
public class DoTestService {

    @Autowired
    TestService testService;

    private final Integer PAGE_SIZE = 1;

    private Long testId;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    Map<Long, Question> questions = new HashMap<Long, Question>();

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

    public void setQuestionTick(Long id) {
        Question qs = this.questions.get(id);
        qs.setIsTicked(true);
        this.questions.put(id, qs);
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
