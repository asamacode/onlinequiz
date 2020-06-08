package com.asama.onlinequiz.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asama.onlinequiz.model.ResultTest;
import com.asama.onlinequiz.model.Student;
import com.asama.onlinequiz.model.Test;
import com.asama.onlinequiz.service.DoTestService;
import com.asama.onlinequiz.service.ResultTestService;
import com.asama.onlinequiz.utils.TimeUtils;

@Controller
public class DoTestController {

    @Autowired
    DoTestService doTestService;
    
    @Autowired
    ResultTestService resultService;

    @Autowired
    HttpSession session;
    
    @RequestMapping("/mytest/view/{tid}")
    public String viewTestDetail(Model model) {
        return "redirect:/login";
    }
    
    @RequestMapping("/mytest/history")
    public String history(Model model) {
        Student student = (Student) session.getAttribute("user");
        if (student != null) {
            List<ResultTest> tests = resultService.findAllListTestedByStudent(student);
            model.addAttribute("tests", tests);
        } else {
            return "redirect:/login";
        }
        
        return "test/list-result";
    }
    
    @RequestMapping("/dotest/submit")
    public String submit(Model model) {
        String startTime = (String) session.getAttribute("timeStarting");
        String endTime = (String) session.getAttribute("timeFinishing");
        try {
            Date start = TimeUtils.covertStringToDate(startTime);
            Date end = TimeUtils.covertStringToDate(endTime);
            
            Student student = (Student) session.getAttribute("user");
            Test test = doTestService.getCurrentTest();
            Float scFloat = doTestService.countMark();
            ResultTest resultTest = new ResultTest();
            resultTest.setTimeStart(start);
            resultTest.setTimeEnd(end);
            resultTest.setTest(test);
            resultTest.setStudent(student);
            resultTest.setScore(scFloat);
            
            resultService.save(resultTest);
            model.addAttribute("ts", test);
            model.addAttribute("score", scFloat);
            
            session.removeAttribute("timeStarting");
            session.removeAttribute("timeFinishing");
            
            doTestService.clear();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return "test/result";
    }

    @RequestMapping("/dotest/countdown")
    @ResponseBody
    public String countdown() {
        long timeLimit;
        String timeFinshing = (String) session.getAttribute("timeFinishing");
        String currentTime = TimeUtils.getCurrentTime();
        timeLimit = TimeUtils.timeRemain(timeFinshing, currentTime) / 1000;
        if (timeLimit > 0) {
            return String.format("%02d : %02d", TimeUtils.getMinutes(timeLimit), TimeUtils.getSecondRemain(timeLimit));
        } else {
            session.setAttribute("isComplete",true);
            return "Bạn đã thi xong rồi!";
        }
    }

    @RequestMapping("/start")
    @ResponseBody
    public Boolean start() {
        session.setAttribute("isStart", true);
        String timeStarting = TimeUtils.getCurrentTime();
        String timeFinishing = TimeUtils.addTime(timeStarting, 1);
        session.setAttribute("timeStarting", timeStarting);
        session.setAttribute("timeFinishing", timeFinishing);
        return true;
    }

    @RequestMapping("/dotest/click-answer")
    @ResponseBody
    public Boolean clickAnswer(@RequestParam("qsid") Long qid, @RequestParam("asid") Long aid) {
        doTestService.setAnswerQuestionTick(qid, aid);
        return true;
    }

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
