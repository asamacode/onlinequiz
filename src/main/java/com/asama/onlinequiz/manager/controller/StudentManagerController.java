package com.asama.onlinequiz.manager.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asama.onlinequiz.model.AppClass;
import com.asama.onlinequiz.model.Student;
import com.asama.onlinequiz.model.UserRole;
import com.asama.onlinequiz.service.AppClassService;
import com.asama.onlinequiz.service.StudentService;
import com.asama.onlinequiz.service.UserRoleService;
import com.asama.onlinequiz.utils.EncryptUtils;

@Controller
@RequestMapping("/manager/student")
public class StudentManagerController {

    @Autowired
    StudentService studentService;
    
    @Autowired
    AppClassService appClassService;
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    ServletContext app;
    
    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            studentService.delete(student.get());
            return "Xóa thành công !";
        } else {
            return "Sinh viên này không tồn tại !";
        }
    }
    
    @PostMapping("/update")
    public String edit(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            @RequestParam("pass") String pass,
            @RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
        
        if (result.hasErrors()) {
            List<AppClass> appClasses = appClassService.findAll();
            List<UserRole> roles = userRoleService.findAll();
            model.addAttribute("app_classes", appClasses);
            model.addAttribute("roles", roles);
            model.addAttribute("message", "Vui lòng nhập nội dung hợp lệ !");
            return "manager/student/add";
        }
        
        if (!file.isEmpty()) {
            String path = app.getRealPath("/static/images/students");
            student.setImage(student.getId() + file.getOriginalFilename());
            File f = new File(path, student.getId()+ file.getOriginalFilename());
            file.transferTo(f);
        }
        student.setPassword(pass);
        studentService.update(student);
        re.addAttribute("message", "Cập nhật thành công !");
        
        return "redirect:/manager/student/update/"+student.getId();
    }
    
    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            List<AppClass> appClasses = appClassService.findAll();
            List<UserRole> roles = userRoleService.findAll();
            model.addAttribute("app_classes", appClasses);
            model.addAttribute("roles", roles);
            model.addAttribute("student", student.get());
        } else {
            model.addAttribute("message", "Sinh viên này không tồn tại !");
        }
        
        return "manager/student/add";
    }
    
    @PostMapping("/add")
    public String add(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            @RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
        if (result.hasErrors()) {
            List<AppClass> appClasses = appClassService.findAll();
            List<UserRole> roles = userRoleService.findAll();
            model.addAttribute("app_classes", appClasses);
            model.addAttribute("roles", roles);
            model.addAttribute("message", "Vui lòng nhập nội dung hợp lệ !");
            return "manager/student/add";
        } 
        
        if (file.isEmpty()) {
            student.setImage("student.png");
        } else {
            String path = app.getRealPath("/static/images/students");
            student.setImage(student.getId() + file.getOriginalFilename());
            File f = new File(path, student.getId()+ file.getOriginalFilename());
            file.transferTo(f);
        }
        String pass = EncryptUtils.MD5(student.getPassword());
        student.setPassword(pass);
        studentService.save(student);
        re.addAttribute("message", "Thêm thành công !");
        
        return "redirect:/manager/student/add";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        List<AppClass> appClasses = appClassService.findAll();
        List<UserRole> roles = userRoleService.findAll();
        model.addAttribute("app_classes", appClasses);
        model.addAttribute("student", new Student());
        model.addAttribute("roles", roles);
        return "manager/student/add";
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "manager/student/list";
    }
}
