package com.asama.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asama.onlinequiz.model.Test;
import com.asama.onlinequiz.service.DoTestService;

@Controller
public class DoTestController {

    @Autowired
    DoTestService doTestService;
    
    @RequestMapping("/dotest/{testid}")
    public String start(Model model, @PathVariable("testid") Long testId) {   
        Test test = doTestService.getAllQuestionTest(testId);
        doTestService.setTestId(testId);
        model.addAttribute("ts", test);
        return "test/start";
    }
    
    @RequestMapping("/dotest")
    public String home(Model model, @RequestParam(value = "page", required = false) Integer page) {
        if (doTestService.getCount() < 1) {
            return "redirect:/home";
        }
        if (page == null) {
            page = 1;
        }
        model.addAttribute("questionsall", doTestService.getQuestions());
        model.addAttribute("questions", doTestService.getPage(page));
        model.addAttribute("page", page);
        return "test/dotest";
    }
}
