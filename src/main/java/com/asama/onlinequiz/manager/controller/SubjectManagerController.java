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

import com.asama.onlinequiz.model.Lecturer;
import com.asama.onlinequiz.model.Specialization;
import com.asama.onlinequiz.model.Subject;
import com.asama.onlinequiz.service.LecturerService;
import com.asama.onlinequiz.service.SpecializationService;
import com.asama.onlinequiz.service.SubjectService;

@Controller
@RequestMapping("/manager/subject")
public class SubjectManagerController {

    @Autowired
    SpecializationService specializationService;

    @Autowired
    LecturerService lecturerService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Optional<Subject> subject = subjectService.findById(id);
        if (subject.isPresent()) {
            List<Lecturer> lecturers = lecturerService.findAll();
            List<Specialization> specializations = specializationService.findAll();
            model.addAttribute("specializations", specializations);
            model.addAttribute("lecturers", lecturers);
            model.addAttribute("subject", subject.get());
        } else {
            model.addAttribute("subject", new Subject());
            model.addAttribute("message", "Môn học không tồn tại !");
        }

        return "manager/subject/add";
    }

    @PostMapping("/update")
    public String update(RedirectAttributes re, Model model, @Valid @ModelAttribute("subject") Subject subject,
            BindingResult result) {
        if (result.hasErrors()) {
            List<Lecturer> lecturers = lecturerService.findAll();
            List<Specialization> specializations = specializationService.findAll();
            model.addAttribute("specializations", specializations);
            model.addAttribute("lecturers", lecturers);
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/subject/add";
        }
        subjectService.update(subject);
        re.addAttribute("message", "Cập nhật thành công !");
        return "redirect:/manager/subject/update/" + subject.getId();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(RedirectAttributes re, @PathVariable("id") Long id) {
        Optional<Subject> subject = subjectService.findById(id);
        if (subject.isPresent()) {
            subjectService.delete(subject.get());
            return "Xóa thành công";
        } else {
            return "Môn học không tồn tại !";
        }
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Subject> list = subjectService.findAll();
        model.addAttribute("subjects", list);
        return "manager/subject/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Lecturer> lecturers = lecturerService.findAll();
        List<Specialization> specializations = specializationService.findAll();
        model.addAttribute("specializations", specializations);
        model.addAttribute("lecturers", lecturers);
        model.addAttribute("subject", new Subject());
        return "manager/subject/add";
    }

    @PostMapping("/add")
    public String addOrUpdate(RedirectAttributes re, Model model, @Valid @ModelAttribute("subject") Subject subject,
            BindingResult result) {
        if (result.hasErrors()) {
            List<Lecturer> lecturers = lecturerService.findAll();
            List<Specialization> specializations = specializationService.findAll();
            model.addAttribute("specializations", specializations);
            model.addAttribute("lecturers", lecturers);
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/subject/add";
        }
        subjectService.save(subject);
        re.addAttribute("message", "Thêm thành công !");
        return "redirect:/manager/subject/add";
    }
}
