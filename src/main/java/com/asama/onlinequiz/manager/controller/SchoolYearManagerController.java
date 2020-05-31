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

import com.asama.onlinequiz.model.SchoolYear;
import com.asama.onlinequiz.service.SchoolYearService;

@Controller
@RequestMapping("/manager/schoolyear")
public class SchoolYearManagerController {

    @Autowired
    SchoolYearService schoolYearService;
    
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Optional<SchoolYear> schoolYear = schoolYearService.findById(id);
        if (schoolYear.isPresent()) {
            model.addAttribute("schoolyear", schoolYear.get());
        } else {
            model.addAttribute("schoolyear", new SchoolYear());
            model.addAttribute("message", "Niên khóa không tồn tại !");
        }
        
        return "manager/schoolyear/add";
    }
    
    @PostMapping("/update")
    public String update(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("schoolyear") SchoolYear schoolYear,
            BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/schoolyear/add";
        }
        schoolYearService.update(schoolYear);
        re.addAttribute("message", "Cập nhật thành công !");
        return "redirect:/manager/schoolyear/update/"+schoolYear.getId();
    }
    
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(RedirectAttributes re, @PathVariable("id") Long id) {
        Optional<SchoolYear> schoolYear = schoolYearService.findById(id);
        if (schoolYear.isPresent()) {
            schoolYearService.delete(schoolYear.get());
            return "Xóa thành công";
        } else {
            return "Niên khóa không tồn tại !";
        }
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        List<SchoolYear> list = schoolYearService.findAll();
        model.addAttribute("schoolyears", list);
        return "manager/schoolyear/list";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("schoolyear", new SchoolYear());
        return "manager/schoolyear/add";
    }
    
    @PostMapping("/add")
    public String addOrUpdate(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("schoolyear") SchoolYear schoolYear,
            BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/schoolyear/add";
        }
        schoolYearService.save(schoolYear);
        re.addAttribute("message", "Thêm thành công !");
        return "redirect:/manager/schoolyear/add";
    }
}
