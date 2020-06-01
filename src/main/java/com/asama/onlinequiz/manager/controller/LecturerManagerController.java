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

import com.asama.onlinequiz.EncryptUtils;
import com.asama.onlinequiz.model.AppClass;
import com.asama.onlinequiz.model.Lecturer;
import com.asama.onlinequiz.model.UserRole;
import com.asama.onlinequiz.service.AppClassService;
import com.asama.onlinequiz.service.LecturerService;
import com.asama.onlinequiz.service.UserRoleService;

@Controller
@RequestMapping("/manager/lecturer")
public class LecturerManagerController {
    @Autowired
    LecturerService lecturerService;
    
    @Autowired
    AppClassService appClassService;
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    ServletContext app;
    
    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id) {
        Optional<Lecturer> lecturer = lecturerService.findById(id);
        if (lecturer.isPresent()) {
            lecturerService.delete(lecturer.get());
            return "Xóa thành công !";
        } else {
            return "Giảng viên này không tồn tại !";
        }
    }
    
    @PostMapping("/update")
    public String edit(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("lecturer") Lecturer lecturer,
            BindingResult result,
            @RequestParam("pass") String pass,
            @RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
        
        if (result.hasErrors()) {
            
            List<UserRole> roles = userRoleService.findAll();
            
            model.addAttribute("roles", roles);
            model.addAttribute("message", "Vui lòng nhập nội dung hợp lệ !");
            return "manager/lecturer/add";
        }
        
        if (!file.isEmpty()) {
            String path = app.getRealPath("/static/images/lecturers");
            lecturer.setImage(lecturer.getId() + file.getOriginalFilename());
            File f = new File(path, lecturer.getId()+ file.getOriginalFilename());
            file.transferTo(f);
        }
        lecturer.setPassword(pass);
        lecturerService.update(lecturer);
        re.addAttribute("message", "Cập nhật thành công !");
        
        return "redirect:/manager/lecturer/update/"+lecturer.getId();
    }
    
    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Optional<Lecturer> lecturer = lecturerService.findById(id);
        if (lecturer.isPresent()) {
            List<AppClass> appClasses = appClassService.findAll();
            List<UserRole> roles = userRoleService.findAll();
            model.addAttribute("app_classes", appClasses);
            model.addAttribute("roles", roles);
            model.addAttribute("lecturer", lecturer.get());
        } else {
            model.addAttribute("message", "Giảng viên này không tồn tại !");
        }
        
        return "manager/lecturer/add";
    }
    
    @PostMapping("/add")
    public String add(RedirectAttributes re, Model model,
            @Valid @ModelAttribute("lecturer") Lecturer lecturer,
            BindingResult result,
            @RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
        if (result.hasErrors()) {
            
            List<UserRole> roles = userRoleService.findAll();
            
            model.addAttribute("roles", roles);
            model.addAttribute("message", "Vui lòng nhập nội dung hợp lệ !");
            return "manager/lecturer/add";
        } 
        
        if (file.isEmpty()) {
            lecturer.setImage("lecturer.png");
        } else {
            String path = app.getRealPath("/static/images/lecturers");
            lecturer.setImage(lecturer.getId() + file.getOriginalFilename());
            File f = new File(path, lecturer.getId()+ file.getOriginalFilename());
            file.transferTo(f);
        }
        String pass = EncryptUtils.MD5(lecturer.getPassword());
        lecturer.setPassword(pass);
        lecturerService.save(lecturer);
        re.addAttribute("message", "Thêm thành công !");
        
        return "redirect:/manager/lecturer/add";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        List<AppClass> appClasses = appClassService.findAll();
        List<UserRole> roles = userRoleService.findAll();
        model.addAttribute("app_classes", appClasses);
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("roles", roles);
        return "manager/lecturer/add";
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        List<Lecturer> lecturers = lecturerService.findAll();
        model.addAttribute("lecturers", lecturers);
        return "manager/lecturer/list";
    }
}
