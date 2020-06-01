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

import com.asama.onlinequiz.model.Specialization;
import com.asama.onlinequiz.service.SpecializationService;

@Controller
@RequestMapping("/manager/specialization")
public class SpecializeManagerController {
    @Autowired
    SpecializationService specializationService;
    
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Optional<Specialization> specialization = specializationService.findById(id);
        if (specialization.isPresent()) {
            model.addAttribute("specialization", specialization.get());
        } else {
            model.addAttribute("specialization", new Specialization());
            model.addAttribute("message", "Chuyên ngành không tồn tại !");
        }
        
        return "manager/specialization/add";
    }
    
    @PostMapping("/update")
    public String update(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("specialization") Specialization specialization,
            BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/specialization/add";
        }
        specializationService.update(specialization);
        re.addAttribute("message", "Cập nhật thành công !");
        return "redirect:/manager/specialization/update/"+specialization.getId();
    }
    
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(RedirectAttributes re, @PathVariable("id") Long id) {
        Optional<Specialization> specialization = specializationService.findById(id);
        if (specialization.isPresent()) {
            specializationService.delete(specialization.get());
            return "Xóa thành công";
        } else {
            return "Chuyên ngành không tồn tại !";
        }
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        List<Specialization> list = specializationService.findAll();
        model.addAttribute("specializations", list);
        return "manager/specialization/list";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("specialization", new Specialization());
        return "manager/specialization/add";
    }
    
    @PostMapping("/add")
    public String addOrUpdate(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("specialization") Specialization specialization,
            BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/specialization/add";
        }
        specializationService.save(specialization);
        re.addAttribute("message", "Thêm thành công !");
        return "redirect:/manager/specialization/add";
    }
}

