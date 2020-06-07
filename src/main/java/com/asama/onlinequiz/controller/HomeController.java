package com.asama.onlinequiz.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asama.onlinequiz.dto.LoginDTO;
import com.asama.onlinequiz.model.Lecturer;
import com.asama.onlinequiz.model.Manager;
import com.asama.onlinequiz.model.Student;
import com.asama.onlinequiz.model.Test;
import com.asama.onlinequiz.service.LecturerService;
import com.asama.onlinequiz.service.ManagerService;
import com.asama.onlinequiz.service.StudentService;
import com.asama.onlinequiz.service.TestService;
import com.asama.onlinequiz.utils.EncryptUtils;

@Controller
public class HomeController {
    
    @Autowired
    TestService testService;

    @Autowired
    StudentService studentService;

    @Autowired
    ManagerService managerService;

    @Autowired
    LecturerService lecturerService;

    @Autowired
    HttpSession session;

    @RequestMapping(value = { "/", "/home" })
    public String home(Model model) {
        Student student = (Student) session.getAttribute("user");
        if (student != null) {
            List<Test> tests = testService.findAllListTestByStudent(student);
            model.addAttribute("tss", tests);
        }
        return "home/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("user", loginDTO);
        return "home/login";
    }

    @PostMapping("/login")
    public String login(Model model, @Valid @ModelAttribute("user") LoginDTO user, BindingResult errors) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập vào thông tin hợp lệ");
            return "home/login";
        }

        String pass = EncryptUtils.MD5(user.getPassword());
        user.setPassword(pass);

        switch (user.getUserType()) {
        case 1:
            Optional<Lecturer> lecturer = lecturerService.findByIdAndPass(user.getUserId(), user.getPassword());
            if (lecturer.isPresent()) {
                session.setAttribute("user", lecturer.get());
                return "redirect:/lecturer/home";
            } else {
                model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác !");
                return "home/login";
            }
        case 2:
            Optional<Manager> manager = managerService.findByIdAndPass(user.getUserId(), user.getPassword());
            if (manager.isPresent()) {
                session.setAttribute("user", manager.get());
                return "redirect:/manager/home";
            } else {
                model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác !");
                return "home/login";
            }
        default:
            Optional<Student> student = studentService.findByIdAndPass(user.getUserId(), user.getPassword());
            if (student.isPresent()) {
                session.setAttribute("user", student.get());
                return "redirect:/home";
            } else {
                model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác !");
                return "home/login";
            }
        }
    }
}
