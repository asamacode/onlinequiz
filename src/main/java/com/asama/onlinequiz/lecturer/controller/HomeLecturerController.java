package com.asama.onlinequiz.lecturer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lecturer")
public class HomeLecturerController {

    @RequestMapping(value = { "/", "/home" })
    public String home() {
        return "lecturer/home/index";
    }
}
