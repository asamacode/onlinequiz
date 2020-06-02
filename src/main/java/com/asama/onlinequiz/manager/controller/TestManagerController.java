package com.asama.onlinequiz.manager.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asama.onlinequiz.model.AppClass;
import com.asama.onlinequiz.model.Test;
import com.asama.onlinequiz.service.AppClassService;
import com.asama.onlinequiz.service.TestService;

@Controller
@RequestMapping("/manager/test")
public class TestManagerController {
    
    @Autowired
    AppClassService classService;
    
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

    @GetMapping("/add/{classid}")
    public String add(Model model, @ModelAttribute("classid") Long classId) {
        Optional<AppClass> appClass = classService.findById(classId);
        if (appClass.isPresent()) {
            model.addAttribute("subjects", appClass.get().getSubjects());
            model.addAttribute("test", new Test());
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

