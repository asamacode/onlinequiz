package com.asama.onlinequiz.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asama.onlinequiz.model.Question;
import com.asama.onlinequiz.model.Specialization;
import com.asama.onlinequiz.model.Test;
import com.asama.onlinequiz.service.ExamService;
import com.asama.onlinequiz.service.QuestionService;
import com.asama.onlinequiz.service.SpecializationService;
import com.asama.onlinequiz.service.TestService;

@Controller
@RequestMapping("/manager/test")
public class TestManagerController {

    @Autowired
    ExamService examService;

    @Autowired
    QuestionService questionService;

    @Autowired
    SpecializationService specializationService;

    @Autowired
    TestService testService;

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Optional<Test> test = testService.findById(id);
        if (test.isPresent()) {

            model.addAttribute("test", test.get());
        } else {
            model.addAttribute("test", new Test());
            model.addAttribute("message", "Đề thi không tồn tại !");
        }

        return "manager/test/add";
    }

    @PostMapping("/update")
    public String update(RedirectAttributes re, Model model, @Valid @ModelAttribute("test") Test test,
            BindingResult result) {
        if (result.hasErrors()) {

            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/test/add";
        }
        testService.update(test);
        re.addAttribute("message", "Cập nhật thành công !");
        return "redirect:/manager/test/update/" + test.getId();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(RedirectAttributes re, @PathVariable("id") Long id) {
        Optional<Test> test = testService.findById(id);
        if (test.isPresent()) {
            testService.delete(test.get());
            return "Xóa thành công";
        } else {
            return "Đề thi không tồn tại !";
        }
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Test> list = testService.findAll();
        model.addAttribute("tests", list);
        return "manager/test/list";
    }

    @RequestMapping("/add/{specid}/{id}")
    public String addQues(@PathVariable("id") Long id, @PathVariable("specid") Long specid) {
        examService.add(id);
        return "redirect:/manager/test/add/" + specid;
    }

    @RequestMapping("/question")
    @ResponseBody
    public List<Question> deleteQuestion(@RequestParam("search") String key, @RequestParam("subid") Long subId) {
        List<Question> questions = questionService.searchByKeyAndSubject(key, subId);
        return questions;
    }

    @PostMapping("/create")
    public String create(RedirectAttributes re, @Valid @ModelAttribute("tss") Test test, BindingResult result) {
        if (result.hasErrors()) {
            re.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            
            return "redirect:/manager/test/add/" + examService.getSpeId();
        }

        test.setQuestions(new ArrayList<>(examService.getItems()));
        testService.save(test);
        examService.clear();

        return "redirect:/manager/test/list";
    }

    @RequestMapping("/question/delete-all")
    @ResponseBody
    public Boolean deleteQuestion() {
        examService.clear();
        return true;
    }

    @RequestMapping("/question/delete/{id}")
    @ResponseBody
    public Boolean deleteQuestion(@PathVariable("id") Long id) {
        examService.remove(id);
        return true;
    }

    @RequestMapping("/add/{speid}/page/{pageid}")
    public String preview(Model model, @PathVariable("speid") Long sid, @PathVariable("pageid") Integer page) {
        
        if (page == null) {
            page = 1;
        }
        Optional<Specialization> spec = specializationService.findById(sid);
        if (spec.isPresent()) {
            model.addAttribute("tss", new Test());
            model.addAttribute("subjects", spec.get().getSubjects());
            model.addAttribute("diff", examService.getDiff());
            model.addAttribute("normal", examService.getNor());
            model.addAttribute("easy", examService.getEasy());
        }
        if (examService.getItems().size() > 0) {
            model.addAttribute("subid", examService.getSubId());
            model.addAttribute("speid", examService.getSpeId());
            List<Question> qss = examService.getPage(page);
            model.addAttribute("currentQuestions", qss);
            model.addAttribute("page", page);
            model.addAttribute("pageCount", examService.getTotalPage());
        }
        return "manager/test/add";
    }

    // load subject by specialization id
    @RequestMapping("/add/{speid}")
    public String add(Model model, @PathVariable("speid") Long sid,
            @RequestParam(value = "subject", required = false) Long subId,
            @RequestParam(value = "diff", required = false) Integer dif,
            @RequestParam(value = "normal", required = false) Integer nor,
            @RequestParam(value = "easy", required = false) Integer easy) {
        
        Optional<Specialization> spec = specializationService.findById(sid);
        if (spec.isPresent()) {
            examService.setSpeId(sid);
            model.addAttribute("subjects", spec.get().getSubjects());
            model.addAttribute("tss", new Test());
            model.addAttribute("diff", examService.getDiff());
            model.addAttribute("normal", examService.getNor());
            model.addAttribute("easy", examService.getEasy());

            if (dif != null && nor != null && easy != null) {
                examService.findAllByLevel(subId, easy, nor, dif);
            }
            if (examService.getItems().size() > 0) {
                model.addAttribute("subid", examService.getSubId());
                model.addAttribute("currentQuestions", examService.getPage(1));
                model.addAttribute("page", 1);
                model.addAttribute("pageCount", examService.getTotalPage());
                model.addAttribute("speid", examService.getSpeId());
            }

        } else {
            model.addAttribute("message", "Lớp học này không tồn tại !");
        }

        return "manager/test/add";
    }

    @PostMapping("/add")
    public String addOrUpdate(RedirectAttributes re, Model model, @Valid @ModelAttribute("test") Test test,
            BindingResult result) {
        if (result.hasErrors()) {

            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/test/add";
        }
        testService.save(test);
        re.addAttribute("message", "Thêm thành công !");
        return "redirect:/manager/test/add";
    }
}
