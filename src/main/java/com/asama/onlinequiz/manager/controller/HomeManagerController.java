package com.asama.onlinequiz.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class HomeManagerController {

    @RequestMapping(value = { "/", "/home" })
    public String home() {
        return "manager/home/index";
    }
}
