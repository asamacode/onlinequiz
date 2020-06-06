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
import com.asama.onlinequiz.model.SchoolYear;
import com.asama.onlinequiz.model.Subject;
import com.asama.onlinequiz.service.AppClassService;
import com.asama.onlinequiz.service.SchoolYearService;
import com.asama.onlinequiz.service.SubjectService;

@Controller
@RequestMapping("/manager/class")
public class ClassManagerController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    AppClassService aService;

    @Autowired
    SchoolYearService schoolYearService;

    @RequestMapping("/{classid}/list-subject")
    public String listSubject(Model model, @PathVariable("classid") Long classId) {
        Optional<AppClass> appClasses = aService.findById(classId);
        if (appClasses.isPresent()) {

            model.addAttribute("subjects", appClasses.get().getSubjects());
        } else {
            model.addAttribute("message", "Lớp học không tồn tại !");
        }

        return "manager/class/list-subject";
    }

    @RequestMapping("/{classid}/add-subject/{subid}")
    public String addSubject(RedirectAttributes re, @PathVariable("classid") Long classId,
            @PathVariable("subid") Long subId) {
        Optional<AppClass> appClass = aService.findById(classId);
        if (appClass.isPresent()) {
            Optional<Subject> sub = subjectService.findById(subId);
            if (sub.isPresent()) {

                sub.get().getAppClasses().add(appClass.get());
                appClass.get().getSubjects().add(sub.get());
                
                aService.update(appClass.get());

                re.addAttribute("message",
                        "Đã thêm môn học " + sub.get().getName() + " vào lớp " + appClass.get().getName());

            } else {
                re.addAttribute("message", "Môn học không tồn tại !");
            }
        } else {
            re.addAttribute("message", "Lớp học không tồn tại !");
        }

        return "redirect:/manager/class/list";
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        Optional<AppClass> appClass = aService.findById(id);
        if (appClass.isPresent()) {
            aService.delete(appClass.get());
            return "Xóa thành công !";
        } else {
            return "Lớp học này không tồn tại !";
        }
    }

    @PostMapping("/update")
    public String edit(RedirectAttributes re, Model model, @Valid @ModelAttribute("app_class") AppClass appClass,
            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/class/add";
        }

        aService.update(appClass);
        re.addAttribute("message", "Cập nhật thành công !");

        return "redirect:/manager/class/add";
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<AppClass> appClass = aService.findById(id);
        if (appClass.isPresent()) {
            List<SchoolYear> years = schoolYearService.findAll();
            model.addAttribute("years", years);
            model.addAttribute("app_class", appClass.get());
        } else {
            model.addAttribute("message", "Lớp học này không tồn tại !");
        }
        return "manager/class/add";
    }

    @PostMapping("/add")
    public String add(RedirectAttributes re, Model model, @Valid @ModelAttribute("app_class") AppClass appClass,
            BindingResult result) {

        if (result.hasErrors()) {
            List<SchoolYear> years = schoolYearService.findAll();
            model.addAttribute("years", years);
            model.addAttribute("message", "Vui lòng nhập thông tin hợp lệ !");
            return "manager/class/add";
        }

        aService.save(appClass);
        re.addAttribute("message", "Thêm thành công !");

        return "redirect:/manager/class/add";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<SchoolYear> years = schoolYearService.findAll();
        model.addAttribute("years", years);
        model.addAttribute("app_class", new AppClass());
        return "manager/class/add";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<AppClass> classes = aService.findAll();
        model.addAttribute("classes", classes);
        return "manager/class/list";
    }
}
